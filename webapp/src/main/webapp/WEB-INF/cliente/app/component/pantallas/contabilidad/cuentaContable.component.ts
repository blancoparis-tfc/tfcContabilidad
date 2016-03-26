import {Component,ElementRef,DynamicComponentLoader,Injector,provide,OnInit,OnDestroy} from 'angular2/core';
import {Response,Headers} from 'angular2/http';
import {CuentaContable} from '../../../model/contabilidad/cuentaContable';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';

import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {Grid} from '../../comun/grid/grid';

import {CuentaContableService} from '../../../service/contabilidad/cuentaContableService';
@Component({
  selector:'contact',
  templateUrl:'app/component/pantallas/contabilidad/cuentaContable.component.html',
  directives:[Grid]
})
export class CuentaContableComponent implements OnInit,OnDestroy{
  modelo:CuentaContable;
  lineas:Array<CuentaContable>;
  columnas:Array<Columna>;
  constructor(
                 private elemento:ElementRef
                ,private dialogo:DbpDialogo
                ,private mensajeria:Mensajeria
                ,private cargador: DynamicComponentLoader
                ,private injector: Injector
                ,private cuentaContableService:CuentaContableService
  ){
    console.info('construir');
    this.modelo = new CuentaContable("","");
    this.columnas=this.getColumnas();
    this.lineas = [];
  }

  ngOnInit() {
      console.info('ngOnInit');
      this.cuentaContableService.obtenerTodos(this.elemento).subscribe(res=>{
          this.lineas=res.json();
      });
  }

  ngOnDestroy(){
      console.info('se va a destruir');
      this.actualizar();
  }

  private actualizar(){
        var lineasActualizadas:Array<CuentaContable>;
        lineasActualizadas=this.lineas.filter(elemento=>elemento.estado === 'MODIFICADO');
        console.info('Las lineas',lineasActualizadas);
        this.cuentaContableService.actualizarLista(lineasActualizadas,this.elemento).subscribe(res=>{
            console.info('Se ha actualizado correctamente');
        });
  }


  private getColumnas():Array<Columna>{
    return [
        new Columna('cuenta','cuenta',TIPO_NO_EDITABLE),
        new Columna('descripcion','descripcion',TIPO_EDITABLE)
    ];
  }

  consultar(){
      //TODO: Falta por asociarlo a un servicio.
      this.lineas.push(new CuentaContable('0001','p1'));
      this.lineas.push(new CuentaContable('0002','p2'));
      this.lineas.push(new CuentaContable('0003','p3'));
  }

  eliminar(elemento:any){
      var idx = this.lineas.indexOf(elemento);
      console.info('eliminar',elemento);
      console.info('posicion',idx);
      if(idx != -1){
        this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quieres eliminar la cuenta ('+elemento.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
            dialogoComponent.instance.cuandoOk.then((_)=>{
                this.cuentaContableService.eliminar(elemento.cuenta,this.elemento).subscribe(res=>{
                    this.lineas.splice(idx,1);
                });
            });
          });
      }
  }

  onSubmit(){
    console.info('Modelo',this.modelo);
    this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quiere crear la cuenta contable ('+this.modelo.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
        dialogoComponent.instance.cuandoOk.then((_)=>{
            this.cuentaContableService.crear(this.modelo,this.elemento).subscribe(res=>{
                this.lineas.push(res.json());
                console.info('nos ha devuelto esto ', res.json());
                this.mensajeria.success(this.elemento,'Se han guardado los datos correctamente.');
            },
            err=>{
                console.info('Procesar el error de segundas');
            });
          });
        dialogoComponent.instance.cuandoCancelar.then((_)=>{
            console.info(' No se va acrear la cuenta contable');
        });
    });
  }
}
