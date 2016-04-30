import {Injectable,ElementRef,ViewContainerRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {Municipio,MunicipioFiltro} from '../../model/localizacion/Municipio';

@Injectable()
export class MunicipioService implements IGenericService<Municipio,number>{

  private url:string='localizacion/municipio';
  private genericService:GenericService<Municipio,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<Municipio,number>(http,this.url,mensajeria);
  }

  public crear(municipio:Municipio,elemento:ViewContainerRef):Observable<Municipio>{
      return this.genericService.crear(municipio,elemento);
  }

  public actualizar(municipio:Municipio,elemento:ViewContainerRef):Observable<Municipio>{
      return this.genericService.actualizar(municipio,elemento);
  }

  public actualizarLista(municipio:Array<Municipio>,elemento:ViewContainerRef):Observable<Municipio>{
      return this.genericService.actualizarLista(municipio,elemento);
  }

  public eliminar(id:number,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:number,elemento:ViewContainerRef):Observable<Municipio>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<Municipio[]>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:MunicipioFiltro, elemento:ViewContainerRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('municipio',filtro.municipio);
      search.append('provincia',filtro.provincia);
      search.append('comunidadAutonoma',filtro.comunidadAutonoma);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
