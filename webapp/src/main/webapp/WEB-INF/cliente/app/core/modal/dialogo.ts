import {
     DynamicComponentLoader,Directive,Host,SkipSelf,forwardRef,Injectable,
     ElementRef,Injector,provide,ViewEncapsulation,Component,ComponentRef,Provider
    ,ViewContainerRef
    ,ReflectiveInjector }
     from '@angular/core';
import {PromiseWrapper} from '../../../node_modules/@angular/core/src/facade/promise';
import {Type,isPresent} from '../../../node_modules/@angular/core/src/facade/lang';
import { KeyboardEvent} from '../../../node_modules/@angular/platform-browser/esm/src/facade/browser';
import {KeyCodes} from '../keycode';
import {AutoFocus} from '../directivas/autofocus.directive';


@Injectable()
export class DbpDialogo{

  constructor(private cargador: DynamicComponentLoader,private injector: Injector){}


  public alert(elemento:ViewContainerRef,dialogoConf:DbpDialogoAlertConf):Promise<DbpDialogoRef>{
      var dbpDialogoRef:DbpDialogoRef=new DbpDialogoRef();
      var ocultarPromesa=  this.cargador.loadNextToLocation(BlockDialogoComponent,elemento);
      return this.cargador.loadNextToLocation(DialogoAlertComponent,elemento).then(containerRef =>{
        this.fill(containerRef,dbpDialogoRef,dialogoConf,ocultarPromesa);
        dbpDialogoRef.contentRef=containerRef;
        return dbpDialogoRef;
      });
  }

  public confirmar(elemento:ViewContainerRef,dialogoConf:DbpDialogoConfirmarConf):Promise<ComponentRef<any>>{
      var dbpDialogoRef:DbpDialogoRef=new DbpDialogoRef()
      var ocultarPromesa=  this.cargador.loadNextToLocation(BlockDialogoComponent,elemento);
      return this.cargador.loadNextToLocation(DialogoConfirarComponent,elemento).then(containerRef =>{
        this.fill(containerRef,dbpDialogoRef,dialogoConf,ocultarPromesa);
        dbpDialogoRef.contentRef=containerRef;
        return containerRef;
      });
  }

  public abrir(tipo:Type,elemento:ViewContainerRef,dialogoConf:DbpDialogoBaseConf,providers: Array<Type | Provider | any[]> = []):Promise<DbpDialogoRef>{
    var dbpDialogoRef:DbpDialogoRef=new DbpDialogoRef();
    var ocultarPromesa=  this.cargador.loadNextToLocation(BlockDialogoComponent,elemento);
    providers.push(provide(DbpDialogoRef, {useValue: dbpDialogoRef}))
    var bindings =  ReflectiveInjector.resolve(providers);
    return this.cargador.loadNextToLocation(DialogoComponenteComponent,elemento,bindings).then(containerRef =>{
      this.fill(containerRef,dbpDialogoRef,dialogoConf,ocultarPromesa);
      if(isPresent(tipo)){
        return this.cargador.loadNextToLocation(tipo,dbpDialogoRef.elementRefContenedor,bindings).then(contentRef=>{
            dbpDialogoRef.cuandoCerramos.then((_)=>{contentRef.destroy();})
            dbpDialogoRef.componenteDentro=contentRef;
            dbpDialogoRef.contentRef=containerRef;
            return dbpDialogoRef;
        });
      }else{
          dbpDialogoRef.contentRef=containerRef;
          return dbpDialogoRef;
      }
    });
  }

  private fill(containerRef:ComponentRef<any>,dbpDialogoRef:DbpDialogoRef,dialogoConf:DbpDialogoBaseConf,ocultarPromesa:Promise<ComponentRef<any>>){
    containerRef.instance.configuacion=dialogoConf;
    containerRef.instance.dbpDialogoRef=dbpDialogoRef;
    ocultarPromesa.then(ocultarRef=>{
      dbpDialogoRef.cuandoCerramos.then((_)=>{
          console.info('destruir el componente');
          ocultarRef.destroy();
        })
    });

  }

}
/**
* Es la referencia a un dialogo abierto.
**/
export class DbpDialogoRef{

