import {Component} from '@angular/core';
import {RouteConfig,RouterOutlet} from '@angular/router-deprecated';
import {Location} from '@angular/common/index';
import {CabeceraComponent} from './component/comun/cabecera.component';
import {PieComponent} from './component/comun/pie.component';
import {AboutComponent} from './component/pantallas/about.component';
import {ContactComponent} from './component/pantallas/contact.component';
import {FormularioComponent} from './component/pantallas/formulario.component';
import {CuentaContableComponent} from "./component/pantallas/contabilidad/cuentaContable.component";
import {AsientoComponent} from './component/pantallas/contabilidad/asiento.component';
import {AsientoFiltroComponent} from './component/pantallas/contabilidad/asientoFiltro.component';
import {PersonaFisicaComponent} from './component/pantallas/persona/PersonaFisica.component';
import {PersonaFisicaFichaComponent} from './component/pantallas/persona/PersonaFisicaFicha.component';
import {InicioComponent} from './component/pantallas/inicio.component';


@RouteConfig([
  {path: '/About', component: AboutComponent, as:'About'},
  {path: '/Contacto', component: ContactComponent,as:'Contacto'},
  {path: '/Formulario', component: FormularioComponent,as:'Formulario'},
  {path: '/CuentaContable', component: CuentaContableComponent,as:'CuentaContable'},
  {path: '/AsientoFicha', component: AsientoComponent, as:"AsientoFicha"},
  {path: '/Asiento', component: AsientoFiltroComponent, as:"Asiento"},
  {path: '/PersonaFisica', component:PersonaFisicaComponent, as:"PersonaFisica"},
  {path: '/PersonaFisicaFicha', component:PersonaFisicaFichaComponent, as:"PersonaFisicaFicha"},
  {path: '/Inicio', component:InicioComponent, as:"Inicio"}
])

@Component({
  selector:'my-app',
  templateUrl:'app/app.component.html',
  directives:[CabeceraComponent,RouterOutlet,PieComponent]
})
export class AppComponent{
  constructor(private location:Location){
     location.go('/Inicio');
  }
};
