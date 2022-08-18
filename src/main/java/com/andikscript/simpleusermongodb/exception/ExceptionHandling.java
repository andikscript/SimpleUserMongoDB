package com.andikscript.simpleusermongodb.exception;


import com.andikscript.simpleusermongodb.handling.*;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.error.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FailedValueBody.class)
    public ResponseEntity<?> failedValueBody(FailedValueBody e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("Value not complete"));
    }

    @ExceptionHandler(UserAlready.class)
    public ResponseEntity<?> userAlready(UserAlready e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ResponseMessage("User already create"));
    }

    @ExceptionHandler(RefreshTokenExpired.class)
    public ResponseEntity<?> refreshTokenExpired(RefreshTokenExpired e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("Refresh token is expired"));
    }

    @ExceptionHandler(UserNotConfirmed.class)
    public ResponseEntity<?> userNotConfirmed(UserNotConfirmed e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("User unconfirmed"));
    }

    @ExceptionHandler(UserNotRegister.class)
    public ResponseEntity<?> userNotRegister(UserNotRegister e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("User not register or already confirmed"));
    }

    @ExceptionHandler(UserNotVerify.class)
    public ResponseEntity<?> userNotVerify(UserNotVerify e) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseMessage("User not verify"));
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> userNotFound(UserNotFound e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Error user not found",
                        Arrays.asList(e.getStackTrace())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> getError = new ArrayList<>();

        for (FieldError err : ex.getFieldErrors()) {
            getError.add(err.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Validate Error",
                        getError
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new Error(
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                        LocalDateTime.now(),
                        "Media Type Not Support",
                        ex.getSupportedMediaTypes()
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Http request not valid",
                        Arrays.asList(ex.getSupportedMethods())
                ));
    }
}
