package com.sandrohenrique.projeto_cadastro_v2.controller;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import com.sandrohenrique.projeto_cadastro_v2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listAll() {
        return new ResponseEntity<>(userService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<User> findById(@PathVariable  Long id) {
        return new ResponseEntity<>(userService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @GetMapping(path = "byName")
    public ResponseEntity<List<User>> findByName(@RequestParam (required = false) String name) {
        return new ResponseEntity<>(userService.findByName(name), HttpStatus.OK);
        // http://localhost:8080/users/byName?name=luc
    }

    @GetMapping(path = "byEmail")
    public ResponseEntity<List<User>> findByEmail(@RequestParam (required = false) String email) {
        return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
        // http://localhost:8080/users/byEmail?email=gmail
    }

    @GetMapping(path = "byAge")
    public ResponseEntity<List<User>> findByAge(@RequestParam (required = false) Integer age) {
        return new ResponseEntity<>(userService.findByAge(age), HttpStatus.OK);
        // http://localhost:8080/users/byAge?age=21
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserPostRequestBody userPostRequestBody) {
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
