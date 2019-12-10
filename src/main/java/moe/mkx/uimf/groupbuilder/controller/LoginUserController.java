package moe.mkx.uimf.groupbuilder.controller;


import moe.mkx.uimf.groupbuilder.model.LoginUser;
import moe.mkx.uimf.groupbuilder.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/members")
@RestController
public class LoginUserController {
    private final LoginUserService loginUserService;

    @Autowired
    public LoginUserController (LoginUserService loginUserService){
        this.loginUserService = loginUserService;
    }
    @PostMapping
    public void addLoginUser(@RequestBody LoginUser loginUser) {
        loginUserService.addLoginUser(loginUser);
    }
    @GetMapping
    public List<LoginUser> getAllUser() {
        return loginUserService.getAllUser();
    }
    @GetMapping(path = "{userID}")
    public LoginUser getPersonByID(@PathVariable("userID") UUID userID) {
        return loginUserService.getUserByID(userID)
                .orElse(null);
    }
    @DeleteMapping(path = "userID")
    public void deleteUserByID(@PathVariable("userID") UUID userID) {
        loginUserService.deleteLoginUser(userID);
    }

    @PutMapping(path = "{userID}")
    public void updateUserByID(@PathVariable("userID") UUID userID, @Valid @NonNull @RequestBody LoginUser updateUser) {
        loginUserService.updatePerson(userID, updateUser);

    }


}
