import {Component,ViewContainerRef} from '@angular/core';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
import {Municipio,MunicipioFiltro} from '../../../model/localizacion/Municipio';
import {MunicipioService} from '../../../service/localizacion/MunicipioService';
import {ProvinciaComponent} from './Provincia.component';


@Component({
  selector:'Municipio',
  templateUrl:'app/component/pantallas/localizacion/Municipio.component.html',
  directives:[Grid,AutoFocus]
})
export class MunicipioComponent{
    modelo:MunicipioFiltro;
    seleccion:Municipio;
    lineas:Array<Municipio>;
    columnas:Array<Columna>;
    constructor(
       private dialogo:DbpDialogo
      ,private MunicipioService:MunicipioService
      ,private viewContainerRef:ViewContainerRef
      ,private dbpDialogoRef:DbpDialogoRef
    ){

      this.modelo = new MunicipioFiltro("","","","");
      this.lineas=[];
      this.columnas=this.getColumnas();
    }

    consultar(){
      this.MunicipioService.filtrar(this.modelo,this.viewContainerRef)
        .subscribe(res=>{
        this.lineas=res.json();
      });
    }

    seleccionar(fila:any){
        console.info('Seleccionar un elemento en la cuenta contable',fila);
        this.seleccion=fila;
        if(this.dbpDialogoRef!=null){
            this.dbpDialogoRef.cerrar();
        }
    }

    buscarProvincia(){
        this.dialogo.abrir(ProvinciaComponent,this.viewContainerRef,new DbpDialogoBaseConf('Provincia')).then(dialogoRef=>{
          dialogoRef.cuandoCerramos.then((_)=>{
              this.modelo.provincia=dialogoRef.componenteDentro.instance.seleccion.nombre;
          });
            return dialogoRef;
        });
    }

    private getColumnas():Array<Columna>{
      return [
			new Columna('id','id',TIPO_NO_EDITABLE),
			new Columna('municipio','municipio',TIPO_NO_EDITABLE)
      ];
    }
}
