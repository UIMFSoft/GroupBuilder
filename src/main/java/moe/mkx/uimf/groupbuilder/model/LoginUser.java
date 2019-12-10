package moe.mkx.uimf.groupbuilder.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class LoginUser {
    private final UUID userID;
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String email;

    public LoginUser(@JsonProperty("id") UUID userID,
                     @JsonProperty("name") String username,
                     @JsonProperty("password") String password,
                     @JsonProperty("email") String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public UUID getUserID(){
        return userID;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }




}
