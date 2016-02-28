package org.dbp.controller.errores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT,reason="El registro ya existe")
public class DuplicadoException extends RuntimeException{

}
