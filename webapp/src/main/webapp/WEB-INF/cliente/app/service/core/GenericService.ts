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
        .catch(this.handleError);
    }

    private handleError (error:Response){
      console.info('Error de generico ',error);
      this.mensajeria.error(this.elemento,error.statusText)
      return Observable.throw(error);
    }

}
