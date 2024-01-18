package fr.esgi.zonevaluator.rest;

import fr.esgi.zonevaluator.exception.ParametreManquantInvalideException;
import fr.esgi.zonevaluator.exception.PdfNonTrouveException;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainRestControllerAdvice {

    @ExceptionHandler(ParametreManquantInvalideException.class)
    public ResponseEntity<Object> handleParametreManquantException(ParametreManquantInvalideException e) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(PdfNonTrouveException.class)
    public ResponseEntity<Object> handlePdfNonTrouveException(PdfNonTrouveException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        jsonObject.put("status", httpStatus.value());
        jsonObject.put("error", httpStatus.getReasonPhrase());
        return ResponseEntity.status(httpStatus).body(jsonObject);

    }

}
