<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
  <head>
      <title> Angular 2 (Hola mundo)</title>
      <!-- Establecer el parametro base  -->
      <c:url value="/" var="contexto" />
	  <base href="${contexto}">
      <!-- 1. Cargamos las librerias -->
      <script src="node_modules/angular2/bundles/angular2-polyfills.min.js"></script>
      <script src="node_modules/es6-shim/es6-shim.min.js"></script>
      <script src="node_modules/systemjs/dist/system.src.js"></script>
      <script src="node_modules/rxjs/bundles/Rx.min.js"></script>
      <script src="node_modules/angular2/bundles/angular2.js"></script>
      <script src="node_modules/angular2/bundles/router.min.js"></script>
      <script src="node_modules/angular2/bundles/http.min.js"></script>


      <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
      <link rel="stylesheet" href="resources/css/style.css">
      <link href="env/app.css"	rel="stylesheet">

      <!-- 2. Configirar SystemJS -->
      <script>
        System.config({
          packages: {
                  '${contexto}/app': {defaultExtension: 'js' },
                  '${contexto}/node_modules/rxjs': { defaultExtension: 'js' }
                },
                paths: {
                    'rxjs/observable/*' : '${contexto}/node_modules/rxjs/observable/*.js',
                    'rxjs/operator/*': '${contexto}/node_modules/rxjs/add/operator/*.js',
                    'rxjs/*': '${contexto}/node_modules/rxjs/*.js'
                }
          });
        System.import('${contexto}/app/boot')
              .then(null, console.error.bind(console));
      </script>
  </head>
  <body>
      <my-app> Cargando ...</my-app>
  </body>
</html>

