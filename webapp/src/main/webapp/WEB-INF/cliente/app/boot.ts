import 'rxjs/add/operator/map';
import 'rxjs/add/operator/delay';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/observable/interval';
import 'rxjs/add/observable/forkJoin';
import 'rxjs/add/operator/publishReplay';
import 'rxjs/add/operator/catch'


import {bootstrap}    from '@angular/platform-browser-dynamic';
import {AppComponent} from './app.component';
import {ROUTER_PROVIDERS} from '@angular/router-deprecated';
import {LocationStrategy,HashLocationStrategy} from '@angular/common/index'
import {provide,enableProdMode} from '@angular/core';
import {HTTP_PROVIDERS} from '@angular/http';

import {DbpDialogo,DbpDialogoRef} from './core/modal/dialogo';
import {Mensajeria} from './core/mensajeria/mensajeria';
import {CuentaContableService} from './service/contabilidad/cuentaContableService';
import {AsientoService} from './service/contabilidad/asientoService';
import {PaisService} from './service/localizacion/paisService';
import {ComunidadAutonomaService} from './service/localizacion/ComunidadAutonomaService';
import {ProvinciaService} from './service/localizacion/ProvinciaService';
import {MunicipioService} from './service/localizacion/MunicipioService';



//DbpDialogoRef

enableProdMode();
bootstrap(AppComponent,[
    , ROUTER_PROVIDERS // proveedor del modulo de router.
    , HTTP_PROVIDERS // proveedor del modulo de http.
    , provide(LocationStrategy, {useClass: HashLocationStrategy}) // La estrategia de cargar usaremos la #.
    , provide(DbpDialogoRef,{useValue:null})// Correción del error a la hora de cargar el objeto dinamicamente (Es el objeto que cargamos dinamicamente en una modal.).
    , DbpDialogo, Mensajeria
    , CuentaContableService, AsientoService
    , PaisService, ComunidadAutonomaService, ProvinciaService, MunicipioService

    ]
);
