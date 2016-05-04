import {Injectable,ElementRef,Component,DynamicComponentLoader,ComponentRef,ViewContainerRef,ApplicationRef} from '@angular/core';
import {PromiseWrapper} from '../../../node_modules/@angular/core/src/facade/promise';
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

  private crearMensaje(elemento:ViewContainerRef, mensaje:string,clase:string){
    console.info('Inicio');
    this.notificacionHtml5(mensaje,clase);
    this.cargador.loadNextToLocation(DbpMensaje,elemento).then(containerRef=>{
        containerRef.instance.mensaje=mensaje;
        containerRef.instance.clase=clase;
        containerRef.instance.elemento=containerRef;
        setTimeout(()=>{containerRef.destroy();},this.intervalo);
    });
  }

  private notificacionHtml5(mensaje:string,titulo:string){
      if(!("Notification" in window)){
          console.info('El navegador no soporta las notificaciones de html 5');
      }else{
        let _Notification = window["Notification"];
        if(_Notification.permission === "granted"){
            var notification = new _Notification(mensaje);
        }else if (_Notification.permission !== 'denied') {
            _Notification.requestPermission(function (permission) {
            if (permission === "granted") {
              var notification = new _Notification(mensaje);
            }
          });
        }
      }
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
  elemento:ComponentRef<any>;
  cerrarMensaje(){
    console.info('Cerramos el elemento');
    this.elemento.destroy()
  }

}
