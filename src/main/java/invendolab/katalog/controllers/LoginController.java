/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.controllers;

import invendolab.katalog.helpers.Response;
import invendolab.katalog.models.Consumer;
import invendolab.katalog.models.responses.LoginResponse;
import invendolab.katalog.repositories.ConsumerRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(value="login", description="Authentication for consumers")
public class LoginController {

    @Autowired
    ConsumerRepository repository;

    private Response response = new Response();
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @ApiOperation(value = "Login with email/username and password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully logged-in"),
            @ApiResponse(code = 401, message = "E-mail/username or password is wrong.")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam("emailOrPassword") String emailOrUsername, @RequestParam("password") String password) {

        Consumer userList = repository.findByEmailOrUserName(emailOrUsername, emailOrUsername);
        boolean success = false;
        Consumer user = new Consumer();

        if( PASSWORD_ENCODER.matches(password, userList.getPassword()) ){
            success = true;
            user = userList;
        }

        if(success) {
            LoginResponse loggedUser = new LoginResponse(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getJobTitle(), user.getBio(), user.getProfileUrl(), user.getCompanyName(), user.getFullName(), user.isActive(), user.getRoles());
            return new ResponseEntity<>(loggedUser, HttpStatus.OK);
        } else {
            response.setErrorCode(401);
            response.setMessage("Email/kullanıcı adı veya şifre yanlış.");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

    }

}
