package com.andikscript.simpleusermongodb.exception;


import com.andikscript.simpleusermongodb.handling.FailedValueBody;
import com.andikscript.simpleusermongodb.handling.RefreshTokenExpired;
import com.andikscript.simpleusermongodb.handling.UserAlready;
import com.andikscript.simpleusermongodb.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
}
