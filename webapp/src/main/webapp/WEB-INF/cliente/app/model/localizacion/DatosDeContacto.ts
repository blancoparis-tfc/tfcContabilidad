export class DatosDeContacto{

  constructor(
  	  public  id: number,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string  ){}
}

export class DatosDeContactoFiltro{
  constructor(
  	  public  id: string,
  	  public  telefono: string,
  	  public  nombre: string,
  	  public  direccionDeCorreo: string  ){}
}