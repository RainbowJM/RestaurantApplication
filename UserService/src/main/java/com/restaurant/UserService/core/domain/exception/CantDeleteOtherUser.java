package com.restaurant.UserService.core.domain.exception;

public class CantDeleteOtherUser extends RuntimeException {
    public CantDeleteOtherUser() {
        super("You can't delete another user if you aren't signed into a staff or management account.");
    }
}
