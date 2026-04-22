package uz.silkStep.project.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PageableResponse<T> {
    private long totalElements;
    private int totalPages;
    private boolean hasNextPage;
    private List<T> content;
}
