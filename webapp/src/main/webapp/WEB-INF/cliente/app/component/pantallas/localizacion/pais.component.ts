import {Component,ViewContainerRef} from 'angular2/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Pais,PaisFiltro} from '../../../model/localizacion/pais';
import {PaisService} from '../../../service/localizacion/paisService';

@Component({
  selector:'pais',
  templateUrl:'app/component/pantallas/localizacion/pais.component.html',
  directives:[Grid,AutoFocus]
})
export class PaisComponent{
    modelo:PaisFiltro;
    lineas:Array<Pais>;
    columnas:Array<Columna>;
    constructor(
      private dialogo:DbpDialogo
      ,private paisService:PaisService
      ,private viewContainerRef:ViewContainerRef
    ){
      this.modelo = new PaisFiltro("","","","","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.paisService.filtrar(this.modelo,this.viewContainerRef)
      //  .map(res => Object.assign(new Pais(),res.json()))
        .subscribe(res=>{
        //console.info('Pais',res.json());
        this.lineas=res.json();
      });
    }

    private getColumnas():Array<Columna>{
      return [
          new Columna('idAlfa2','idAlfa2',TIPO_NO_EDITABLE),
          new Columna('codAlfa3','codAlfa3',TIPO_NO_EDITABLE),
          new Columna('codNumerico','codNumerico',TIPO_NO_EDITABLE),
          new Columna('nombreComun','nombreComun',TIPO_NO_EDITABLE),
          new Columna('nombreOficial','nombreOficial',TIPO_NO_EDITABLE)
      ];
    }
}
