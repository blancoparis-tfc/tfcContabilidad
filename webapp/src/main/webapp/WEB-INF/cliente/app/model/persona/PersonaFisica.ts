import {DatosDeContacto} from '../localizacion/DatosDeContacto';
export class PersonaFisica{

  constructor(
  	  public  id: number,
      public  tipoDeIdentificadorFiscal: string,
  	  public  identificadorFiscal: string,
  	  public  nombre: string,
  	  public  apellidos: string,
      public  datosDeContacto: Array<DatosDeContacto>
     ){}
}

export class PersonaFisicaFiltro{
  constructor(
  	  public  id: string,
  	  public  identificadorFiscal: string,
  	  public  nombre: string,
  	  public  apellidos: string  ){}
}
