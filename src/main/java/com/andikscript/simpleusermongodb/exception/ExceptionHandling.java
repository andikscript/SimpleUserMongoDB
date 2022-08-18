package com.andikscript.simpleusermongodb.exception;


import com.andikscript.simpleusermongodb.handling.*;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import com.andikscript.simpleusermongodb.model.error.Error;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
                        "Error",
                        Arrays.asList("Error user not found")
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
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new Error(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        LocalDateTime.now(),
                        "Http Request Method Not Supported",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(new Error(
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                        LocalDateTime.now(),
                        "Http Media Type Not Accept",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Missing Path Variable",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Missing Servlet Request Parameter",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Servlet Request Binding",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Conversion Not Supported",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Type Mismatch",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Http Message Not Readable",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Http Message Not Writable",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Missing Servlet Request Part",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "Bind Exception",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new Error(
                        HttpStatus.BAD_REQUEST,
                        LocalDateTime.now(),
                        "No Handler Found",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return ResponseEntity
                .status(HttpStatus.REQUEST_TIMEOUT)
                .body(new Error(
                        HttpStatus.REQUEST_TIMEOUT,
                        LocalDateTime.now(),
                        "Async Request Timeout",
                        Collections.singletonList(ex.getMessage())
                ));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new Error(
                        HttpStatus.EXPECTATION_FAILED,
                        LocalDateTime.now(),
                        "Exception Internal",
                        Collections.singletonList(ex.getMessage())
                ));
    }
}
