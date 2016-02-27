import {Injectable} from 'angular2/core';
import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {CuentaContable} from '../../model/contabilidad/cuentaContable';
import {GenericService} from '../core/GenericService';

@Injectable()
export class CuentaContableService {
  private url="contabilidad/cuentaContable";
  private genericService:GenericService<CuentaContable,String>;
  constructor(private http:Http)  {
      this.genericService=new GenericService<CuentaContable,String>(http,this.url);
  }

  public crear(cuentaContable:CuentaContable):Observable<CuentaContable>{
    return this.genericService.crear(cuentaContable);
  }

}
