package ru.netology.authorizationservice.exception;

/**
 * @author Stanislav Rakitov
 */
public class UnauthorizedUser extends RuntimeException {
    public UnauthorizedUser(String msg) {
        super(msg);
    }
}