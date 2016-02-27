import {Injectable} from 'angular2/core';
import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {CuentaContable} from '../../model/contabilidad/cuentaContable';

@Injectable()
export class CuentaContableService{
  private url="contabilidad/cuentaContable";
  private headers;
  constructor(private http:Http)  {
      this.headers  = new Headers({
          'Content-Type': 'application/json'
        });
  }

  public crear(cuentaContable:CuentaContable):Observable<CuentaContable>{
    return this.http
          .put(this.url,JSON.stringify(cuentaContable),{headers:this.headers})
          .map((res:Response) => res.json());
  }

}
