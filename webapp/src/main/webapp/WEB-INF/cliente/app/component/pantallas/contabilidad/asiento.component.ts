import {Component,ElementRef,ViewContainerRef} from 'angular2/core';
import {Response} from 'angular2/http';
import {Router,RouteParams} from 'angular2/router';
import {Asiento} from '../../../model/contabilidad/asiento';
import {AsientoService} from '../../../service/contabilidad/asientoService';
import {DbpDialogo,DbpDialogoBaseConf,DbpDialogoConfirmarConf} from '../../../core/modal/dialogo';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';
import {OperacionesUtils,Estado} from '../../../core/utils/components/operacionesUtil';
import {Grid} from '../../comun/grid/grid';
import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE,TiposEditables,Acciones} from '../../comun/grid/columna';
import {LineaAsiento} from '../../../model/contabilidad/lineaAsiento';
import {CuentaContable} from '../../../model/contabilidad/cuentaContable';
import {CuentaContableComponent} from './cuentaContable.component';

import {AutoFocus} from '../../../core/directivas/autofocus.directive';
@Component({
    selector:'asiento',
    templateUrl:'app/component/pantallas/contabilidad/asiento.component.html',
    directives:[Grid,AutoFocus]
})
export class AsientoComponent {

    modelo:Asiento;
    etiquetaEstado:string;
    estado:Estado;
    operacionesAsiento:OperacionesUtils<Asiento,number>;
    columnas:Array<Columna>;

    constructor(private asientoService:AsientoService, private elemento:ElementRef
               , private dialogo:DbpDialogo, private mensajeria:Mensajeria,private router:Router
               ,params:RouteParams
               ,private viewContainerRef:ViewContainerRef){

        var identificador:string=params.get("id");

        console.info('Id',identificador);
        this.transitarCrear();
        if(identificador!=null){
            this.asientoService.obtenerId(Number(identificador),this.viewContainerRef).subscribe(res=>{
                console.info('Cargar los datos',res);
                this.parser(res);
                this.transitarModificar();
            })
        }
        this.columnas=this.getColumnas();
        this.operacionesAsiento = new OperacionesUtils<Asiento,number>(dialogo,elemento,asientoService,this.viewContainerRef);
    }

    inicializarModelo():Asiento{
      return new Asiento(null,'',[]);
    }

    crearNuevaLinea(){
          this.modelo.lineas.push(new LineaAsiento(null,new CuentaContable('',''),'H',0,''));
    }

    accion(evento:[Columna,any]){
      if(evento[0].nombre==='cuentaContable'){
        this.dialogo.abrir(CuentaContableComponent,this.viewContainerRef,new DbpDialogoBaseConf('Cuenta contable')).then(dialogoRef=>{
          dialogoRef.cuandoCerramos.then((_)=>{
            evento[1].cuenta=dialogoRef.componenteDentro.instance.modelo;
          });
          return dialogoRef;
        });
      }
    }

    private getColumnas():Array<Columna>{
      return [
          new Columna('id','id',TIPO_NO_EDITABLE),
          new Columna('cuentaContable','cuentaContable',TIPO_EDITABLE).addAccion(Acciones.POPUP),
          new Columna('tipoMovimientoContable','tipoMovimientoContable',TIPO_EDITABLE,TiposEditables.SELECT,['H','D'],1),
          new Columna('importe','importe',TIPO_EDITABLE).addAccion(Acciones.POPUP),
          new Columna('concepto','concepto',TIPO_EDITABLE)
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
      this.operacionesAsiento.crear(
        new DbpDialogoConfirmarConf('¿Quiere crear un nuevo asiento?','Asiento'),this.modelo
        ,(res)=>{
          this.parser(res);
          this.mensajeria.success(this.viewContainerRef,'Se actualizado el asiento ('+res.json().id+') correctamente.');
          this.transitarModificar();
        });
    }

    modificar(){
      this.operacionesAsiento.actualizar(
        new DbpDialogoConfirmarConf('¿Quiere actualizar el asiento?','Asiento'),this.modelo
        ,(res)=>{
          this.parser(res);
          this.mensajeria.success(this.viewContainerRef,'Se actualizado el asiento ('+res.json().id+') correctamente.');
        });
    }

    parser(res:Response){
      // TODO: Apaño por al recuperar los objetos no los instancia al objeto que nos interesa.
      this.modelo=res.json();
      this.modelo.lineas=this.modelo.lineas.map((linea)=>new LineaAsiento(linea.id,linea.cuenta,linea.tipoMovimientoContable,linea.importe,linea.concepto));
    }

    eliminar(id:number){
      this.operacionesAsiento.eliminar(
        new DbpDialogoConfirmarConf('¿Ser va eliminar el asiento '+id+'?','Asiento'),id
        ,(res)=>{
          this.mensajeria.success(this.viewContainerRef,'Se ha eliminado el asiento ('+id+') correctamente.');
          this.transitarCrear();
        });
    }

    eliminarLinea(elemento:any){
        var idx = this.modelo.lineas.indexOf(elemento);
        console.info('eliminar',elemento);
        console.info('posicion',idx);
        this.modelo.lineas.splice(idx,1);
    }

    isModificar():boolean{
      return this.estado == Estado.MODIFICAR;
    }

    volver(){
      this.router.navigate(['/Asiento']);
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

}
