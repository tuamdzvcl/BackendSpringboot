package edu.backendservlet.Service;

import edu.backendservlet.DTO.Request.CategoryRequest;
import edu.backendservlet.DTO.Response.CategoryResponse;
import edu.backendservlet.Model.Category;
import edu.backendservlet.Repository.CategoryRepository;
import edu.backendservlet.exception.Appexcption;
import edu.backendservlet.exception.ErrorCode;
import edu.backendservlet.mapper.CategoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;





@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CatetoryService {
    CategoryRepository categoryRepository;
    CategoryMapper  categoryMapper;

    public Page<Category> getlistcategory(Pageable pageable) {
        return  categoryRepository.findAll(pageable);
    }
    public CategoryResponse getCategoryById(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new Appexcption(ErrorCode.CATEGORT_NOT_FOUND));
        return  categoryMapper.toCategory(category);
    }
    public Category CreateCategory(CategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        return categoryRepository.save(category);

    }
    public CategoryResponse UpdateCategory(Long id,CategoryRequest request){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new Appexcption(ErrorCode.CATEGORT_NOT_FOUND));
        categoryMapper.updateCategory(category,request);
        categoryRepository.save(category);
        return categoryMapper.toCategory(category);
    }
    public String DeleteCategory(Long id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new Appexcption(ErrorCode.CATEGORT_NOT_FOUND));
        categoryRepository.delete(category);
        return "Xoa the loai thanh cong";
    }

}
