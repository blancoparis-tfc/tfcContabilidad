import {Injectable,ViewContainerRef} from 'angular2/core';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {IGenericService} from '../../../service/core/GenericService';
import {Response} from 'angular2/http';

export enum Estado {
  CREAR,MODIFICAR
}


export class OperacionesUtils <E,ID>{

  constructor(private dialogo:DbpDialogo,
  private service:IGenericService<E,ID>,
  private viewContainerRef:ViewContainerRef){

  }

  crear(
          dialogoConf:DbpDialogoConfirmarConf,
          modelo:E,
          procesarResponse: (value:E) => void,
          pprocesarCancelar?:(_)=>void
              ){

                this.dialogo.confirmar(this.viewContainerRef,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.crear(modelo,this.viewContainerRef)
                          .subscribe((res)=>{
                            console.info('Res',res);
                            procesarResponse(res);
                          })
                        });
                        dialogoComponent.instance.cuandoCancelar.then(pprocesarCancelar);
                });
  }

  actualizar(
          dialogoConf:DbpDialogoConfirmarConf,modelo:E,
          procesarResponse: (value:E) => void,
          pprocesarCancelar?:(_)=>void
              ){
                this.dialogo.confirmar(this.viewContainerRef,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.actualizar(modelo,this.viewContainerRef)
                          .subscribe((res)=>{
                            procesarResponse(res);
                          })
                        });
                        dialogoComponent.instance.cuandoCancelar.then(pprocesarCancelar);
                });
  }

  eliminar(
          dialogoConf:DbpDialogoConfirmarConf,id:ID,
          procesarResponse: (value:Response) => void,
          pprocesarCancelar?:(_)=>void
              ){
                this.dialogo.confirmar(this.viewContainerRef,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.eliminar(id,this.viewContainerRef)
                          .subscribe((res)=>{
                            procesarResponse(res);
                          })
                        });
                        dialogoComponent.instance.cuandoCancelar.then(pprocesarCancelar);
                });
  }


}
