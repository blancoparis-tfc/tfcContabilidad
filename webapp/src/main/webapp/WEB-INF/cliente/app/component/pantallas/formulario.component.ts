import {Component} from 'angular2/core';
import {Http,Response} from 'angular2/http';
import {Formulario} from '../../model/formulario';
@Component({
  selector:'formularioDbp',
  templateUrl:'app/component/pantallas/formulario.component.html'
})
export class FormularioComponent{
  modelo:Formulario;

  constructor(private http:Http){
    this.modelo = new Formulario(null,null);
    http.get('./app/tipo.json')
    .map((res: Response)=> res.json())
    .subscribe(res =>{
       this.modelo.id=res.id;
       this.modelo.descripcion=res.descripcion;
     });
  }

  onSubmit(){
    console.info('Modelo',this.modelo);
  }
}
