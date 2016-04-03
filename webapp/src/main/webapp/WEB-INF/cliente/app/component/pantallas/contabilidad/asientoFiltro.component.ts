import {Component,ElementRef} from 'angular2/core';
import {Router} from 'angular2/router';
import {AsientoFiltro,ResumenAsiento} from '../../../model/contabilidad/asientoFiltro';
import {AsientoService} from '../../../service/contabilidad/asientoService';


import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';
@Component({
    selector:'asientoFiltro',
    templateUrl:'app/component/pantallas/contabilidad/asientoFiltro.component.html',
    directives:[AutoFocus,Grid]
})
export class AsientoFiltroComponent{
    modelo:AsientoFiltro;
    lineas:Array<ResumenAsiento>;
    columnas:Array<Columna>;
    constructor(private asientoService:AsientoService,private elemento:ElementRef
    ,private router:Router){
      this.modelo = new AsientoFiltro('','','','');
      this.lineas = [];
      this.columnas =this.getColumnas();
    }

    onSubmit(){
      console.info('Los nuevos datos ',this.modelo);
      this.asientoService.filtrar(this.modelo,this.elemento).subscribe(res=>{
          this.lineas=res.json();
      });
    }

    private getColumnas():Array<Columna>{
      return [
          new Columna('id','cuenta',TIPO_NO_EDITABLE),
          new Columna('descripcion','descripcion',TIPO_NO_EDITABLE),
          new Columna('saldo','saldo',TIPO_NO_EDITABLE)
      ];
    }

    seleccionar(fila:any){
        this.router.navigate(['/Asiento',{id:fila.id}]);
    }

    crear(){
        this.router.navigate(['/Asiento']);
    }
}
