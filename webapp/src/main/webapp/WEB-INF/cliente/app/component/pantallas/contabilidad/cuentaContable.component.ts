import {Component,ElementRef,DynamicComponentLoader,Injector,provide} from 'angular2/core';
import {Http,Response,Headers} from 'angular2/http';
import {CuentaContable} from '../../../model/contabilidad/cuentaContable';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';

@Component({
  selector:'contact',
  templateUrl:'app/component/pantallas/contabilidad/cuentaContable.component.html'
})
export class CuentaContableComponent{
  modelo:CuentaContable;

  constructor(private http:Http,
                private elemento:ElementRef
                ,private dialogo:DbpDialogo
                ,private cargador: DynamicComponentLoader
                ,private injector: Injector
  ){
    this.modelo = new CuentaContable(null,null);
    http.get('app/mock/contabilidad/cuentaContable.json')
    .map((res: Response)=> res.json())
    .subscribe(res =>{
       this.modelo.cuenta=res.clave;
       this.modelo.descripcion=res.descripcion;
     });
  }

  onSubmit(){
    console.info('Modelo',this.modelo);
    this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('¿Quiere crear la cuenta contable ('+this.modelo.cuenta+')?','Cuenta contable')).then(dialogoComponent=>{
        dialogoComponent.instance.cuandoOk.then((_)=>{
            console.info(' Se creara la cuenta contable');
            let headers = new Headers();
            headers.append('Content-Type', 'application/json');

            this.http.put('contabilidad/cuentaContable',JSON.stringify(this.modelo),{headers: headers})
            .map((res: Response)=> res.json())
            .subscribe(res =>{
                console.info('Los datos',res)
            });
          });
        dialogoComponent.instance.cuandoCancelar.then((_)=>{
            console.info(' No se va acrear la cuenta contable');
        });
    });
  }
}
