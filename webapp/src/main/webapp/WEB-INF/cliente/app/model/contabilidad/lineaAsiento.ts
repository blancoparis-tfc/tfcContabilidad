import {CuentaContable} from './cuentaContable';
export class LineaAsiento{
    constructor(public id:number,
    public cuenta:CuentaContable,
    public tipoMovimientoContable:string,
    public importe:number,
    public concepto:string){

    }

    get cuentaContable():string{
      return this.cuenta.cuenta;
    }

    set cuentaContable(cuenta:string){
      this.cuenta.cuenta=cuenta;
    }
}
