package com.studbet.security.password;

public interface PasswordEncrypt {
    String encryptPassword(String password);
    boolean checkPassword(String password, String hashedPassword);
}
