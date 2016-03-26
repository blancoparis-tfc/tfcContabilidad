export const TIPO_NO_EDITABLE:String='noEditable';
export const TIPO_EDITABLE:String='Editable';
export enum TiposEditables{
    INPUT,SELECT
}

export enum Acciones{
    POPUP
}

export class Columna {
  private acciones:Array<Acciones>;
  constructor(
        public nombre:String
      , public descripcion:String
      , public tipo:String = TIPO_NO_EDITABLE
      , public tipoEditables:TiposEditables = TiposEditables.INPUT
      , public valores:Array<any> = []
      , public direccion:Number = 1
      ){
        this.acciones = [];
  }

  addAccion(accion:Acciones):Columna{
      this.acciones.push(accion);
      return this;
  }

}
