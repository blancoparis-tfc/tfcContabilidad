import {Injectable,ElementRef} from 'angular2/core';
import {Http,Headers,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {CuentaContable} from '../../model/contabilidad/cuentaContable';
import {GenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';

@Injectable()
export class CuentaContableService {
  private url="contabilidad/cuentaContable";
  private genericService:GenericService<CuentaContable,String>;
  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<CuentaContable,String>(http,this.url,mensajeria);
  }

  public crear(cuentaContable:CuentaContable,elemento:ElementRef):Observable<Response>{
      return this.genericService.crear(cuentaContable,elemento);
  }

  public actualizar(cuentaContable:CuentaContable,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizar(cuentaContable,elemento);
  }

  public actualizarLista(cuentasContables:Array<CuentaContable>,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizarLista(cuentasContables,elemento);
  }

  public eliminar(id:String,elemento:ElementRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerTodos(elemento:ElementRef):Observable<Response>{
      return this.genericService.obtenerTodos(elemento);
  }


}
