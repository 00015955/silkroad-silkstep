package uz.silkStep.project.filter;

import lombok.Data;

@Data
public class BaseFilter {
    private String search;
    private Integer page = 0;
    private Integer size = 9;
}
