import {Injectable,ElementRef,Component,DynamicComponentLoader} from 'angular2/core';
import {PromiseWrapper,Promise} from 'angular2/src/facade/async';
@Injectable()
export class Mensajeria{

  private intervalo:number=10000;

  constructor(private cargador: DynamicComponentLoader){}

  public success(elemento:ElementRef,mensaje:string){
      this.cargador.loadNextToLocation(DbpMensajeSuccess,elemento).then(containerRef=>{
        containerRef.instance.mensaje=mensaje;
        setTimeout(()=>{containerRef.dispose();},this.intervalo);
      });
  }

  public warning(elemento:ElementRef,mensaje:string){
    this.cargador.loadNextToLocation(DbpMensajeWarning,elemento).then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      setTimeout(()=>{containerRef.dispose();},this.intervalo);
    });
  }

  public error(elemento:ElementRef,mensaje:string){
    this.cargador.loadNextToLocation(DbpMensajeError,elemento).then(containerRef=>{
      containerRef.instance.mensaje=mensaje;
      setTimeout(()=>{containerRef.dispose();},this.intervalo);
    });
  }
}

@Component({
  selector:'dbp-mensaje-success',
  template:`<div class="alert alert-success" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close" (click)="cerrarMensaje()"><span aria-hidden="true">&times;</span></button>
            {{mensaje}}
            </div>`
})
export class DbpMensajeSuccess{
  mensaje:String;
  cerrarMensaje(){}
}

@Component({
  selector:'dbp-mensaje-error',
  template:'<div class="alert alert-danger" role="alert">{{mensaje}}</div>'
})
export class DbpMensajeError{
  mensaje:String;
}

@Component({
  selector:'dbp-mensaje-warning',
  template:'<div class="alert alert-warning" role="alert">{{mensaje}}</div>'
})
export class DbpMensajeWarning{
  mensaje:String;
}
