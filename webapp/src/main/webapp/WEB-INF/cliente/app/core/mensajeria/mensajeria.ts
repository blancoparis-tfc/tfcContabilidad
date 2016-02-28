import {Injectable} from 'angular2/core';

@Injectable()
export class Mensajeria{

  public success(mensaje:string){
      alert('success:'+mensaje);
  }

  public warning(mensaje:string){
      alert('warning'+mensaje);
  }

  public error(mensaje:string){
      alert('error:'+mensaje)
  }
  
}
