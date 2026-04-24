package uz.silkStep.project.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    LOGIN_ATTEMPT_NOT_FOUND,
    USER_NOT_FOUND,
    DESTINATION_NOT_FOUND,
    DESTINATION_FACT_NOT_FOUND,
    ATTRACTION_NOT_FOUND,
    SESSION_NOT_FOUND,
    EVENT_NOT_FOUND,
    EVENT_TAG_NOT_FOUND,
    TRAVEL_TRIP_NOT_FOUND,
    TRIP_PLAN_NOT_FOUND,
    GUIDE_NOT_FOUND,

    NOT_REGISTERED_IN_TELEGRAM,
    OTP_CODE_MISMATCH,
    REFRESH_TOKEN_INVALID,
    EXISTS_LARGER_THAN_MIN_AMOUNT,
    EXISTS_LESS_THAN_MAX_AMOUNT,
    EXISTS_SLUG_ALREADY,

}

// ExceptionType → an enum that defines various types of exceptions that can occur in the application. 
//Each constant represents a specific type of exception, such as not finding a user, destination, attraction, or guide, as well as issues related to login attempts, OTP code mismatches, and token validity. 
//This enum is used to standardize the representation of different exception scenarios across the application.