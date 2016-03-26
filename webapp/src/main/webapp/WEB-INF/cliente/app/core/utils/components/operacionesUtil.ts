import {Injectable,ElementRef} from 'angular2/core';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../../core/modal/dialogo';
import {IGenericService} from '../../../service/core/GenericService';
import {Response} from 'angular2/http';

export enum Estado {
  CREAR,MODIFICAR
}


export class OperacionesUtils <E,ID>{

  constructor(private dialogo:DbpDialogo,private elemento:ElementRef,
  private service:IGenericService<E,ID>){

  }

  crear(
          dialogoConf:DbpDialogoConfirmarConf,
          modelo:E,
          procesarResponse: (value:Response) => void,
          pprocesarCancelar?:(_)=>void
              ){

                this.dialogo.confirmar(this.elemento,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.crear(modelo,this.elemento)
                          .subscribe((res)=>{
                            procesarResponse(res);
                          })
                        });
                        dialogoComponent.instance.cuandoCancelar.then(pprocesarCancelar);
                });
  }

  actualizar(
          dialogoConf:DbpDialogoConfirmarConf,modelo:E,
          procesarResponse: (value:Response) => void,
          pprocesarCancelar?:(_)=>void
              ){
                this.dialogo.confirmar(this.elemento,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.actualizar(modelo,this.elemento)
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
                this.dialogo.confirmar(this.elemento,dialogoConf)
                    .then(dialogoComponent=>{
                        dialogoComponent.instance.cuandoOk.then((_)=>{
                          this.service.eliminar(id,this.elemento)
                          .subscribe((res)=>{
                            procesarResponse(res);
                          })
                        });
                        dialogoComponent.instance.cuandoCancelar.then(pprocesarCancelar);
                });
  }


}
