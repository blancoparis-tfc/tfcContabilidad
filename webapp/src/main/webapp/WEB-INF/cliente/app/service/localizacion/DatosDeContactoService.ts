import {Injectable,ElementRef,ViewContainerRef} from '@angular/core';
import {Http,Response,URLSearchParams} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {DatosDeContacto,DatosDeContactoFiltro} from '../../model/localizacion/DatosDeContacto';

@Injectable()
export class DatosDeContactoService implements IGenericService<DatosDeContacto,number>{

  private url:string='localizacion/datosDeContacto';
  private genericService:GenericService<DatosDeContacto,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<DatosDeContacto,number>(http,this.url,mensajeria);
  }

  public crear(datosDeContacto:DatosDeContacto,elemento:ViewContainerRef):Observable<DatosDeContacto>{
      return this.genericService.crear(datosDeContacto,elemento);
  }

  public actualizar(datosDeContacto:DatosDeContacto,elemento:ViewContainerRef):Observable<DatosDeContacto>{
      return this.genericService.actualizar(datosDeContacto,elemento);
  }

  public actualizarLista(datosDeContacto:Array<DatosDeContacto>,elemento:ViewContainerRef):Observable<DatosDeContacto>{
      return this.genericService.actualizarLista(datosDeContacto,elemento);
  }

  public eliminar(id:number,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:number,elemento:ViewContainerRef):Observable<DatosDeContacto>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<DatosDeContacto[]>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:DatosDeContactoFiltro, elemento:ViewContainerRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('telefono',filtro.telefono);
  	  search.append('nombre',filtro.nombre);
  	  search.append('direccionDeCorreo',filtro.direccionDeCorreo);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
