
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
