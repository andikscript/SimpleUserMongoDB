package com.andikscript.simpleusermongodb.model.mongo;

import com.andikscript.simpleusermongodb.constrainst.email.EmailValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "email is mandatory")
    @EmailValue(message = "email must valid")
    private String email;

    @NotBlank(message = "username is mandatory")
    private String username;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotNull(message = "role is mandatory")
    private Role[] roles;

    private String confirmed;

    @NotBlank(message = "phone is mandatory")
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
