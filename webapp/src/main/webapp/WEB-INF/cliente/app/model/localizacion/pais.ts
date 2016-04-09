export class Pais{

  constructor(
      public idAlfa2:string,
      public codAlfa3:string,
      public codNumerico:number,
      public nombreComun:string,
      public nombreOficial:string
  ){}
}

export class PaisFiltro{
  constructor(
      public idAlfa2:string,
      public codAlfa3:string,
      public codNumerico:string,
      public nombreComun:string,
      public nombreOficial:string
  ){}
}
