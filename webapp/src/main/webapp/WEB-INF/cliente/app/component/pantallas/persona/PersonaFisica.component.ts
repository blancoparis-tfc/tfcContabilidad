import {Component,ViewContainerRef} from '@angular/core';
import {Router} from '@angular/router-deprecated';

import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';

import {PersonaFisica,PersonaFisicaFiltro} from '../../../model/persona/PersonaFisica';
import {PersonaFisicaService} from '../../../service/persona/PersonaFisicaService';

@Component({
  selector:'PersonaFisica',
  templateUrl:'app/component/pantallas/persona/PersonaFisica.component.html',
  providers:[PersonaFisicaService],
  directives:[Grid,AutoFocus]
})
export class PersonaFisicaComponent{
    filtro:PersonaFisicaFiltro;
    modelo:PersonaFisica;
    lineas:Array<PersonaFisica>;
    columnas:Array<Columna>;
    estado:Estado;
    eFiltro:Estado=Estado.FILTRO;
    eCrear:Estado=Estado.CREAR;
    eModificar:Estado=Estado.MODIFICAR;
    operaciones:OperacionesUtils<PersonaFisica,number>;
    constructor(
       private elemento:ViewContainerRef
      ,private dialogo:DbpDialogo
      ,private personaFisicaService:PersonaFisicaService
      ,private mensajeria:Mensajeria
      ,private dbpDialogoRef:DbpDialogoRef
      ,private router:Router
    ){
      this.filtro = new PersonaFisicaFiltro("","","","");
      this.modelo = new PersonaFisica(null,null,null,null,null,[]);
      this.lineas=[];
      this.columnas=this.getColumnas();
      this.transitarFiltro();
      this.operaciones = new OperacionesUtils<PersonaFisica,number>(dialogo,personaFisicaService,elemento);
    }

    consultar(){
      this.personaFisicaService.filtrar(this.filtro,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    crear(){
        if(this.dbpDialogoRef!=null){
			this.transitarCrear();
			this.modelo = new PersonaFisica(null,null,null,null,null,[]);
        }else{
			this.router.navigate(['/PersonaFisicaFicha']);
        }
	}

	cancelar(){
		this.transitarFiltro();
	}

    guardar(){
      this.operaciones.crear(
        new DbpDialogoConfirmarConf('¿Quiere crear una nueva personaFisica?','PersonaFisica'),this.modelo
        ,(data)=>{
          this.modelo = data;
          this.lineas.push(data);
          this.transitarModificar();
          this.mensajeria.success(this.elemento,'Se ha creado la personaFisica ('+data.id+') correctamente.');
        });
    }

   modificar(){
      this.operaciones.actualizar(
        new DbpDialogoConfirmarConf('¿Quiere actualizar la personaFisica?','PersonaFisica'),this.modelo
        ,(data)=>{
          this.transitarFiltro();
          this.mensajeria.success(this.elemento,'Se actualizado la personaFisica ('+data.id+') correctamente.');
        });
    }

	seleccionar(fila:any){
    if(this.dbpDialogoRef!=null){
  		this.modelo=fila;
  		this.transitarModificar();
    }else{
      this.router.navigate(['/PersonaFisicaFicha',{id:fila.id}]);
    }
	}

    eliminarOp(id:number){
        var idx = this.lineas.indexOf(this.modelo);
		this.operaciones.eliminar(
		      new DbpDialogoConfirmarConf('¿Ser va eliminar el personaFisica '+id+'?','PersonaFisica'),id
		      ,(data)=>{
		        this.mensajeria.success(this.elemento,'Se ha eliminado el personaFisica ('+id+') correctamente.');
		        this.transitarFiltro();
		        this.lineas.splice(idx,1);
		});
	}

	eliminar(elemento:any){
	    var idx = this.lineas.indexOf(elemento);
	    if(idx != -1){
	        this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quieres eliminar la personaFisica ('+elemento.id+')?','PersonaFisica')).then(dialogoComponent=>{
	            dialogoComponent.instance.cuandoOk.then((_)=>{
	                this.personaFisicaService.eliminar(elemento.id,this.elemento).subscribe(res=>{
	                    this.lineas.splice(idx,1);
	                });
	            });
	          });
      }
	}

	private transitarFiltro(){ this.estado=this.eFiltro; }
	private transitarCrear(){ this.estado=this.eCrear; }
	private transitarModificar(){ this.estado=this.eModificar; }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('identificadorFiscal','identificadorFiscal',TIPO_NO_EDITABLE),
			new Columna('nombre','nombre',TIPO_NO_EDITABLE),
			new Columna('apellidos','apellidos',TIPO_NO_EDITABLE)
      ];
    }
}
