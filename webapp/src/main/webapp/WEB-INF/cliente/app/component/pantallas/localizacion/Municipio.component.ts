import {Component,ElementRef,ViewContainerRef} from 'angular2/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Municipio,MunicipioFiltro} from '../../../model/localizacion/Municipio';
import {MunicipioService} from '../../../service/localizacion/MunicipioService';


@Component({
  selector:'Municipio',
  templateUrl:'app/component/pantallas/localizacion/Municipio.component.html',
  directives:[Grid,AutoFocus]
})
export class MunicipioComponent{
    modelo:MunicipioFiltro;
    lineas:Array<Municipio>;
    columnas:Array<Columna>;
    constructor(
      private elemento:ElementRef
      ,private dialogo:DbpDialogo
      ,private MunicipioService:MunicipioService
      ,private viewContainerRef:ViewContainerRef
    ){

      this.modelo = new MunicipioFiltro("","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.MunicipioService.filtrar(this.modelo,this.viewContainerRef)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('municipio','municipio',TIPO_NO_EDITABLE)
      ];
    }
}
