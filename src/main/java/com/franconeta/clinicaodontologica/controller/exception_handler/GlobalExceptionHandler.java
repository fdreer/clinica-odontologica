package com.franconeta.clinicaodontologica.controller.exception_handler;

//@RestControllerAdvice
public class GlobalExceptionHandler {
     // Para exceptions personalizadas
//     private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
//     @ExceptionHandler(EntidadNoEncontradaException.class)
//     protected ResponseEntity<EntidadNoEncontradaException> handleDuplicateKeyException(EntidadNoEncontradaException ex, WebRequest request) {
//          logger.error(ex.getMessage());
//          return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
//          return new ResponseEntity<>(ExceptionResponseDTO.builder()
//                  .timeStamp(LocalDateTime.now())
//                  .error("Not found")
//                  .status(400)
//                  .message(ex.getMessage())
//                  .build(), HttpStatus.BAD_REQUEST);
//     }

//     @ExceptionHandler(EntityNotFoundException.class)
//     protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
//          logger.error(ex.getMessage());
////          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//          return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
////          return new ResponseEntity<>(ExceptionResponseDTO.builder()
////                  .timeStamp(LocalDateTime.now())
////                  .error("Not found")
////                  .status(404)
////                  .message(ex.getMessage())
////                  .build(), HttpStatus.BAD_REQUEST);
//     }
}
