package ru.netology.authorizationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.authorizationservice.exception.InvalidCredentials;
import ru.netology.authorizationservice.exception.UnauthorizedUser;
import ru.netology.authorizationservice.model.Authorities;
import ru.netology.authorizationservice.service.AuthorizationService;

import java.util.List;

/**
 * @author Stanislav Rakitov
 */
@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }


    // Теперь, когда весь код у вас готов, то вам необходимо написать обработчики ошибок,
    // которые выкидывает сервис AuthorizationService.


    // На InvalidCredentials он должен обратно клиенту отсылать http статус с кодом 400
    // и телом в виде сообщения из exception'а
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // На UnauthorizedUser он должен обратно клиенту отсылать http статус с кодом 401
    // и телом в виде сообщения из exception'а и писать в консоль сообщение из exception'а
    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> unauthorizedUserHandler(UnauthorizedUser e) {
        System.out.println("[unauthorizedUserHandler] " + e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
