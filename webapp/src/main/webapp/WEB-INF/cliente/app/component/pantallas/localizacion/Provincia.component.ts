import {Component,ElementRef} from 'angular2/core';
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
    lineas:Array<Provincia>;
    columnas:Array<Columna>;
    constructor(
      private elemento:ElementRef
      ,private dialogo:DbpDialogo
      ,private ProvinciaService:ProvinciaService
    ){

      this.modelo = new ProvinciaFiltro("","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.ProvinciaService.filtrar(this.modelo,this.elemento)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('nombre','nombre',TIPO_NO_EDITABLE)
      ];
    }
}
