import {Injectable,ElementRef,ViewContainerRef} from '@angular/core';
import {Http,Headers,Response} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {CuentaContable} from '../../model/contabilidad/cuentaContable';
import {GenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';

@Injectable()
export class CuentaContableService {
  private url:string="contabilidad/cuentaContable";
  private genericService:GenericService<CuentaContable,String>;
  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<CuentaContable,String>(http,this.url,mensajeria);
  }

  public crear(cuentaContable:CuentaContable,elemento:ViewContainerRef):Observable<CuentaContable>{
      return this.genericService.crear(cuentaContable,elemento);
  }

  public actualizar(cuentaContable:CuentaContable,elemento:ViewContainerRef):Observable<CuentaContable>{
      return this.genericService.actualizar(cuentaContable,elemento);
  }

  public actualizarLista(cuentasContables:Array<CuentaContable>,elemento:ViewContainerRef):Observable<CuentaContable>{
      return this.genericService.actualizarLista(cuentasContables,elemento);
  }

  public eliminar(id:String,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<CuentaContable[]>{
      return this.genericService.obtenerTodos(elemento);
  }

  public obtenerId(id:string,elemento:ViewContainerRef):Observable<CuentaContable>{
    return this.genericService.obtenerId(id,elemento);
  }

}
