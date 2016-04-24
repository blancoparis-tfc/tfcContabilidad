import {Injectable,ElementRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {Provincia,ProvinciaFiltro} from '../../model/localizacion/Provincia';

@Injectable()
export class ProvinciaService implements IGenericService<Provincia,number>{

  private url:string='localizacion/provincia';
  private genericService:GenericService<Provincia,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<Provincia,number>(http,this.url,mensajeria);
  }

  public crear(provincia:Provincia,elemento:ElementRef):Observable<Response>{
      return this.genericService.crear(provincia,elemento);
  }

  public actualizar(provincia:Provincia,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizar(provincia,elemento);
  }

  public actualizarLista(provincia:Array<Provincia>,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizarLista(provincia,elemento);
  }

  public eliminar(id:number,elemento:ElementRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:number,elemento:ElementRef):Observable<Response>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ElementRef):Observable<Response>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:ProvinciaFiltro, elemento:ElementRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('nombre',filtro.nombre);
      search.append('comunidadAutonoma',filtro.comunidadAutonoma);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
