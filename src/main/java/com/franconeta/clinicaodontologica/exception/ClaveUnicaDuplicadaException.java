package com.franconeta.clinicaodontologica.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
// Para exceptions genericas de spring
public class ClaveUnicaDuplicadaException extends DuplicateKeyException {
     public ClaveUnicaDuplicadaException(String msg) {
          super(msg);
     }

     public ClaveUnicaDuplicadaException(String msg, Throwable cause) {
          super(msg, cause);
     }
}
