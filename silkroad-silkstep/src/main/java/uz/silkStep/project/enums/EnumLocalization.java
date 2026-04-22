package uz.silkStep.project.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EnumLocalization implements Serializable {
    String ru;
    String en;
    String uz;
}
