
package uz.silkStep.project.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserStatus implements Serializable {
    PENDING,
    ACTIVE,
    BLOCK,
    REVIEW;
}

// UserStatus → represents the status of a user account, with possible values of PENDING (indicating that the account is awaiting approval or activation), ACTIVE (indicating that the account is active and can be used), BLOCK (indicating that the account has been blocked or suspended), and REVIEW (indicating that the account is under review, possibly for verification or moderation purposes). 
//This enum is likely used to manage and track the state of user accounts within the application.