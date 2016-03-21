import {Component,Input,Output,EventEmitter,OnInit} from 'angular2/core';
import {Columna} from './columna';
import {Ordenar} from './ordernar';
@Component({
    selector: 'dbp-grid',
    templateUrl:'app/component/comun/grid/grid.html'
})
export class Grid implements OnInit{
  @Input() columnas:Array<Columna>;
  @Input() filas:Array<any>;

  @Output() eliminar = new EventEmitter();

  columnaActiva:Columna;

  private algoritmoOrdenar:Ordenar = new Ordenar();

  ngOnInit() {
      console.info('Entro en el grid');
      console.info('Eliminar:',this.eliminar.asObservable());
      console.info('dato',this.eliminar.observers);
      console.info('isUnsubcribed',this.eliminar.isUnsubscribed);
      console.info('Contador',this.eliminar.count);
  }

  onEliminar(item:any){
      console.info('Operacion de eliminar',item);
      this.eliminar.next(item);
  }

  ordenar(columna:Columna){
    this.columnaActiva=columna;
    this.algoritmoOrdenar.ordernar(columna,this.filas);
  }

  esColumnaActiva(columna:Columna){
    return this.columnaActiva===columna;
  }

}
