package uz.silkStep.project.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.silkStep.project.enums.Language;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatItem implements Serializable {

    private String value;
    private Map<Language, String> label;
}
