package com.example.springrest;

import com.example.springrest.entity.User;
import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.service.RepositoryStubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//1
@RestController
@RequestMapping("/users")
public class UserRestController {


    @Autowired
    private RepositoryStubService repositoryService;

   // 2
   // если сделать так же как и в 3, то непонятно как он будет различать
    // либо надо когда по другому переделывать, как в видео
    // передали параметр - все, ид - только определнный
    // но тогда смысл эксепшна в getUserById
    @GetMapping("/all")
    public List<User> listAllUsers() {
        return repositoryService.findAll();
    }

    //3
    @GetMapping
    public User getUserById(/*4*/@RequestParam(name="id") Long id) {
        User user = repositoryService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    //5
    @PostMapping
    public User createUser(/* 6 */@RequestBody User newUser) {
        return repositoryService.saveUser(newUser);
    }

    //7
    @PutMapping
    public User updateUser(/* 8 */@RequestParam(name="id") Long id,
                           /* 9 */@RequestBody User updatedUser) {
        User userToUpdate = repositoryService.findUserById(id);
        if (userToUpdate != null) {
            userToUpdate.setFirstName(updatedUser.getFirstName());
            userToUpdate.setSecondName(updatedUser.getSecondName());
            userToUpdate.setPosition(updatedUser.getPosition());
            userToUpdate.setDepartment(updatedUser.getDepartment());
            return repositoryService.saveUser(id, userToUpdate);
        } else {
            updatedUser.setId(id);
            return repositoryService.saveUser(id, updatedUser);
        }
    }

   // 10
    @DeleteMapping
    public void deleteUser(/* 11 */ @RequestParam(name="id")Long id) {
        repositoryService.deleteById(id);
    }


}
