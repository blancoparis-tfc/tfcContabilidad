export class Provincia{

  constructor(
  	  public  id: number,
  	  public  nombre: string  ){}
}

export class ProvinciaFiltro{
  constructor(
  	  public  id: string,
  	  public  nombre: string ,
      public comunidadAutonoma:string
    ){} 
}
