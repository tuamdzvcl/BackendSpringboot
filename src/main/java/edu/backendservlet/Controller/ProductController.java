package edu.backendservlet.Controller;

import edu.backendservlet.DTO.Request.ProductRequest;
import edu.backendservlet.DTO.Response.ApiResponse;
import edu.backendservlet.DTO.Response.CategoryResponse;
import edu.backendservlet.DTO.Response.Interface.ProductResponseipm;
import edu.backendservlet.DTO.Response.PageResponse;
import edu.backendservlet.DTO.Response.ProductResponse;
import edu.backendservlet.Model.Category;
import edu.backendservlet.Service.CatetoryService;
import edu.backendservlet.Service.ProductService;
import edu.backendservlet.mapper.ProductMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;
    ProductMapper  productMapper;
    @GetMapping()
    public ApiResponse<PageResponse<ProductResponseipm>> getProduct_NameCaregory(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Page<ProductResponseipm> responseipms = productService.getlistProduct(PageRequest.of(page, size));

        PageResponse<ProductResponseipm> response = PageResponse.
                <ProductResponseipm>builder()
                .content(responseipms.getContent()
                        .stream().toList())
                .totalElements(responseipms.getTotalElements()) // tổng số bản ghi ở Db
                .totalPages(responseipms.getTotalPages()) // sô danh sách của trang hiện tại
                .page(responseipms.getNumber())
                .size(responseipms.getSize()) // số phân tử của mỗi trang
                .build();
        return ApiResponse.<PageResponse<ProductResponseipm>>
                        builder()
                .code(HttpStatus.OK.value())
                .result(response)
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductResponseipm> getProductByIdCaregory(@PathVariable Long id){

        ProductResponseipm productResponse=productService.getProductById(id);

        return ApiResponse.<ProductResponseipm>builder()
                .code(HttpStatus.OK.value())
                .result(productResponse)
        .build();
    }
    @PostMapping()
    public ApiResponse<ProductResponse> addProduct(@RequestBody ProductRequest request){
        ProductResponse response= productService.CreateProduct(request);
            return ApiResponse.<ProductResponse>builder()
                    .code(HttpStatus.OK.value())
                    .result(response)
                    .build();
    }
    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProduct(@PathVariable Long id,@RequestBody ProductRequest request){
        ProductResponse response= productService.UpdateProduct(id, request);
        return ApiResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .result(response)
                .build();
    }
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteProduct(@PathVariable Long id){
       String result = productService.DeleteProduct(id);

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .result(result)
                .build();
    }
}
