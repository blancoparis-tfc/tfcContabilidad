import {ElementRef} from 'angular2/core';
import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {Mensajeria} from '../../core/mensajeria/mensajeria';

export class GenericService <E,ID>{
    private headers;
    private elemento:ElementRef;
    constructor(private http:Http,private url,private mensajeria:Mensajeria){
      this.headers  = new Headers({
          'Content-Type': 'application/json'
        });
    }

    public crear(cuentaContable:E,elemento:ElementRef):Observable<Response>{
      return this.http
        .post(this.url,JSON.stringify(cuentaContable),{headers:this.headers})
        .catch((error)=>{return this.handleError(error,elemento);});
    }

    public eliminar(id:ID,elemento:ElementRef):Observable<Response>{
      return this.http
        .delete(this.parserUrlId(id),{headers:this.headers})
        .catch((error)=>{return this.handleError(error,elemento);});
    }

    public actualizarLista(cuentasContables:Array<E>,elemento:ElementRef):Observable<Response>{
      return this.http
          .put(this.url+'/lista',JSON.stringify(cuentasContables),{headers:this.headers})
          .catch((error)=>{return this.handleError(error,elemento);});
    }

    public actualizar(cuentaContable:E,elemento:ElementRef):Observable<Response>{
      return this.http
          .put(this.url,JSON.stringify(cuentaContable),{headers:this.headers})
          .catch((error)=>{return this.handleError(error,elemento);});
    }


    public obtenerTodos(elemento:ElementRef):Observable<Response>{
      return this.http
          .get(this.url)
          .catch((error)=>{return this.handleError(error,elemento);});
    }

    private handleError (error:Response,elemento:ElementRef){
      console.info('Error de generico ',error);
      this.mensajeria.error(elemento,error.text());
      return Observable.throw(error);
    }

    private parserUrlId(id:ID){
      return this.url + "/"+id;
    }
}
