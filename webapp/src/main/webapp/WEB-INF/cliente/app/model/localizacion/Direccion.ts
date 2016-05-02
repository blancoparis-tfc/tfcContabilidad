import {Municipio} from './Municipio';

export class Direccion{

  constructor(
  	  public  id: number,
  	  public  direccion: string,
      public  municipio: Municipio  ){}


}

export class DireccionFiltro{
  constructor(
  	  public  id: string,
  	  public  direccion: string  ){}
}
