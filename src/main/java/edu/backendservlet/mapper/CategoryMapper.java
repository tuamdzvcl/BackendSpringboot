package edu.backendservlet.mapper;

import edu.backendservlet.DTO.Request.CategoryRequest;
import edu.backendservlet.DTO.Response.CategoryResponse;
import edu.backendservlet.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    //đích đến laf response
    CategoryResponse toCategory(Category category);
    //đích đến là Category
    Category toEntity(CategoryRequest request);
    //chuyển về dữ liệu của category
    void updateCategory(@MappingTarget Category category,CategoryRequest request);
}
