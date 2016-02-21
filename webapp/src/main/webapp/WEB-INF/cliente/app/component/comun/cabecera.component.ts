import {Component} from 'angular2/core';
import {RouterLink,Location} from 'angular2/router';
@Component({
  selector:'dpbCabecera',
  templateUrl:'app/component/comun/cabecera.component.html',
  directives:[RouterLink]
})
export class CabeceraComponent{

  constructor(private location:Location){}

  activeOpcionMenuClass(menu:string){
    return this.location.path().indexOf(menu) > -1;
  }
}
