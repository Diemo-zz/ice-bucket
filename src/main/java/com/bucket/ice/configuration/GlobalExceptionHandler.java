package com.bucket.ice.configuration;

import com.bucket.ice.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MissingRequestValueException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger Logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({DatabaseException.class})
    public ResponseEntity<Object> handleException(DatabaseException exception) {
        Logger.error("{}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unknown exception has occurred");
    }

    //@ExceptionHandler({Exception.class})
    //public ResponseEntity<Object> handleException(Exception exception) {
    //    Logger.error("{}", exception.getMessage());
    //    Logger.error("{}", exception.getClass());
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //            .body("An unknown exception has occurred exception");
    //}

    @ExceptionHandler({MissingRequestValueException.class})
    public ResponseEntity<Object> handleException(MissingRequestValueException exception) {
        return ResponseEntity.status(exception.getStatusCode()).body(exception.getMessage());
    }

}
