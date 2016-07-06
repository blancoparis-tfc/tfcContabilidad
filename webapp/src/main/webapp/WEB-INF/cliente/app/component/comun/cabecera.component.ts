import {Component} from '@angular/core';
import {RouterLink} from '@angular/router-deprecated';
import {Location} from '@angular/common';
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
