import {Injectable,ElementRef,Component,DynamicComponentLoader,ComponentRef,ViewContainerRef} from 'angular2/core';
import {PromiseWrapper} from 'angular2/src/facade/promise';
@Injectable()
export class Mensajeria{

  private intervalo:number=10000;

  constructor(private cargador: DynamicComponentLoader){}

  public success(elemento:ViewContainerRef,mensaje:string){
      this.cargador.loadAsRoot(DbpMensaje,'#mensajeria',elemento.injector).then(containerRef=>{
        containerRef.instance.mensaje=mensaje;
        containerRef.instance.clase='alert-success';
        containerRef.instance.elemento=containerRef;
        setTimeout(()=>{containerRef.destroy();},this.intervalo);
      });
  }

  public warning(elemento:ViewContainerRef,mensaje:string){
    this.cargador.loadAsRoot(DbpMensaje,'#mensajeria',elemento.injector).then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      containerRef.instance.clase='alert-warning';
      containerRef.instance.elemento=containerRef;
      setTimeout(()=>{containerRef.destroy();},this.intervalo);
    });
  }


  public error(elemento:ViewContainerRef,mensaje:string){
    this.cargador.loadAsRoot(DbpMensaje,'#mensajeria',elemento.injector).then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      containerRef.instance.clase='alert-danger';
      containerRef.instance.elemento=containerRef;
      setTimeout(()=>{containerRef.destroy();},this.intervalo);
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
    this.elemento.destroy()
  }

}
