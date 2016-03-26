import {Injectable,ElementRef} from 'angular2/core';
import {Http,Response} from 'angular2/http';
import {Observable} from 'rxjs/Observable';
import {GenericService,IGenericService} from '../core/GenericService';
import {Asiento} from '../../model/contabilidad/asiento';
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

    public obtenerTodos(elemento:ElementRef):Observable<Response>{
        return this.genericService.obtenerTodos(elemento);
    }


}
