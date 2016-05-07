import {Component,ViewContainerRef} from '@angular/core';
import {Router,RouteParams} from '@angular/router-deprecated';

import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {DbpDialogo,DbpDialogoBaseConf,DbpDialogoConfirmarConf} from '../../../core/modal/dialogo';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';
import {PersonaFisica} from '../../../model/persona/PersonaFisica';
import {DatosDeContacto} from '../../../model/localizacion/DatosDeContacto';
import {Direccion} from '../../../model/localizacion/Direccion';
import {PersonaFisicaService} from '../../../service/persona/PersonaFisicaService';
import {Grid} from '../../comun/grid/grid';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE,TiposEditables,Acciones} from '../../comun/grid/columna';

@Component({
  selector:'PersonaFisicaFicha',
  templateUrl:'app/component/pantallas/persona/PersonaFisicaFicha.component.html',
  providers:[PersonaFisicaService],
  directives:[Grid]
})
export class PersonaFisicaFichaComponent{

	modelo:PersonaFisica;
	estado:Estado;
	etiquetaEstado:string;
	operaciones:OperacionesUtils<PersonaFisica,number>;
  columnas:Array<Columna>;

	constructor(
		private personaFisicaService:PersonaFisicaService,
		private viewContainerRef:ViewContainerRef,
		private dialogo:DbpDialogo,
		private mensajeria:Mensajeria,
		params:RouteParams,
		private router:Router
	){
		var identificador:string=params.get("id");
	 	this.transitarCrear();
		if(identificador!=null){
			this.personaFisicaService.obtenerId(Number(identificador),this.viewContainerRef).subscribe(data=>{
			this.modelo=data;
      if(this.modelo.datosDeContacto.length==0){
        this.crearNuevaLinea();
      }
			this.transitarModificar();
			})
		}
    this.columnas=this.getColumnas();
		this.operaciones = new OperacionesUtils<PersonaFisica,number>(dialogo,personaFisicaService,viewContainerRef);
	  }

    crearNuevaLinea(){
          this.modelo.datosDeContacto.push(new DatosDeContacto(null,'','','',new Direccion(null,null,null)));
    }

    eliminarLinea(elemento:any){
        var idx = this.modelo.datosDeContacto.indexOf(elemento);
        this.modelo.datosDeContacto.splice(idx,1);
    }

  // Código relacionado con el grid.
  private getColumnas():Array<Columna>{
    return [
        new Columna('id','id',TIPO_NO_EDITABLE),
        new Columna('telefono','telefono',TIPO_EDITABLE),
        new Columna('nombre','nombre',TIPO_EDITABLE),
        new Columna('direccionDeCorreo','direccionDeCorreo',TIPO_EDITABLE)
        // Faltaria la parte de la dirección
    ];
  }

	onSubmit(){
		console.info('Moddelo',this.modelo);
		switch(this.estado){
			case Estado.CREAR:
				this.crear();
			break;
			case Estado.MODIFICAR:
				this.modificar();
			break;
		}
	}

	crear(){
	    this.operaciones.crear(
	      new DbpDialogoConfirmarConf('¿Quiere crear una nueva personaFisica?','personaFisica'),this.modelo
	      ,(data)=>{
          this.modelo=data;
	        this.mensajeria.success(this.viewContainerRef,'Se actualizado la personaFisica ('+data.id+') correctamente.');
	        this.transitarModificar();
	      });
	}

	modificar(){
		this.operaciones.actualizar(
			new DbpDialogoConfirmarConf('¿Quiere actualizar el personaFisica?','personaFisica'),this.modelo
			,(data)=>{
        this.modelo=data;
				this.mensajeria.success(this.viewContainerRef,'Se actualizado el personaFisica ('+data.id+') correctamente.');
		});
	}

	eliminar(id:number){
		this.operaciones.eliminar(
			new DbpDialogoConfirmarConf('¿Ser va eliminar el personaFisica '+id+'?','personaFisica'),id
			,(data)=>{
				this.mensajeria.success(this.viewContainerRef,'Se ha eliminado el personaFisica ('+id+') correctamente.');
				this.transitarCrear();
		});
	}

	isModificar():boolean{
		return this.estado == Estado.MODIFICAR;
	}

	volver(){
		this.router.navigate(['/PersonaFisica']);
	}
	transitarModificar(){
		this.estado = Estado.MODIFICAR;
		this.etiquetaEstado= 'Modifcar';
	}

	transitarCrear(){
		this.modelo=this.inicializarModelo();
    this.crearNuevaLinea();
		this.estado = Estado.CREAR;
		this.etiquetaEstado = 'Crear';
	}

	inicializarModelo():PersonaFisica{
		return  new PersonaFisica(null,null,null,null,null,[]);
	}
}
