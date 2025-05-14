package br.com.fiap.fin_up_api.controller;

import br.com.fiap.fin_up_api.model.User;
import br.com.fiap.fin_up_api.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PostMapping
    public User create(@RequestBody User user){
        user.setRole(UserRole.USER);
        return repository.save(user);
    }

}
