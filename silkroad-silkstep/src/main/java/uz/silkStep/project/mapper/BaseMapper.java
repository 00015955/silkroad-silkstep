
package uz.silkStep.project.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import uz.silkStep.project.dto.response.PageableResponse;

@Mapper
public interface BaseMapper {
    default <T> PageableResponse<T> toPageableResponse(Page<T> page) {
        PageableResponse<T> response = new PageableResponse<>();
        response.setContent(page.getContent());
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setHasNextPage(page.hasNext());
        return response;
    }
}
// This code defines a BaseMapper interface with a default method to convert a Spring Data Page object into a custom PageableResponse object.