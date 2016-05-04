<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
  <head>
      <title> Angular 2 (Hola mundo)</title>
      <!-- Establecer el parametro base  -->
      <c:url value="/" var="contexto" />
	  <base href="${contexto}">
      <!-- 1. Cargamos las librerias -->
      <script src="node_modules/es6-shim/es6-shim.min.js"></script>
      <script src="node_modules/zone.js/dist/zone.js"></script>
      <script src="node_modules/reflect-metadata/Reflect.js"></script>
      <script src="node_modules/systemjs/dist/system.src.js"></script>


      <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css">
      <link rel="stylesheet" href="resources/css/style.css">
      <link href="env/app.css"	rel="stylesheet">

      <!-- 2. Configirar SystemJS -->
      <script>
      var ctx ='${contexto}';
      </script>
      <script src="systemjs/system.config.js"></script>
      <script>
        	System.import('app').catch(function(err){ console.error(err);  });
      </script>
  </head>
  <body>
      <my-app> Cargando ...</my-app>
  </body>
</html>

