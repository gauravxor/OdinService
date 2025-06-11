package com.clumsycoder.odinservice.constants;

public final class ErrorMessages {
    private ErrorMessages() {
    }

    /**
     * Odin Service error messages
     */
    public static final String INVALID_CREDENTIALS = "Invalid username or password.";
    public static final String UNAUTHORIZED_ACCESS = "You are not authorized to access this resource.";

    public static final String TOKEN_EXPIRED = "Authentication token has expired.";
    public static final String JWT_ERROR = "Invalid or malformed authentication token.";

    public static final String OTP_SERVICE_ERROR = "An error occurred while processing the OTP.";
    public static final String OTP_ERROR = "Invalid OTP request.";
    public static final String OTP_EXPIRED = "The OTP has expired.";
    public static final String OTP_INVALID = "The OTP provided is incorrect.";
    public static final String OTP_MAX_ATTEMPT_EXCEEDED = "Maximum OTP verification attempts exceeded.";

    /**
     * Error Messages used by both Odin and Nucleus Service errors
     */
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String USER_ALREADY_EXISTS = "User already exists.";

    /**
     * Nucleus Service error messages
     */
    public static final String NUCLEUS_SERVICE_ERROR = "An internal error occurred in Nucleus Service.";
    public static final String USER_ERROR = "An error occurred while processing user information.";
    public static final String EMAIL_ALREADY_USED = "Email address is already in use.";
    public static final String USERNAME_ALREADY_USED = "Username is already taken.";
    public static final String VALIDATION_ERROR = "Input validation failed.";
}