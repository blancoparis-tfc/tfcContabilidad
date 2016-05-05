import {Injectable,ElementRef,ViewContainerRef} from '@angular/core';
import {Http,Response,URLSearchParams} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Mensajeria} from '../../core/mensajeria/mensajeria';
import {PersonaFisica,PersonaFisicaFiltro} from '../../model/persona/PersonaFisica';

@Injectable()
export class PersonaFisicaService implements IGenericService<PersonaFisica,number>{

  private url:string='persona/personaFisica';
  private genericService:GenericService<PersonaFisica,number>;

  constructor(private http:Http,private mensajeria:Mensajeria)  {
      this.genericService=new GenericService<PersonaFisica,number>(http,this.url,mensajeria);
  }

  public crear(personaFisica:PersonaFisica,elemento:ViewContainerRef):Observable<PersonaFisica>{
      return this.genericService.crear(personaFisica,elemento);
  }

  public actualizar(personaFisica:PersonaFisica,elemento:ViewContainerRef):Observable<PersonaFisica>{
      return this.genericService.actualizar(personaFisica,elemento);
  }

  public actualizarLista(personaFisica:Array<PersonaFisica>,elemento:ViewContainerRef):Observable<PersonaFisica>{
      return this.genericService.actualizarLista(personaFisica,elemento);
  }

  public eliminar(id:number,elemento:ViewContainerRef):Observable<Response>{
      return this.genericService.eliminar(id,elemento);
  }

  public obtenerId(id:number,elemento:ViewContainerRef):Observable<PersonaFisica>{
    return this.genericService.obtenerId(id,elemento);
  }

  public obtenerTodos(elemento:ViewContainerRef):Observable<PersonaFisica[]>{
      return this.genericService.obtenerTodos(elemento);
  }

  public filtrar(filtro:PersonaFisicaFiltro, elemento:ViewContainerRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
  	  search.append('id',filtro.id);
  	  search.append('identificadorFiscal',filtro.identificadorFiscal);
  	  search.append('nombre',filtro.nombre);
  	  search.append('apellidos',filtro.apellidos);
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
  }

}