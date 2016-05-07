import {Component,ViewContainerRef} from '@angular/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {ComunidadAutonoma,ComunidadAutonomaFiltro} from '../../../model/localizacion/ComunidadAutonoma';
import {ComunidadAutonomaService} from '../../../service/localizacion/ComunidadAutonomaService';

@Component({
  selector:'ComunidadAutonoma',
  templateUrl:'app/component/pantallas/localizacion/ComunidadAutonoma.component.html',
  directives:[Grid,AutoFocus]
})
export class ComunidadAutonomaComponent{
    modelo:ComunidadAutonomaFiltro;
    seleccion:ComunidadAutonoma;
    lineas:Array<ComunidadAutonoma>;
    columnas:Array<Columna>;
    constructor(
      private dialogo:DbpDialogo
      ,private ComunidadAutonomaService:ComunidadAutonomaService
      ,private viewContainerRef:ViewContainerRef
      ,private dbpDialogoRef:DbpDialogoRef
    ){

      this.modelo = new ComunidadAutonomaFiltro("","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.ComunidadAutonomaService.filtrar(this.modelo,this.viewContainerRef)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    seleccionar(fila:any){
        this.seleccion=fila;
        if(this.dbpDialogoRef!=null){
            this.dbpDialogoRef.cerrar();
        }
    }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('nombre','nombre',TIPO_NO_EDITABLE)
      ];
    }
}
