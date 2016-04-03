export class AsientoFiltro{
  constructor(
      public id:string,
      public descripcion:string,
      public cuenta:string,
      public concepto:string){}
}

export class ResumenAsiento{
  constructor(
      public id:number,
      public descripcion:string,
      public saldo:number
  ){}
}
