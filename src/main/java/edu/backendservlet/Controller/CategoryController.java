package edu.backendservlet.Controller;

import edu.backendservlet.DTO.Request.CategoryRequest;
import edu.backendservlet.DTO.Response.ApiResponse;
import edu.backendservlet.DTO.Response.CategoryResponse;
import edu.backendservlet.DTO.Response.PageResponse;
import edu.backendservlet.Model.Category;
import edu.backendservlet.Service.CatetoryService;
import edu.backendservlet.mapper.CategoryMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {
    CatetoryService catetoryService;
    CategoryMapper categoryMapper;
    @GetMapping()
        public ApiResponse<PageResponse<CategoryResponse>> getCategories(
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size){

        Page<Category> categoryPage = catetoryService.getlistcategory(PageRequest.of(page, size));

        PageResponse<CategoryResponse> response = PageResponse.
                <CategoryResponse>builder()
                .content(categoryPage.getContent()
                        .stream()
                        .map(categoryMapper::toCategory)
                        .toList())
                .totalElements(categoryPage.getTotalElements()) // tổng số bản ghi ở Db
                .totalPages(categoryPage.getTotalPages()) // sô danh sách của trang hiện tại
                .page(categoryPage.getNumber())
                .size(categoryPage.getSize()) // số phân tử của mỗi trang
                .build();
            return ApiResponse.<PageResponse<CategoryResponse>>
                            builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id){
        CategoryResponse response = catetoryService.getCategoryById(id);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .result(response)
                .build();
    }
    @PostMapping()
    public ApiResponse<Category> createCategory(@RequestBody CategoryRequest request){
        Category category = catetoryService.CreateCategory(request);
        return ApiResponse.<Category>builder()
                .code(HttpStatus.OK.value())
                .result(category)
                .build();
    }
    @PutMapping()
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable Long Id,@RequestBody CategoryRequest request){
        CategoryResponse response = catetoryService.UpdateCategory(Id,request);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .result(response)
                .message("Update successfully")
                .build();
    }
    @DeleteMapping()
    public ApiResponse<String> deleteCategory(Long id){
        catetoryService.DeleteCategory(id);
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .result("Thanh cong")
                .build();
    }
}
