import {LineaAsiento} from './lineaAsiento';
export class Asiento{

    constructor(
      public id:number,
      public descripcion:String,
      public lineas:Array<LineaAsiento>
    ){

    }
}
