import {Component,ViewContainerRef} from 'angular2/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';

import {MunicipioComponent} from './Municipio.component';
import {Municipio} from '../../../model/localizacion/Municipio';
import {Direccion} from '../../../model/localizacion/Direccion';
import {DatosDeContacto,DatosDeContactoFiltro} from '../../../model/localizacion/DatosDeContacto';
import {DatosDeContactoService} from '../../../service/localizacion/DatosDeContactoService';

@Component({
  selector:'DatosDeContacto',
  templateUrl:'app/component/pantallas/localizacion/DatosDeContacto.component.html',
  providers:[DatosDeContactoService],
  directives:[Grid,AutoFocus]
})
export class DatosDeContactoComponent{
    filtro:DatosDeContactoFiltro;
    modelo:DatosDeContacto;
    lineas:Array<DatosDeContacto>;
    columnas:Array<Columna>;
    estado:Estado;
    eFiltro:Estado=Estado.FILTRO;
    eCrear:Estado=Estado.CREAR;
    eModificar:Estado=Estado.MODIFICAR;
    operaciones:OperacionesUtils<DatosDeContacto,number>;
    constructor(
       private elemento:ViewContainerRef
      ,private dialogo:DbpDialogo
      ,private datosDeContactoService:DatosDeContactoService
      ,private mensajeria:Mensajeria
      ,private dbpDialogoRef:DbpDialogoRef
    ){
      this.filtro = new DatosDeContactoFiltro("","","","");
      this.modelo = new DatosDeContacto(null,null,null,null,new Direccion(null,null,new Municipio(null,null)));
      this.lineas=[];
      this.columnas=this.getColumnas();
      this.transitarFiltro();
      this.operaciones = new OperacionesUtils<DatosDeContacto,number>(dialogo,datosDeContactoService,elemento);
    }

    consultar(){
      this.datosDeContactoService.filtrar(this.filtro,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    crear(){
		this.transitarCrear();
		this.modelo = new DatosDeContacto(null,null,null,null,new Direccion(null,null,new Municipio(null,null)));
	}

	cancelar(){
		this.transitarFiltro();
	}

    guardar(){
      this.operaciones.crear(
        new DbpDialogoConfirmarConf('¿Quiere crear una nueva datosDeContacto?','DatosDeContacto'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se ha creado la datosDeContacto ('+data.id+') correctamente.');
          this.lineas.push(data);
        });
   }

   modificar(){
      this.operaciones.actualizar(
        new DbpDialogoConfirmarConf('¿Quiere actualizar la datosDeContacto?','DatosDeContacto'),this.modelo
        ,(data)=>{
          this.mensajeria.success(this.elemento,'Se actualizado la datosDeContacto ('+data.id+') correctamente.');
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
		      new DbpDialogoConfirmarConf('¿Ser va eliminar el datosDeContacto '+id+'?','DatosDeContacto'),id
		      ,(data)=>{
		        this.mensajeria.success(this.elemento,'Se ha eliminado el datosDeContacto ('+id+') correctamente.');
		        this.transitarFiltro();
		        this.lineas.splice(idx,1);
		});
	}

	eliminar(elemento:any){
	    var idx = this.lineas.indexOf(elemento);
	    if(idx != -1){
	        this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quieres eliminar la datosDeContacto ('+elemento.id+')?','DatosDeContacto')).then(dialogoComponent=>{
	            dialogoComponent.instance.cuandoOk.then((_)=>{
	                this.datosDeContactoService.eliminar(elemento.id,this.elemento).subscribe(res=>{
	                    this.lineas.splice(idx,1);
	                });
	            });
	          });
      }
	}

  // TODO: Esto lo tenemos que poder poner en comun.
  buscarMunicipio(){
    this.dialogo.abrir(MunicipioComponent,this.elemento,new DbpDialogoBaseConf('Municipio')).then(dialogoRef=>{
      console.info('Componente de dentro',dialogoRef.componenteDentro);
      dialogoRef.cuandoCerramos.then((_)=>{
        this.modelo.direccion.municipio=Object.assign(this.modelo.direccion.municipio,dialogoRef.componenteDentro.instance.seleccion)
        console.info('Seleccion municipio 3',this.modelo.direccion.municipio);
      });
      return dialogoRef;
    });
  }
  	isResetearSeleccionado():boolean{
  		return this.modelo.direccion.municipio.municipio!=null;
   	}

  	resetearMunicipio(){
  		this.modelo.direccion.municipio=new Municipio(null,null);
  	}

	municipioSeleccionado():string{
  		if(this.modelo.direccion.municipio.municipio!=null){
  			return this.modelo.direccion.municipio.municipio;
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
			new Columna('telefono','telefono',TIPO_NO_EDITABLE),
			new Columna('nombre','nombre',TIPO_NO_EDITABLE),
			new Columna('direccionDeCorreo','direccionDeCorreo',TIPO_NO_EDITABLE)
      ];
    }
}
