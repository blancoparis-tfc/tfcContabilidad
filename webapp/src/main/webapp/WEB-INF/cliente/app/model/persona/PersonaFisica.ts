export class PersonaFisica{

  constructor(
  	  public  id: number,
  	  public  identificadorFiscal: string,
      public  tipoDeIdentificadorFiscal: string,
  	  public  nombre: string,
  	  public  apellidos: string  ){}
}

export class PersonaFisicaFiltro{
  constructor(
  	  public  id: string,
  	  public  identificadorFiscal: string,
  	  public  nombre: string,
  	  public  apellidos: string  ){}
}
