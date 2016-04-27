import {Component,ViewContainerRef} from 'angular2/core';
import {Router} from 'angular2/router';
import {AsientoFiltro,ResumenAsiento} from '../../../model/contabilidad/asientoFiltro';
import {Asiento} from '../../../model/contabilidad/asiento';
import {AsientoService} from '../../../service/contabilidad/asientoService';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';
import {DbpDialogo,DbpDialogoBaseConf,DbpDialogoConfirmarConf} from '../../../core/modal/dialogo';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';


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
    operacionesAsiento:OperacionesUtils<Asiento,number>;
    constructor(
      private asientoService:AsientoService
     , private router:Router
     , private mensajeria:Mensajeria
     , dialogo:DbpDialogo
     , private viewContainerRef:ViewContainerRef){
      this.modelo = new AsientoFiltro('','','','');
      this.lineas = [];
      this.columnas =this.getColumnas();
      this.operacionesAsiento = new OperacionesUtils<Asiento,number>(dialogo,asientoService,this.viewContainerRef);
    }

    onSubmit(){
      console.info('Los nuevos datos ',this.modelo);
      this.asientoService.filtrar(this.modelo,this.viewContainerRef).subscribe(res=>{
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
        this.router.navigate(['/AsientoFicha',{id:fila.id}]);
    }

    crear(){
        this.router.navigate(['/AsientoFicha']);
    }

    eliminarLinea(elemento:any){
        var idx = this.lineas.indexOf(elemento);
        this.operacionesAsiento.eliminar(
          new DbpDialogoConfirmarConf('¿Ser va eliminar el asiento '+elemento.id+'?','Asiento'),elemento.id
          ,(res)=>{
            this.mensajeria.success(this.viewContainerRef,'Se ha eliminado el asiento ('+elemento.id+') correctamente.');
            this.lineas.splice(idx,1);
          });
    }

}
