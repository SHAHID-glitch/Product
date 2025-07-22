package com.haridwaruniversity.product.Product.UserDtoResponse;


import com.haridwaruniversity.product.Product.User.User;
import lombok.Data;

@Data
public class UserDtoResponse {
    private String name;
    private String email;

    public UserDtoResponse() {}

    public UserDtoResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
