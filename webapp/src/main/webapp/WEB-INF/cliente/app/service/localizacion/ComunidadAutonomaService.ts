import {Injectable,ElementRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {ComunidadAutonoma,ComunidadAutonomaFiltro} from '../../model/localizacion/ComunidadAutonoma';


@Injectable()
export class ComunidadAutonomaService implements IGenericService<ComunidadAutonoma,number>{

  private url:string='localizacion/comunidadAutonoma';
  private genericService:GenericService<ComunidadAutonoma,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<ComunidadAutonoma,number>(http,this.url,mensajeria);
  }

  public crear(comunidadAutonoma:ComunidadAutonoma,elemento:ElementRef):Observable<Response>{
      return this.genericService.crear(comunidadAutonoma,elemento);
  }

  public actualizar(comunidadAutonoma:ComunidadAutonoma,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizar(comunidadAutonoma,elemento);
  }

  public actualizarLista(comunidadAutonoma:Array<ComunidadAutonoma>,elemento:ElementRef):Observable<Response>{
      return this.genericService.actualizarLista(comunidadAutonoma,elemento);
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

  public filtrar(filtro:ComunidadAutonomaFiltro, elemento:ElementRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('nombre',filtro.nombre);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}
