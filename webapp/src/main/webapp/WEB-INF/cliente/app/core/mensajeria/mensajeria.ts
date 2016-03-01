import {Injectable,ElementRef,Component,DynamicComponentLoader,ComponentRef} from 'angular2/core';
import {PromiseWrapper,Promise} from 'angular2/src/facade/async';
@Injectable()
export class Mensajeria{

  private intervalo:number=10000;

  constructor(private cargador: DynamicComponentLoader){}

  public success(elemento:ElementRef,mensaje:string){
      this.cargador.loadIntoLocation(DbpMensaje,elemento,'mensajeria').then(containerRef=>{
        containerRef.instance.mensaje=mensaje;
        containerRef.instance.clase='alert-success';
        containerRef.instance.elemento=containerRef;
        setTimeout(()=>{containerRef.dispose();},this.intervalo);
      });
  }

  public warning(elemento:ElementRef,mensaje:string){
    this.cargador.loadIntoLocation(DbpMensaje,elemento,'mensajeria').then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      containerRef.instance.clase='alert-warning';
      containerRef.instance.elemento=containerRef;
      setTimeout(()=>{containerRef.dispose();},this.intervalo);
    });
  }

  public error(elemento:ElementRef,mensaje:string){
    this.cargador.loadIntoLocation(DbpMensaje,elemento,'mensajeria').then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      containerRef.instance.clase='alert-danger';
      containerRef.instance.elemento=containerRef;
      setTimeout(()=>{containerRef.dispose();},this.intervalo);
    });
  }
}

@Component({
  selector:'dbp-mensaje-success',
  template:`<div class="alert {{clase}}"  role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close" (click)="cerrarMensaje()"><span aria-hidden="true">&times;</span></button>
            {{mensaje}}
            </div>`
})
export class DbpMensaje{
  mensaje:String;
  clase:String;
  elemento:ComponentRef;
  cerrarMensaje(){
    console.info('Cerramos el elemento');
    this.elemento.dispose();
  }

}