    _contentRef:ComponentRef<any>; // Representa al componente de la ventana.
    componenteDentro:ComponentRef<any>; // Representa al componente que hay dentro de la ventana.
    elementRefContenedor: ViewContainerRef; // Es elemento ref del contenedor de la ventana modal.
    private isCerrado:Boolean; // Nos indica se venta se ha cerrado.
    contentRefDeferred:any; // Lo usaremos, para sincronizar los datos.
    cuandoCerramosDeferred:any; // Se lanzara cuando se cierre la ventana

    constructor(){
      this._contentRef = null;
      this.isCerrado = false;
      this.contentRefDeferred=PromiseWrapper.completer();
      this.cuandoCerramosDeferred=PromiseWrapper.completer();
    }

    set contentRef(valor:ComponentRef<any>){
        this._contentRef = valor;
        this.contentRefDeferred.resolve(valor);
    }

    get cuandoCerramos():Promise<any>{
      return this.cuandoCerramosDeferred.promise;
    }

    cerrar(resultado: any = null){
      this.contentRefDeferred.promise.then((_)=>{
        if(!this.isCerrado){
          this.isCerrado=true;
          this._contentRef.destroy(); // Esto cerrar la ventana modal.
          this.cuandoCerramosDeferred.resolve(resultado);
        }
      });
    }
}
export class DbpDialogoBaseConf{
  constructor(public titulo:String=''){}
}
export class DbpDialogoAlertConf extends DbpDialogoBaseConf{
  constructor(public mensaje:String,public titulo:String){super(titulo)}
}

export class DbpDialogoConfirmarConf extends DbpDialogoBaseConf{
  constructor(public mensaje:String = '',public titulo:String,
    public botonOk:String ='Ok',public botonCancelar:String ='Candelar' ){super(titulo) }
}

class AbstractDialogoComponent{
  public dbpDialogoRef:DbpDialogoRef;
  cerrarModal(){
      this.dbpDialogoRef.cerrar();
  }
  documentKeypress(event:KeyboardEvent){
    if (event.keyCode == KeyCodes.ESCAPE) {
      this.dbpDialogoRef.cerrar();
    }
  }
}

@Component({
  selector:'dbp-dialogo-alert',
  templateUrl:'app/core/modal/dialogo.alert.html',
  host:{'(body:keydown)':'documentKeypress($event)'}
})
export class DialogoAlertComponent extends AbstractDialogoComponent{
  public configuacion:DbpDialogoAlertConf;
}

@Component({
  selector:'dbp-dialogo-confirmar',
  templateUrl:'app/core/modal/dialogo.confirmar.html',
  host:{'(body:keydown)':'documentKeypress($event)'},
  directives:[AutoFocus]
})
export class DialogoConfirarComponent extends AbstractDialogoComponent{

  public configuacion:DbpDialogoConfirmarConf;
  public okDeferred:any;
  public cancelarDeferred:any;

  constructor(){
    super();
    this.okDeferred=PromiseWrapper.completer();
    this.cancelarDeferred=PromiseWrapper.completer();
  }

  get cuandoOk():Promise<any>{return this.okDeferred.promise;}

  get cuandoCancelar():Promise<any>{return this.cancelarDeferred.promise;}

  procesarOk(elemento=null){
      this.dbpDialogoRef.cuandoCerramos.then((_)=>{this.okDeferred.resolve(elemento);});
      this.dbpDialogoRef.cerrar();
  }
  procesarCancelar(elemento=null){
      this.dbpDialogoRef.cuandoCerramos.then((_)=>{this.cancelarDeferred.resolve(elemento);});
      this.dbpDialogoRef.cerrar();
  }
}
// TODO: Usamos la directiva, para pasarle el elementRef, superior al contenedor para poder inyectar algo. Lo suyo seria poder obtenerlo a traves del framework.
@Directive({
  selector: 'componente',
})
class MdDialogContent {
  constructor(dbpDialogo:DbpDialogoRef, elementRef: ViewContainerRef) {
    dbpDialogo.elementRefContenedor=elementRef;
  }
}

@Component({
  selector:'dbp-dialogo-component',
  templateUrl:'app/core/modal/dialogo.componente.html',
  directives:[MdDialogContent],
  host:{'(body:keydown)':'documentKeypress($event)'}
})
export class DialogoComponenteComponent extends AbstractDialogoComponent{
  public contentRef:ElementRef;
  constructor(dbpDialogo:DbpDialogoRef){
    super();
    this.contentRef=null;
  }
}

@Component({
  selector:'dbp-dialogo-block',
  template:'<div class="modal-backdrop fade in" ></div>'
})
export class BlockDialogoComponent{}
