import {Direccion} from './Direccion';

export class DatosDeContacto{

  constructor(
  	  public  id: number,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string,
      public  direccion:Direccion){}

      get direccionDescripcion():string{
        return this.direccion.direccion;
      }

      set direccionDescripcion(direccion:string){
        this.direccion.direccion=direccion;
      }

      get municipio():string{
        return this.direccion.municipio.municipio;
      }

      set municipio(municipio:string){
        this.direccion.municipio.municipio=municipio;
      }

}

export class DatosDeContactoFiltro{
  constructor(
  	  public  id: string,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string  ){}
}
