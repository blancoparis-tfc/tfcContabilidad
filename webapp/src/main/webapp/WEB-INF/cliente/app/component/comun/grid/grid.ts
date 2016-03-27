import {Component,Input,Output,EventEmitter,OnInit,ElementRef} from 'angular2/core';
import {Columna,TiposEditables} from './columna';
import {Ordenar} from './ordernar';
import {DbpDialogo,DbpDialogoBaseConf} from '../../../core/modal/dialogo';
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

  constructor(private elemento:ElementRef
              ,private dialogo:DbpDialogo){

  }

  ngOnInit() {
      console.info('Entro en el grid');

  }

  onHelp(){
      console.info('Ayuda');
      //this.crearNuevaLinea=()=>{};
      this.dialogo.abrir(AyudaGridComponent,this.elemento,new DbpDialogoBaseConf('Teclas de acción')).then(dialogoRef=>{
          return dialogoRef;
      });
  }

  onSeleccionar(item:any){
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

@Component({
selector:'ayudaGrid',
template:`
<table class="table table-bordered">
  <thead>
    <tr>
      <th>Tecla</th>
      <th>Función</th>
    <tr>
  </thead>
  <tbody>
    <tr>
      <td>Ctrl+F1</td>
      <td>Nos muestra la ayuda contextual</td>
    </tr>
    <tr>
      <td>Ctrl+F6</td>
      <td>Se lanza una acción asociada al campo.</td>
    <tr>
    <tr>
      <td>Ctrl+Enter</td>
      <td>Añade una nueva linea</td>
    <tr>
    <tr>
      <td>Ctrl+Supr</td>
      <td>Borra la linea</td>
    </tr>
  </tbody>
</table>`
})
export class AyudaGridComponent{
  constructor(){}
}
