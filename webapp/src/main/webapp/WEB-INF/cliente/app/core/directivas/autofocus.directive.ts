import {Directive,ElementRef,OnInit} from 'angular2/core';
@Directive({
  selector:'[autofocus]'
})
export class AutoFocus implements OnInit{
    constructor(public el: ElementRef){  }
    ngOnInit(){
      this.el.nativeElement.focus();
    }
}
