import {Injectable,ElementRef,Component,DynamicComponentLoader,ComponentRef,ViewContainerRef,ApplicationRef} from 'angular2/core';
import {PromiseWrapper} from 'angular2/src/facade/promise';
@Injectable()
export class Mensajeria{

  private intervalo:number=10000;

  constructor(private cargador: DynamicComponentLoader){}

  public success(elemento:ViewContainerRef,mensaje:string){
    this.crearMensaje(elemento,mensaje,'alert-success');
  }

  public warning(elemento:ViewContainerRef,mensaje:string){
    this.crearMensaje(elemento,mensaje,'alert-warning');
  }


  public error(elemento:ViewContainerRef,mensaje:string){
    this.crearMensaje(elemento,mensaje,'alert-danger');
  }

  private crearMensaje(elemento:ViewContainerRef, mensaje:string,clase:String){
    this.cargador.loadNextToLocation(DbpMensaje,elemento).then(containerRef=>{
        containerRef.instance.mensaje=mensaje;
        containerRef.instance.clase=clase;
        containerRef.instance.elemento=containerRef;
        setTimeout(()=>{containerRef.destroy();},this.intervalo);
    });
  }
}

@Component({
  selector:'dbp-mensaje-success',
  template:`<div class="alert {{clase}}"  role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close" (click)="cerrarMensaje()"><span aria-hidden="true">&times;</span></button>
            {{mensaje}} Texto
            </div>`
})
export class DbpMensaje{
  mensaje:String;
  clase:String;
  elemento:ComponentRef;
  cerrarMensaje(){
    console.info('Cerramos el elemento');
    this.elemento.destroy()
  }

}
