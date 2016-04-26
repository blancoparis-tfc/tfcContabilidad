import {Injectable,ElementRef,ViewContainerRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {Pais,PaisFiltro} from '../../model/localizacion/pais';

@Injectable()
export class PaisService implements IGenericService<Pais,string>{

  private url:string='localizacion/pais';
  private genericService:GenericService<Pais,string>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<Pais,string>(http,this.url,mensajeria);
  }

  public crear(asiento:Pais,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.crear(asiento,elemento);
  }

  public actualizar(asiento:Pais,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.actualizar(asiento,elemento);
  }

  public actualizarLista(asiento:Array<Pais>,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.actualizarLista(asiento,elemento);
  }

  public eliminar(id:string,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:string,elemento:ViewContainerRef):Observable<Response>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:PaisFiltro, elemento:ViewContainerRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
      search.append('idAlfa2',filtro.idAlfa2);
      search.append('codAlfa3',filtro.codAlfa3);
      search.append('codNumerico',filtro.codNumerico);
      search.append('nombreComun',filtro.nombreComun);
      search.append('nombreOficial' ,filtro.nombreOficial);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
