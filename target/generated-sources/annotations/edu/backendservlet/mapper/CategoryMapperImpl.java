package edu.backendservlet.mapper;

import edu.backendservlet.DTO.Request.CategoryRequest;
import edu.backendservlet.DTO.Response.CategoryResponse;
import edu.backendservlet.Model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T18:00:17+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryResponse toCategory(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse.CategoryResponseBuilder categoryResponse = CategoryResponse.builder();

        categoryResponse.name( category.getName() );
        categoryResponse.description( category.getDescription() );
        categoryResponse.imageName( category.getImageName() );

        return categoryResponse.build();
    }

    @Override
    public Category toEntity(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( request.getName() );
        category.description( request.getDescription() );
        category.imageName( request.getImageName() );

        return category.build();
    }

    @Override
    public void updateCategory(Category category, CategoryRequest request) {
        if ( request == null ) {
            return;
        }

        category.setName( request.getName() );
        category.setDescription( request.getDescription() );
        category.setImageName( request.getImageName() );
    }
}
