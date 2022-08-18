package com.andikscript.simpleusermongodb.security.jwt;

import com.andikscript.simpleusermongodb.model.error.Error;
import org.json.JSONObject;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.valueOf(new JSONObject(new Error(
                HttpStatus.UNAUTHORIZED,
                LocalDateTime.now(),
                "Error : Unauthorized",
                Collections.singletonList("Please enter valid Token")
        ))));
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error : Unauthorized");
    }
}
