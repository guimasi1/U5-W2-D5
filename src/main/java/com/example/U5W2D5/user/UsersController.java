package com.example.U5W2D5.user;

import com.example.U5W2D5.config.MailgunSender;
import com.example.U5W2D5.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private MailgunSender mailgunSender;

    @GetMapping
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "id") String id) {
        return usersService.getUsers(page,size,id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsersResponseDTO create(@RequestBody @Validated NewUserDTO user, BindingResult validation) {
        if(validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Errori nel payload.");
        } else {
            User newUser = usersService.save(user);
            //mailgunSender.sendMail(newUser.getEmail());
            return new UsersResponseDTO(newUser.getId());
        }
    }

    @PutMapping("/{uuid}")
    public User updateById(@PathVariable UUID uuid, @RequestBody User body) {
        return usersService.findByIdAndUpdate(uuid, body);
    }

    @DeleteMapping("/{uuid}")
    public void deleteById(@PathVariable UUID uuid) {
        usersService.deleteById(uuid);
    }

    @GetMapping("/{uuid}")
    public User getUserById(@PathVariable UUID uuid) {
        return usersService.findById(uuid);
    }

}
