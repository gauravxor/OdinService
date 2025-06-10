package com.clumsycoder.odinservice.constants;

public final class ErrorMessages {
    private ErrorMessages() {
    }

    /**
     * Odin Service related exception
     */
    public static final String ODIN_SERVICE_ERROR = "Something went wrong in OdinService.";

    /**
     * OTP related exception messages
     */
    public static final String OTP_ERROR = "Something went wrong with OTP.";

    /**
     * JWT related exception messages
     */
    public static final String JWT_ERROR = "Something went wrong with JWT.";

    /**
     * Nucleus Service related exception
     */
    public static final String NUCLEUS_SERVICE_ERROR = "Error in Nucleus Service.";
    public static final String USERNAME_ALREADY_USED = "Username is not available.";
    public static final String EMAIL_ALREADY_USED = "Email is already used.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_UPDATE_FAILED = "Failed to update the user.";
    public static final String USER_CREATE_FAILED = "Failed to create the user.";
    public static final String USER_DELETE_FAILED = "Failed to delete the user.";
    public static final String VALIDATION_ERROR = "Field validation error.";

}