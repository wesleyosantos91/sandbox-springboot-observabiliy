package io.github.wesleyosantos91.mapper;

import io.github.wesleyosantos91.domain.entity.PersonEntity;
import io.github.wesleyosantos91.domain.request.PersonQueryRequest;
import io.github.wesleyosantos91.domain.request.PersonRequest;
import io.github.wesleyosantos91.domain.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface PersonMapper {

    PersonEntity toEntity(final PersonQueryRequest queryRequest);
    PersonEntity toEntity(final PersonRequest request, @MappingTarget PersonEntity entity);
    PersonResponse toResponse(final PersonEntity entity);


    default List<PersonResponse> toListResponse(List<PersonEntity> entities){
        List<PersonResponse> list = new ArrayList<>();
        entities.forEach(e -> list.add(toResponse(e)));
        return list;
    }

    default Page<PersonResponse> toPageResponse(Page<PersonEntity> pages){
        List<PersonResponse> list = toListResponse(pages.getContent());
        return new PageImpl<>(list, pages.getPageable(), pages.getTotalElements());

    }
}
