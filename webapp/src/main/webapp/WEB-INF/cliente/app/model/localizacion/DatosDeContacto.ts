import {Direccion} from './Direccion';

export class DatosDeContacto{

  constructor(
  	  public  id: number,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string,
      public  direccion:Direccion){}
}

export class DatosDeContactoFiltro{
  constructor(
  	  public  id: string,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string  ){}
}
