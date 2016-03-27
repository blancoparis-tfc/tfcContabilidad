import {Component,Input,Output,EventEmitter,OnInit} from 'angular2/core';
import {Columna,TiposEditables} from './columna';
import {Ordenar} from './ordernar';
@Component({
    selector: 'dbp-grid',
    templateUrl:'app/component/comun/grid/grid.html'
})
export class Grid implements OnInit{
  @Input() columnas:Array<Columna>;
  @Input() filas:Array<any>;
  //@Input() crearNuevaLinea:()=>void;

  @Output() crearNuevaLinea= new EventEmitter();
  @Output() accion=new EventEmitter();
  @Output() eliminar = new EventEmitter();
  @Output() seleccionar = new EventEmitter();

  columnaActiva:Columna;

  private algoritmoOrdenar:Ordenar = new Ordenar();

  ngOnInit() {
      console.info('Entro en el grid');
      //this.crearNuevaLinea=()=>{};
  }

  onSeleccionar(item:any){
    console.info('Seleecionada esta fila',item);
    this.seleccionar.next(item);
  }

  onEliminar(item:any){
      this.eliminar.next(item);
  }

  onCambio(item:any){
      item.estado='MODIFICADO';
  }

  ordenar(columna:Columna){
    this.columnaActiva=columna;
    this.algoritmoOrdenar.ordernar(columna,this.filas);
  }

  esColumnaActiva(columna:Columna){
    return this.columnaActiva===columna;
  }

  esInput(columna:Columna){
    return columna.tipo==='Editable' && columna.tipoEditables == TiposEditables.INPUT;
  }

  esSelect(columna:Columna){
    return columna.tipo==='Editable' && columna.tipoEditables == TiposEditables.SELECT;
  }

  nuevaLinea(columna:Columna){
      console.info('Pulso el enter');
      this.crearNuevaLinea.next(columna);
  }

  accionF6(columna:Columna,item:any){
    let valor:[Columna,any];
    valor=[columna,item];
    console.info('Los datos 2',valor);
    this.accion.next(valor);
  }

}
