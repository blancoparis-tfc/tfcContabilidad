import 'rxjs/operator/map';
import 'rxjs/operator/mergeMap';
import 'rxjs/Rx';

import {bootstrap}    from 'angular2/platform/browser';
import {AppComponent} from './app.component';
import {ROUTER_PROVIDERS, LocationStrategy, HashLocationStrategy} from 'angular2/router';
import {provide,enableProdMode} from 'angular2/core';
import {HTTP_PROVIDERS} from 'angular2/http';
import {DbpDialogo,DbpDialogoRef} from './core/modal/dialogo';
import {Mensajeria} from './core/mensajeria/mensajeria';
import {CuentaContableService} from './service/contabilidad/cuentaContableService';
import {AsientoService} from './service/contabilidad/asientoService';
import {PaisService} from './service/localizacion/paisService';
//DbpDialogoRef

enableProdMode();
bootstrap(AppComponent,[
    , ROUTER_PROVIDERS // proveedor del modulo de router.
    , HTTP_PROVIDERS // proveedor del modulo de http.
    , provide(LocationStrategy, {useClass: HashLocationStrategy}) // La estrategia de cargar usaremos la #.
    , provide(DbpDialogoRef,{useValue:null})// Correción del error a la hora de cargar el objeto dinamicamente (Es el objeto que cargamos dinamicamente en una modal.).
    , DbpDialogo, Mensajeria
    , CuentaContableService, AsientoService
    , PaisService
    ]
);
