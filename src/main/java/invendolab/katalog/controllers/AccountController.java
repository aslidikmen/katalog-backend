/*
 * Created by Batuhan Kök on 14/5/2018.
 * Copyright (c) D-Teknoloji 2018.
 */

package invendolab.katalog.controllers;

import invendolab.katalog.helpers.RandomGenerator;
import invendolab.katalog.helpers.Response;
import invendolab.katalog.models.Consumer;
import invendolab.katalog.models.ForgetPassword;
import invendolab.katalog.repositories.ConsumerRepository;
import invendolab.katalog.repositories.ForgetPasswordRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
@Api(value="account", description="CRUD for user accounts")
public class AccountController {

    @Autowired
    ConsumerRepository repository;

    @Autowired
    ForgetPasswordRepository forgetPasswordRepo;

    @Value("${INFO_MAIL:test@gmail.com}")
    String envMail;

    @Value("${INFO_PASSWORD:test}")
    String envPassword;

    private Response response = new Response();

    @ApiOperation(value = "Forget password", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully send password reset link to user"),
            @ApiResponse(code = 404, message = "There is no user in database with this email")
    })
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> forgetPassword(@RequestParam(value = "email") String email) {

        Consumer user;

        if (email != null && repository.existsByEmail(email)) {

            user = repository.findByEmail(email);

            if (user.getEmail() != null) {

                RandomGenerator rand = new RandomGenerator();
                String key = rand.getKey();

                ForgetPassword forgetPassword = new ForgetPassword(user.getId(), key, false);

                try {

                    forgetPasswordRepo.save(forgetPassword);

                    response.setErrorCode(200);

                    Email emailToSend = EmailBuilder.startingBlank()
                            .from(envMail)
                            .to(email)
                            .withSubject("Katalog Şifre Sıfırlama Talebiniz İletilmiştir.")
                            .withPlainText("Şifre sıfırlamak için aşağıdaki linke tıklayınız.\n" + " https://www.katalog.com/sifre-degistir/" + key)
                            .withHeader("X-Priority", 5)
                            .buildEmail();

                    Mailer mailer = MailerBuilder
                            .withSMTPServer("smtp.gmail.com", 587, String.valueOf(envMail), String.valueOf(envPassword))
                            .withTransportStrategy(TransportStrategy.SMTP_TLS)
                            .withSessionTimeout(10 * 1000)
                            .withDebugLogging(true)
                            .buildMailer();

                    mailer.sendMail(emailToSend);

                    response.setMessage("Şifre sıfırlama linki " + email + " adresine gönderilmiştir.");
                    response.setSuccess(true);
                    return new ResponseEntity<>(response, HttpStatus.OK);

                } catch (Exception e) {

                    response.setErrorCode(502);
                    response.setMessage("Şifre sıfırlama işlemi yapılamadı.");
                    response.setSuccess(false);
                    response.setException(e);
                    return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

                }

            } else {

                response.setErrorCode(404);
                response.setMessage("Bu e-mail adresine ait bir kullanıcı bulunamadı.");
                response.setSuccess(false);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

            }

        } else {

            response.setErrorCode(400);
            response.setMessage("E-mail adresi boş bırakılamaz.");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }

    @ApiOperation(value = "Reset password with forget password unique key", response = Response.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully send password reset link to user"),
            @ApiResponse(code = 404, message = "There is no user in database with this email")
    })
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ResponseEntity<?> resetPassword(@RequestParam(value = "key") String key, @RequestParam(value = "password") String password) {

        Consumer user;
        ForgetPassword forgetPassword;

        if (password != null || key != null) {

            if (forgetPasswordRepo.existsByKey(key)) {

                forgetPassword = forgetPasswordRepo.findAllByKey(key);

                Optional<Consumer> consumer = repository.findById(forgetPassword.getConsumerId());

                if (consumer.isPresent()) {

                    user = consumer.get();
                    user.setPassword(password);

                    try {

                        repository.save(user);
                        forgetPasswordRepo.deleteById(forgetPassword.getId());

                        Email emailToSend = EmailBuilder.startingBlank()
                                .from(envMail)
                                .to(user.getEmail())
                                .withSubject("Katalog Şifre Sıfırlama İşleminiz Tamamlanmıştır.")
                                .withPlainText("Şifre sıfırlama işleminiz başarıyla tamamlanmıştır.\n" + "Hatalı bir işlem olduğunu düşünyorsanız, bu e-mail'e cevap yazabilirsiniz.")
                                .withHeader("X-Priority", 5)
                                .buildEmail();

                        Mailer mailer = MailerBuilder
                                .withSMTPServer("smtp.gmail.com", 587, String.valueOf(envMail), String.valueOf(envPassword))
                                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                                .withSessionTimeout(10 * 1000)
                                .withDebugLogging(true)
                                .buildMailer();

                        mailer.sendMail(emailToSend);

                        response.setErrorCode(200);
                        response.setMessage("Şifre sıfırlama işlemi başarıyla tamamlanmıştır.");
                        response.setSuccess(true);
                        return new ResponseEntity<>(response, HttpStatus.OK);

                    } catch (Exception e) {

                        response.setErrorCode(502);
                        response.setMessage("Şifre sıfırlama işlemi yapılamadı.");
                        response.setSuccess(false);
                        response.setException(e);
                        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

                    }

                } else {

                    response.setErrorCode(502);
                    response.setMessage("Şifre sıfırlama işlemi yapılamadı.");
                    response.setSuccess(false);
                    return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);

                }

            } else {

                response.setErrorCode(400);
                response.setMessage("Bu bu link daha önce kullanılmıştır.");
                response.setSuccess(false);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

            }

        } else {

            response.setErrorCode(400);
            response.setMessage("E-mail adresi boş bırakılamaz.");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }

    }

}
