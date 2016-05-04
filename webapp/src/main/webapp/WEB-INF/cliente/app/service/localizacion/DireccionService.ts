import {Injectable,ElementRef,ViewContainerRef} from '@angular/core';
import {Http,Response,URLSearchParams} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {Direccion,DireccionFiltro} from '../../model/localizacion/Direccion';

@Injectable()
export class DireccionService implements IGenericService<Direccion,number>{

  private url:string='localizacion/direccion';
  private genericService:GenericService<Direccion,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<Direccion,number>(http,this.url,mensajeria);
  }

  public crear(direccion:Direccion,elemento:ViewContainerRef):Observable<Direccion>{
      return this.genericService.crear(direccion,elemento);
  }

  public actualizar(direccion:Direccion,elemento:ViewContainerRef):Observable<Direccion>{
      return this.genericService.actualizar(direccion,elemento);
  }

  public actualizarLista(direccion:Array<Direccion>,elemento:ViewContainerRef):Observable<Direccion>{
      return this.genericService.actualizarLista(direccion,elemento);
  }

  public eliminar(id:number,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:number,elemento:ViewContainerRef):Observable<Direccion>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<Direccion[]>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:DireccionFiltro, elemento:ViewContainerRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('direccion',filtro.direccion);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
