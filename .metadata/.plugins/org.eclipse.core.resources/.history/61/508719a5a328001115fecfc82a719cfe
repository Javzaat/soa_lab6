package com.example.authsoap.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.authsoap.LoginUserRequest;
import com.example.authsoap.LoginUserResponse;
import com.example.authsoap.RegisterUserRequest;
import com.example.authsoap.RegisterUserResponse;
import com.example.authsoap.ValidateTokenRequest;
import com.example.authsoap.ValidateTokenResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/authsoap";

    // Simple in-memory storage
    private Map<String, String> users = new HashMap<>();
    private Map<String, String> tokens = new HashMap<>();


    // RegisterUser SOAP Operation
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RegisterUserRequest")
    @ResponsePayload
    public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {

        RegisterUserResponse response = new RegisterUserResponse();

        users.put(request.getUsername(), request.getPassword());

        response.setMessage("User registered successfully");

        return response;
    }


    // LoginUser SOAP Operation
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "LoginUserRequest")
    @ResponsePayload
    public LoginUserResponse loginUser(@RequestPayload LoginUserRequest request) {

        LoginUserResponse response = new LoginUserResponse();

        String storedPassword = users.get(request.getUsername());

        if (storedPassword != null && storedPassword.equals(request.getPassword())) {

            String token = UUID.randomUUID().toString();
            tokens.put(token, request.getUsername());

            response.setToken(token);
            response.setMessage("Login successful");

        } else {

            response.setToken("");
            response.setMessage("Invalid credentials");
        }

        return response;
    }


    // ValidateToken SOAP Operation
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ValidateTokenRequest")
    @ResponsePayload
    public ValidateTokenResponse validateToken(@RequestPayload ValidateTokenRequest request) {

        ValidateTokenResponse response = new ValidateTokenResponse();

        boolean valid = tokens.containsKey(request.getToken());

        response.setValid(valid);

        if (valid) {
            response.setMessage("Token valid");
        } else {
            response.setMessage("Token invalid");
        }

        return response;
    }
}