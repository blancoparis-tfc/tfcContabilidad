import {Component} from 'angular2/core';
import {RouterLink} from 'angular2/router';
import {Location} from 'angular2/platform/common';
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
