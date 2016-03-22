import {FilaGrid} from '../core/filaGrid';

export class CuentaContable extends FilaGrid{
  constructor(
    public cuenta:string
    ,public descripcion:string){
      super('LECTURA');
  }
}
