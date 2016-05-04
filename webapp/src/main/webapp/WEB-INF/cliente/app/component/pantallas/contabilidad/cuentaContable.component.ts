import {Component,DynamicComponentLoader,Injector,provide,OnInit,OnDestroy,ViewContainerRef} from '@angular/core';
import {FormBuilder} from '@angular/common';
import {Response,Headers} from '@angular/http';
import {CuentaContable} from '../../../model/contabilidad/cuentaContable';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';

import {Columna,TIPO_EDITABLE,TIPO_NO_EDITABLE} from '../../comun/grid/columna';
import {Grid} from '../../comun/grid/grid';
import {AutoFocus} from '../../../core/directivas/autofocus.directive';

import {CuentaContableService} from '../../../service/contabilidad/cuentaContableService';
@Component({
  selector:'contact',
  templateUrl:'app/component/pantallas/contabilidad/cuentaContable.component.html',
  directives:[Grid,AutoFocus]
})
export class CuentaContableComponent implements OnInit,OnDestroy{
  modelo:CuentaContable;
  lineas:Array<CuentaContable>;
  columnas:Array<Columna>;
  pintarCabecera:boolean;
  constructor(

                private dialogo:DbpDialogo
                ,private mensajeria:Mensajeria
                ,private cargador: DynamicComponentLoader
                ,private injector: Injector
                ,private cuentaContableService:CuentaContableService
                ,private formBuilder:FormBuilder
                ,private dbpDialogoRef:DbpDialogoRef
                ,private viewContainerRef:ViewContainerRef
  ){
    console.info('construir');
    this.modelo = new CuentaContable("","");
    this.columnas=this.getColumnas();
    this.lineas = [];
    this.pintarCabecera = this.dbpDialogoRef==null;
  }

  ngOnInit() {
      console.info('ngOnInit');
      this.cuentaContableService.obtenerTodos(this.viewContainerRef).subscribe(res=>{
          this.lineas=res;
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
        this.cuentaContableService.actualizarLista(lineasActualizadas,this.viewContainerRef).subscribe(res=>{
            console.info('Se ha actualizado correctamente');
        });
  }


  private getColumnas():Array<Columna>{
    return [
        new Columna('cuenta','cuenta',TIPO_NO_EDITABLE),
        new Columna('descripcion','descripcion',TIPO_EDITABLE)
    ];
  }

  seleccionar(fila:any){
      console.info('Seleccionar un elemento en la cuenta contable',fila);
      this.modelo=fila;
      if(this.dbpDialogoRef!=null){
          this.dbpDialogoRef.cerrar();
      }
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
        this.dialogo.confirmar(this.viewContainerRef,new DbpDialogoConfirmarConf('¿Quieres eliminar la cuenta ('+elemento.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
            dialogoComponent.instance.cuandoOk.then((_)=>{
                this.cuentaContableService.eliminar(elemento.cuenta,this.viewContainerRef).subscribe(res=>{
                    this.lineas.splice(idx,1);
                });
            });
          });
      }
  }

  onSubmit(){
    console.info('Modelo',this.modelo);
    this.dialogo.confirmar(this.viewContainerRef,new DbpDialogoConfirmarConf('¿Quiere crear la cuenta contable ('+this.modelo.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
        dialogoComponent.instance.cuandoOk.then((_)=>{
            this.cuentaContableService.crear(this.modelo,this.viewContainerRef).subscribe(data=>{
                console.info('nos ha devuelto esto ', data);
                this.lineas.push(data);
                this.mensajeria.success(this.viewContainerRef,'Se han guardado los datos correctamente.');
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
