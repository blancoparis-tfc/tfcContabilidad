import {Component,ElementRef,DynamicComponentLoader,Injector,provide} from 'angular2/core';
import {Http,Response,Headers} from 'angular2/http';
import {CuentaContable} from '../../../model/contabilidad/cuentaContable';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {Mensajeria} from '../../../core/mensajeria/mensajeria';

import {CuentaContableService} from '../../../service/contabilidad/cuentaContableService';
@Component({
  selector:'contact',
  templateUrl:'app/component/pantallas/contabilidad/cuentaContable.component.html'
})
export class CuentaContableComponent{
  modelo:CuentaContable;

  constructor(private http:Http,
                private elemento:ElementRef
                ,private dialogo:DbpDialogo
                ,private mensajeria:Mensajeria
                ,private cargador: DynamicComponentLoader
                ,private injector: Injector
                ,private cuentaContableService:CuentaContableService
  ){
    this.modelo = new CuentaContable("","");
  }

  onSubmit(){
    console.info('Modelo',this.modelo);
    this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quiere crear la cuenta contable ('+this.modelo.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
        dialogoComponent.instance.cuandoOk.then((_)=>{
            this.cuentaContableService.crear(this.modelo,this.elemento).subscribe(res=>{
                this.mensajeria.success(this.elemento,'Se han guardado los datos correctamente.');
            },
            err=>{
                console.info('Procesar el error de segundas');
                this.mensajeria.error(this.elemento,''+err);
            });
          });
        dialogoComponent.instance.cuandoCancelar.then((_)=>{
            console.info(' No se va acrear la cuenta contable');
        });
    });
  }
}
