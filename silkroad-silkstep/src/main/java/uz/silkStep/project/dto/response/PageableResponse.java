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
// PageableResponse → represents a generic response DTO for paginated data, containing fields for the total number of elements, total pages, whether there is a next page, and the content of the current page as a list of type T. This class can be used to standardize the structure of paginated responses across different endpoints in the application.