package org.dbp.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.dbp.conf.aop.log.DbpLog;
import org.dbp.controller.json.EjemploJson;
import org.dbp.controller.json.EjemploJson.Resumen;
import org.dbp.controller.json.EjemploJson.Resumen2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

@Controller
@RequestMapping("inicio")
public class InicioController {

	private static Logger logger = LoggerFactory.getLogger(InicioController.class);
	@DbpLog
	@RequestMapping
	public String inicio(){
		logger.info("Metodo {}","inicio");
		return "inicio";
	}
	

	
	@RequestMapping("excepcion/{param}")
	public String excepcion(HttpServletRequest  request,@PathVariable(value="param") String param) throws Exception{
		throw new Exception("Error de prueba");
	}
	@DbpLog
	@RequestMapping(value="ejemploJson",produces = "application/json")
	public @ResponseBody EjemploJson ejemploJson(){
		return mockEjemplo();
	}

	@JsonView(Resumen.class)
	@DbpLog
	@RequestMapping(value="resumenJson",produces = "application/json")
	public @ResponseBody EjemploJson resumenJson(){
		return mockEjemplo();
	}
	
	@JsonView(Resumen2.class)
	@DbpLog
	@RequestMapping(value="resumen2Json",produces = "application/json")
	public @ResponseBody EjemploJson resumen2Json(){
		return mockEjemplo();
	}

	@DbpLog
	@RequestMapping(value="ejemploXml",produces = "application/xml")
	public @ResponseBody EjemploJson ejemploXml(){
		return mockEjemplo();
	}
	
	private EjemploJson mockEjemplo() {
		EjemploJson valdev=new EjemploJson();
		valdev.setNumero(1L);
		valdev.setCadena("cadena");
		valdev.setDescripcion("descripcion");
		valdev.setFecha(new Date());
		valdev.setFechaLocal(LocalDate.now());
		valdev.setFechaTime(LocalDateTime.now());
		return valdev;
	}
}
