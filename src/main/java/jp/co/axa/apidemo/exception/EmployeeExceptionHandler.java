package jp.co.axa.apidemo.exception;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeExceptionHandler {

    /**
     * handleNoSuchElementException
     * a method to handle the no such element exception
     * when change a employee that does not exist
     * 
     * @param e
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(Exception e) {
        return new ResponseEntity<>("employee not found", HttpStatus.NOT_FOUND);
    }

    /**
     * handleEmptyResultDataAccessException
     * a method to handle the empty result data access exception
     * when delete a employee that does not exist
     * 
     * @param e
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        return new ResponseEntity<>("employee not found", HttpStatus.NOT_FOUND);
    }

}
