package uz.silkStep.project.filter;

import lombok.Data;

@Data
public class BaseFilter {
    private String search;
    private Integer page = 0;
    private Integer size = 9;
}

// BaseFilter → serves as a base class for filtering and pagination in API requests. 
//It contains common fields such as search (for keyword-based filtering), page (for pagination), and size (to specify the number of items per page). 
//This class can be extended by specific filter classes for different entities, allowing for consistent handling of filtering and pagination across the application.