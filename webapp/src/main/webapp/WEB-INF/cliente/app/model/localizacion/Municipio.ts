export class Municipio{

  constructor(
  	  public  id: number,
  	  public  municipio: string  ){}
}

export class MunicipioFiltro{
  constructor(
  	  public  id: string,
  	  public  municipio: string ,
      public  provincia: string ,
      public  comunidadAutonoma:string){}
}
