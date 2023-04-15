package com.franconeta.clinicaodontologica.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntidadNoEncontradaException extends EntityNotFoundException {
     public EntidadNoEncontradaException() {
     }

     public EntidadNoEncontradaException(Exception cause) {
          super(cause);
     }

     public EntidadNoEncontradaException(String message) {
          super(message);
     }

     public EntidadNoEncontradaException(String message, Exception cause) {
          super(message, cause);
     }
}
