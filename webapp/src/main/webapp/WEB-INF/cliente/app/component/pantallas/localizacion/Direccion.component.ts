import {Component,ViewContainerRef} from '@angular/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';

import {MunicipioComponent} from './Municipio.component';
import {Municipio} from '../../../model/localizacion/Municipio';
import {Direccion,DireccionFiltro} from '../../../model/localizacion/Direccion';
import {DireccionService} from '../../../service/localizacion/DireccionService';

@Component({
  selector:'Direccion',
  templateUrl:'app/component/pantallas/localizacion/Direccion.component.html',
  providers:[DireccionService],
  directives:[Grid,AutoFocus]
})
export class DireccionComponent{
    filtro:DireccionFiltro;
    modelo:Direccion;
    lineas:Array<Direccion>;
    columnas:Array<Columna>;
    estado:Estado;
    eFiltro:Estado=Estado.FILTRO;
    eCrear:Estado=Estado.CREAR;
    eModificar:Estado=Estado.MODIFICAR;
    operaciones:OperacionesUtils<Direccion,number>;
    constructor(
       private elemento:ViewContainerRef
      ,private dialogo:DbpDialogo
      ,private direccionService:DireccionService
      ,private mensajeria:Mensajeria
      ,private dbpDialogoRef:DbpDialogoRef
    ){
      this.filtro = new DireccionFiltro("","");
      this.modelo = new Direccion(null,null,new Municipio(null,null));
      this.lineas=[];
      this.columnas=this.getColumnas();
      this.transitarFiltro();
      this.operaciones = new OperacionesUtils<Direccion,number>(dialogo,direccionService,elemento);
    }
    consultar(){
      this.direccionService.filtrar(this.filtro,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    crear(){
		this.transitarCrear();
		this.modelo = new Direccion(null,null,new Municipio(null,null));
	}

	cancelar(){	this.transitarFiltro();	}

    guardar(){
      this.operaciones.crear(
        new DbpDialogoConfirmarConf('�Quiere crear una nueva direccion?','Direccion'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se ha creado la direccion ('+data.id+') correctamente.');
          this.lineas.push(data);
        });
    }

   modificar(){
      this.operaciones.actualizar(
        new DbpDialogoConfirmarConf('�Quiere actualizar la direccion?','Direccion'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se actualizado la direccion ('+data.id+') correctamente.');
          this.transitarFiltro();
        });
    }

	seleccionar(fila:any){
		this.modelo=fila;
		this.transitarModificar();
	}

    eliminarOp(id:number){
        var idx = this.lineas.indexOf(this.modelo);
		this.operaciones.eliminar(
		      new DbpDialogoConfirmarConf('�Ser va eliminar el direccion '+id+'?','Direccion'),id
		      ,(data)=>{
		        this.mensajeria.success(this.elemento,'Se ha eliminado el direccion ('+id+') correctamente.');
		        this.transitarFiltro();
		        this.lineas.splice(idx,1);
		});
	}

	eliminar(elemento:any){
	    var idx = this.lineas.indexOf(elemento);
	    if(idx != -1){
	        this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('�Quieres eliminar la direccion ('+elemento.id+')?','Direccion')).then(dialogoComponent=>{
	            dialogoComponent.instance.cuandoOk.then((_)=>{
	                this.direccionService.eliminar(elemento.id,this.elemento).subscribe(res=>{
	                    this.lineas.splice(idx,1);
	                });
	            });
	          });
      }
	}

  buscarMunicipio(){
    this.dialogo.abrir(MunicipioComponent,this.elemento,new DbpDialogoBaseConf('Municipio')).then(dialogoRef=>{
      console.info('Componente de dentro',dialogoRef.componenteDentro);
      dialogoRef.cuandoCerramos.then((_)=>{
        this.modelo.municipio=Object.assign(this.modelo.municipio,dialogoRef.componenteDentro.instance.seleccion)
        console.info('Seleccion municipio 3',this.modelo.municipio);
      });
      return dialogoRef;
    });
  }
  	isResetearSeleccionado():boolean{
  		return this.modelo.municipio.municipio!=null;
   	}

  	resetearMunicipio(){
  		this.modelo.municipio=new Municipio(null,null);
  	}

	municipioSeleccionado():string{
		if(this.modelo.municipio.municipio!=null){
			return this.modelo.municipio.municipio;
		}else{
			return 'desconocido';
		}
	}

	private transitarFiltro(){ this.estado=this.eFiltro; }
	private transitarCrear(){ this.estado=this.eCrear; }
	private transitarModificar(){ this.estado=this.eModificar; }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('direccion','direccion',TIPO_NO_EDITABLE)
      ];
    }
}
