package com.franconeta.clinicaodontologica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TurnoOcupadoException extends Exception{
     public TurnoOcupadoException() {
     }

     public TurnoOcupadoException(String message) {
          super(message);
     }

     public TurnoOcupadoException(String message, Throwable cause) {
          super(message, cause);
     }

     public TurnoOcupadoException(Throwable cause) {
          super(cause);
     }
}
