package com.modulodecompras.modulo.Resources.excetion;

import com.modulodecompras.modulo.Services.NotFoundExcecion.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExcetionManipulator {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        StardError err = new StardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resources noot found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
