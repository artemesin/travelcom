package user.service.exceptions;

import common.buisness.exceptions.BasicTravelCheckedException;

public class UserStillHasOrdersException extends BasicTravelCheckedException {

    public UserStillHasOrdersException(String message, int code) {
        super(message, code);
    }
}
