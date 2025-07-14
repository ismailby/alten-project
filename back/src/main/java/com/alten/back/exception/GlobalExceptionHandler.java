package com.alten.back.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alten.back.constants.Constants;



/**
 * Cette classe gere les exceptions globale.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorResponse.class)
    public ResponseEntity<Map<String, Object>> handleErrorResponse(ErrorResponse ex) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put(Constants.ERROR, ex.getErrorCode());
        errorDetails.put(Constants.MESSAGE, ex.getErrorMessage());
        errorDetails.put(Constants.STATUS, ex.getHttpStatus());
        return new ResponseEntity<>(errorDetails, ex.getHttpStatus());
    }

    
    //pour capturer l'exception synthaxe invalide
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidJson(HttpMessageNotReadableException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", Constants.ERROR_400);
        errorDetails.put("message", "Requête JSON invalide. Vérifiez la syntaxe.");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
   
}
