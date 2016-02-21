import {Component} from 'angular2/core';
import {RouteConfig,RouterOutlet,Location} from 'angular2/router';

import {CabeceraComponent} from './component/comun/cabecera.component';
import {PieComponent} from './component/comun/pie.component';
import {AboutComponent} from './component/pantallas/about.component';
import {ContactComponent} from './component/pantallas/contact.component';
import {FormularioComponent} from './component/pantallas/formulario.component';

@RouteConfig([
  {path: '/About', component: AboutComponent, as:'About'},
  {path: '/Contacto', component: ContactComponent,as:'Contacto'},
  {path: '/Formulario', component: FormularioComponent,as:'Formulario'}
])
@Component({
  selector:'my-app',
  templateUrl:'app/app.component.html',
  directives:[CabeceraComponent,RouterOutlet,PieComponent]
})
export class AppComponent{

  constructor(private location:Location){
     location.go('/About');
  }
};
