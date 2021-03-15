package com.example.spring_boot_rest1.controller;

import com.example.spring_boot_rest1.exceptions.InvalidCredentials;
import com.example.spring_boot_rest1.exceptions.UserNotFoundException;
import com.example.spring_boot_rest1.service.AuthorizationService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/")
@Validated
public class AuthorizationController implements ErrorController {
    private AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping()
    public ModelAndView showIndexMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //    /*username как в фэйсбуке:
    //        - нельзя использовать только цифры
    //        - можно A-Z 0-9 и точку
    //        - длина не менее 5 символов не более 50
    //        - нельзя точку в начале или конце, две точки подряд

    @PostMapping("enter")
    public String check(@RequestParam @Pattern(regexp = "^(?!.*\\.\\.)(?!\\.)(?!.*\\.$)(?!\\d+$)[a-zA-Z0-9.]{5,50}$") String username,
                        @RequestParam String pass)
            throws UserNotFoundException, InvalidCredentials {
        return service.check(username, pass);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException() {
        return "Такой пользователь не найден";
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleRegexException() {
        return "- нельзя использовать только цифры <br>" +
                "- можно A-Z 0-9 и точку<br>" +
                "- длина не менее 5 символов не более 50<br>" +
                "- нельзя точку в начале или конце, две точки подряд<br>";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleEverything(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

    @RequestMapping(value = "/error")
    public ModelAndView error() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }
}
