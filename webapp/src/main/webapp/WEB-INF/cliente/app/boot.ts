import 'rxjs/operator/map';
import 'rxjs/operator/mergeMap';
import 'rxjs/Rx';

import {bootstrap}    from 'angular2/platform/browser';
import {AppComponent} from './app.component';
import {ROUTER_PROVIDERS, LocationStrategy, HashLocationStrategy} from 'angular2/router';
import {provide} from 'angular2/core';
import {HTTP_PROVIDERS} from 'angular2/http';
import {DbpDialogo} from './core/modal/dialogo';
import {Mensajeria} from './core/mensajeria/mensajeria';
import {CuentaContableService} from './service/contabilidad/cuentaContableService';


bootstrap(AppComponent,[
    , ROUTER_PROVIDERS // proveedor del modulo de router.
    , HTTP_PROVIDERS // proveedor del modulo de http.
    , provide(LocationStrategy, {useClass: HashLocationStrategy}) // La estrategia de cargar usaremos la #.
    , DbpDialogo
    , CuentaContableService
    , Mensajeria
    ]
);
