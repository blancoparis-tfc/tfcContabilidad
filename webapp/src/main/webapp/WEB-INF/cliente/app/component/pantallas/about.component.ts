import {Component,ElementRef,DynamicComponentLoader,Injector,provide,ViewContainerRef} from '@angular/core';
import {DbpDialogo,DbpDialogoAlertConf,DbpDialogoConfirmarConf,DbpDialogoBaseConf,DbpDialogoRef} from '../../core/modal/dialogo';
import {PaisService} from '../../service/localizacion/paisService';
import {PaisComponent} from './localizacion/pais.component';
import {ComunidadAutonomaComponent} from './localizacion/ComunidadAutonoma.component';
import {ProvinciaComponent} from './localizacion/Provincia.component';
import {MunicipioComponent} from './localizacion/Municipio.component';
import {DireccionComponent} from './localizacion/Direccion.component';
import {DatosDeContactoComponent} from "./localizacion/DatosDeContacto.component";
import {PersonaFisicaComponent} from './persona/PersonaFisica.component';

@Component({
  selector:'about',
  templateUrl:'app/component/pantallas/about.component.html'
})
export class AboutComponent{

  constructor(private elemento:ElementRef
              ,private dialogo:DbpDialogo
              ,private cargador: DynamicComponentLoader
              ,private paisService: PaisService
              ,private injector: Injector
              ,private viewContainerRef: ViewContainerRef){

              }

  abrirModal(){
    console.info('Abrir una modal');
    this.dialogo.alert(this.viewContainerRef,new DbpDialogoAlertConf('Mensajeasdaf a ag ggds hfdhsdfhsdghsgfhjsgfjgsfjgfjghjhdjhgjhgj h hg hjj djdhgj hdjd hfg jfghj fd jgj','Titulo')).then(dialogoRef=>{
      dialogoRef.cuandoCerramos.then((_)=>{console.info('Se ha cerrado el alert')});
      return dialogoRef;
    });
  }

  abrirConfirmar(){
  this.dialogo.confirmar(this.viewContainerRef,new DbpDialogoConfirmarConf('Mensaje de confirmar','Ejemplo de confirmar')).then(dialogoComponent=>{
      dialogoComponent.instance.cuandoOk.then((_)=>{console.info(' despues Ok 234');});
      dialogoComponent.instance.cuandoCancelar.then((_)=>{console.info(' despues cancelar 234');});
  });
}
abrirComponente(){
  var id=[provide(ParamId, {useValue:new ParamId(2)})]
  this.dialogo.abrir(EjemploFormularioComponent,this.viewContainerRef,new DbpDialogoBaseConf('Ejemplo para'),id).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}
abrirPais(){
  this.dialogo.abrir(PaisComponent,this.viewContainerRef,new DbpDialogoBaseConf('Paises')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}
abrirComunidad(){
  this.dialogo.abrir(ComunidadAutonomaComponent,this.viewContainerRef,new DbpDialogoBaseConf('Comunidades Autonomas')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });

}

abrirProvincia(){
  this.dialogo.abrir(ProvinciaComponent,this.viewContainerRef,new DbpDialogoBaseConf('Provincias')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}

abrirMunicipio(){
  this.dialogo.abrir(MunicipioComponent,this.viewContainerRef,new DbpDialogoBaseConf('Municipio')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}

abrirDireccion(){
  this.dialogo.abrir(DireccionComponent,this.viewContainerRef,new DbpDialogoBaseConf('Direccion')).then(dialogoRef=>{
    console.info('Componente de dentro',dialogoRef.componenteDentro);
    dialogoRef.cuandoCerramos.then((_)=>{
      console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
    });
    return dialogoRef;
  });
}

  abrirDatosDeContacto(){
    this.dialogo.abrir(DatosDeContactoComponent,this.viewContainerRef,new DbpDialogoBaseConf('Direccion')).then(dialogoRef=>{
      console.info('Componente de dentro',dialogoRef.componenteDentro);
      dialogoRef.cuandoCerramos.then((_)=>{
        console.info('Se cerro el componente',dialogoRef.componenteDentro.instance);
      });
      return dialogoRef;
    });
  }

  abrirPersonaFisica(){
    this.dialogo.abrir(PersonaFisicaComponent,this.viewContainerRef,new DbpDialogoBaseConf('Persona')).then(dialogoRef=>{
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
