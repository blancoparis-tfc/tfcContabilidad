import {ElementRef,ViewContainerRef} from 'angular2/core';
import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {Mensajeria} from '../../core/mensajeria/mensajeria';

export interface IGenericService <E,ID>{
     crear(cuentaContable:E,elemento:ViewContainerRef):Observable<E>;
     eliminar(id:ID,elemento:ViewContainerRef):Observable<Response>;
     actualizarLista(cuentasContables:Array<E>,elemento:ViewContainerRef):Observable<E>;
     actualizar(cuentaContable:E,elemento:ViewContainerRef):Observable<E>;
     obtenerTodos(elemento:ViewContainerRef):Observable<E[]>;
}

export class GenericService <E,ID> implements GenericService<E,ID>{
    public headers;
    constructor(private http:Http,private url,private mensajeria:Mensajeria){
      this.headers  = new Headers({
          'Content-Type': 'application/json'
        });
    }

    public crear(cuentaContable:E,elemento:ViewContainerRef):Observable<E>{
      return this.http
        .post(this.url,JSON.stringify(cuentaContable),{headers:this.headers})
        .map(this.extractData)
        .catch((error)=>this.handleError(error,elemento));
    }

    public eliminar(id:ID,elemento:ViewContainerRef):Observable<Response>{
      return this.http
        .delete(this.parserUrlId(id),{headers:this.headers})
        .catch((error)=>this.handleError(error,elemento));
    }

    public actualizarLista(cuentasContables:Array<E>,elemento:ViewContainerRef):Observable<E>{
      return this.http
          .put(this.url+'/lista',JSON.stringify(cuentasContables),{headers:this.headers})
          .map(this.extractData)
          .catch((error)=>this.handleError(error,elemento));
    }

    public actualizar(cuentaContable:E,elemento:ViewContainerRef):Observable<E>{
      return this.http
          .put(this.url,JSON.stringify(cuentaContable),{headers:this.headers})
          .map(this.extractData)
          .catch((error)=>this.handleError(error,elemento));
    }

    public obtenerId(id:ID,elemento:ViewContainerRef):Observable<E>{
      return this.http
          .get(this.parserUrlId(id))
          .map(this.extractData)
          .catch((error)=>this.handleError(error,elemento));
    }

    public obtenerTodos(elemento:ViewContainerRef):Observable<E[]>{
      return this.http
          .get(this.url)
          .map(this.extractData)
          .catch((error)=>this.handleError(error,elemento));
    }

    private extractData(res: Response) {
      if (res.status < 200 || res.status >= 300) {
        throw new Error('Bad response status: ' + res.status);
      }
      let body = res.json();
      return body || { };
    }

    /*private handleErrorV2(error:Response){
      console.info('Error',error);
      let errMsg = error.text() || 'Server error';
      console.error(errMsg);
      this.mensajeria.consola('Prueba '+errMsg);
      return Observable.throw(errMsg);
    }*/

    public handleError (error:Response,elemento:ViewContainerRef){
      console.info('Error',error);
      let errMsg = error.text() || 'Server error';
      console.error(errMsg);
      this.mensajeria.error(elemento,'Prueba '+errMsg);
      return Observable.throw(errMsg);
    }

    private parserUrlId(id:ID){
      return this.url + "/"+id;
    }
}
