import {Injectable,ElementRef} from 'angular2/core';
import {Http,Response,URLSearchParams} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Asiento} from '../../model/contabilidad/asiento';
import {AsientoFiltro} from '../../model/contabilidad/asientoFiltro';
import {Mensajeria} from '../../core/mensajeria/mensajeria';

@Injectable()
export class AsientoService implements IGenericService<Asiento,number>{
    private url:string='contabilidad/asiento';
    private genericService:GenericService<Asiento,number>;
    constructor(private http:Http,private mensajeria:Mensajeria)  {
        this.genericService=new GenericService<Asiento,number>(http,this.url,mensajeria);
    }

    public crear(asiento:Asiento,elemento:ElementRef):Observable<Response>{
        return this.genericService.crear(asiento,elemento);
    }

    public actualizar(asiento:Asiento,elemento:ElementRef):Observable<Response>{
        return this.genericService.actualizar(asiento,elemento);
    }

    public actualizarLista(asiento:Array<Asiento>,elemento:ElementRef):Observable<Response>{
        return this.genericService.actualizarLista(asiento,elemento);
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

    public filtrar(filtro:AsientoFiltro,elemento:ElementRef):Observable<Response>{
      var search:URLSearchParams=new URLSearchParams();
      search.append('id',filtro.id);
      search.append('descripcion',filtro.descripcion);
      search.append('cuenta',filtro.cuenta);
      search.append('concepto',filtro.concepto)
      return this.http
          .get(this.url+"/filtro",{search:search})
          .catch((error)=>{return this.genericService.handleError(error,elemento);});
    }

}
