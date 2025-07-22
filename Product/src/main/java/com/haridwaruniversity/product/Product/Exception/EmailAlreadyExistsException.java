package com.haridwaruniversity.product.Product.Exception;

public class EmailAlreadyExistsException extends RuntimeException{
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
}
