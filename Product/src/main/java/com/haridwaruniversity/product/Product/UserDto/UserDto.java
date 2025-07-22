package com.haridwaruniversity.product.Product.UserDto;

import com.haridwaruniversity.product.Product.User.User;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


public class UserDto {
    private String name;
    private String email;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDto(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
    public UserDto() {}
}
