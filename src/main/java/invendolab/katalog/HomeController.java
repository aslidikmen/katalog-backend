/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@Api(value = "home", description = "Home screen to hello")
public class HomeController {

    @Value("${HOMEPAGE_MESSAGE:local}")
    String homepageMessage;

    @ApiOperation(value = "Only saying hello to everyone.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully") })
    @GetMapping("/")
    public String home(){

        return "<body style=\"background-color:#F6F6F6\">" +
                "<link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans\" rel=\"stylesheet\">" +
                "<h1 style=\"font-family: 'Nunito Sans', sans-serif; color:#3B3B3B\">" +
                "<br /><br /><br />" +
                "<center>" +
                "Hello from Katalog API " +
                "through " +
                homepageMessage +
                " environment." +
                "</center>" +
                "</h1>" +
                "</body>";

    }

}
