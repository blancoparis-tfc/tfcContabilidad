import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
export class GenericService <E,ID>{
    private headers;
    constructor(private http:Http,private url){
      this.headers  = new Headers({
          'Content-Type': 'application/json'
        });
    }

    public crear(cuentaContable:E):Observable<Response>{
      return this.http
        .post(this.url,JSON.stringify(cuentaContable),{headers:this.headers});
    }

}
