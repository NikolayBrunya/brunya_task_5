package com.example.springrest;

import com.example.springrest.entity.User;
import com.example.springrest.exceptions.UserNotFoundException;
import com.example.springrest.exceptions.UserNotRightsException;
import com.example.springrest.service.RepositoryStubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


//1
@RestController
@RequestMapping
public class UserRestController {

    Principal principal;
    @Autowired
    private RepositoryStubService repositoryService;

    // 2
    // если сделать так же как и в 3, то непонятно как он будет различать
    // либо надо когда по другому переделывать, как в видео
    // передали параметр - все, ид - только определнный
    // но тогда смысл эксепшна в getUserById
    @GetMapping("/users/all")
    public List<User> listAllUsers() {
        return repositoryService.findAll();
    }

    //3
    @GetMapping("/users")
    public User getUserById(/*4*/@RequestParam(name = "id") Long id) {
        User user = repositoryService.findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    //5
    @PostMapping("/users")
    public User createUser(/* 6 */@RequestBody User newUser)  {
        //if (request.isUserInRole("ROLE2") == false) throw new UserNotRightsException();
        return repositoryService.saveUser(newUser);
    }

    //7
    @PutMapping("/users")
    public User updateUser(/* 8 */@RequestParam(name = "id") Long id,
            /* 9 */@RequestBody User updatedUser) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean hasUserRole = authentication.getAuthorities().stream()
//                .anyMatch(r -> r.getAuthority().equals("ROLE2"));
//        if (hasUserRole == false) throw new UserNotRightsException();

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
    @DeleteMapping("/users")
    public void deleteUser(/* 11 */ @RequestParam(name = "id") Long id) throws UserNotRightsException {
        //if (request.isUserInRole("ROLE2") == false) throw new UserNotRightsException();
        repositoryService.deleteById(id);
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Service is running";
    }
}
