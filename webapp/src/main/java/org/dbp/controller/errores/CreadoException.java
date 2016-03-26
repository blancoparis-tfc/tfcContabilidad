package org.dbp.controller.errores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value=HttpStatus.CONFLICT,reason="El registro ya fue creado")
public class CreadoException extends RuntimeException{

}
