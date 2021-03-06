import {Component,ViewContainerRef} from '@angular/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Provincia,ProvinciaFiltro} from '../../../model/localizacion/Provincia';
import {ProvinciaService} from '../../../service/localizacion/ProvinciaService';

@Component({
  selector:'Provincia',
  templateUrl:'app/component/pantallas/localizacion/Provincia.component.html',
  directives:[Grid,AutoFocus]
})
export class ProvinciaComponent{
    modelo:ProvinciaFiltro;
    seleccion:Provincia;
    lineas:Array<Provincia>;
    columnas:Array<Columna>;
    constructor(
       private dialogo:DbpDialogo
      ,private ProvinciaService:ProvinciaService
      ,private viewContainerRef:ViewContainerRef
      ,private dbpDialogoRef:DbpDialogoRef
    ){
      this.modelo = new ProvinciaFiltro("","","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.ProvinciaService.filtrar(this.modelo,this.viewContainerRef)
        .subscribe(res=>{this.lineas=res.json(); });
    }

    seleccionar(fila:any){
        console.info('Seleccionar un elemento en la cuenta contable',fila);
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
