import {Component,ElementRef,DynamicComponentLoader,Injector,provide} from 'angular2/core';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../core/modal/dialogo';
import {PaisService} from '../../service/localizacion/paisService';
import {PaisComponent} from './localizacion/pais.component';
import {ComunidadAutonomaComponent} from './localizacion/ComunidadAutonoma.component';

@Component({
  selector:'about',
  templateUrl:'app/component/pantallas/about.component.html'
})
export class AboutComponent{

  constructor(private elemento:ElementRef
              ,private dialogo:DbpDialogo
              ,private cargador: DynamicComponentLoader
              ,private paisService: PaisService
              ,private injector: Injector){

              }

  abrirModal(){
    console.info('Abrir una modal');
    this.dialogo.alert(this.elemento,new DbpDialogoAlertConf('Mensajeasdaf a ag ggds hfdhsdfhsdghsgfhjsgfjgsfjgfjghjhdjhgjhgj h hg hjj djdhgj hdjd hfg jfghj fd jgj','Titulo')).then(dialogoRef=>{
      dialogoRef.cuandoCerramos.then((_)=>{console.info('Se ha cerrado el alert')});
      return dialogoRef;
    });
  }

  abrirConfirmar(){
  this.dialogo.confirmar(this.elemento,new DbpDialogoConfirmarConf('Mensaje de confirmar','Ejemplo de confirmar')).then(dialogoComponent=>{
      dialogoComponent.instance.cuandoOk.then((_)=>{console.info(' despues Ok 234');});
      dialogoComponent.instance.cuandoCancelar.then((_)=>{console.info(' despues cancelar 234');});
  });
}
abrirComponente(){
  var id=[provide(ParamId, {useValue:new ParamId(2)})]
  this.dialogo.abrir(EjemploFormularioComponent,this.elemento,new DbpDialogoBaseConf('Ejemplo para'),id).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}
abrirPais(){
  this.dialogo.abrir(PaisComponent,this.elemento,new DbpDialogoBaseConf('Paises')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}
abrirComunidad(){
  this.dialogo.abrir(ComunidadAutonomaComponent,this.elemento,new DbpDialogoBaseConf('Comunidades Autonomas')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });

}
/*
abrirComponenteComplejo(){
  this.dialogo.abrir(AsientoComponent,this.elemento,new DbpDialogoBaseConf('Ejemplo para')).then(dialogoRef=>{
    return dialogoRef;
  });
}*/
}

export class ParamId{
constructor(public id:Number){}
}

@Component({
selector:'ejemplo-formulario',
templateUrl:'app/component/pantallas/formulario.html'
})
export class EjemploFormularioComponent{
public dato:String='antes';
constructor(id:ParamId,public dbpDialogoRef:DbpDialogoRef){
  console.info('El id',id,dbpDialogoRef);
  this.dato="eco"+id.id;
}

ok(){
  this.dbpDialogoRef.cerrar();
}


}
