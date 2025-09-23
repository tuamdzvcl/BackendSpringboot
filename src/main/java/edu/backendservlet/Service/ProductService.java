package edu.backendservlet.Service;

import edu.backendservlet.DTO.Request.ProductRequest;
import edu.backendservlet.DTO.Response.Interface.ProductResponseipm;
import edu.backendservlet.DTO.Response.PageResponse;
import edu.backendservlet.DTO.Response.ProductResponse;
import edu.backendservlet.Model.Category;
import edu.backendservlet.Model.Product;
import edu.backendservlet.Model.Product_Category;
import edu.backendservlet.Repository.CategoryRepository;
import edu.backendservlet.Repository.ProductCategoryRepository;
import edu.backendservlet.Repository.ProductRepository;
import edu.backendservlet.exception.Appexcption;
import edu.backendservlet.exception.ErrorCode;
import edu.backendservlet.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper  productMapper;
    ProductCategoryRepository productCategoryRepository;
    CategoryRepository categoryRepository;

    public Page<ProductResponseipm> getlistProduct(Pageable pageable) {
        return productRepository.findProductsflowCategory(pageable);
    }
    public ProductResponseipm getProductById(Long id){
        return productRepository.findProductflowCategoryByid(id)
                .orElseThrow(()-> new Appexcption(ErrorCode.PRODUCT_NOT_FOUNF));
    }
    @Transactional
    public ProductResponse CreateProduct(ProductRequest request){
        Category category = categoryRepository.findByName(request.getCategory_name())
                .orElseThrow(()->new Appexcption(ErrorCode.CATEGORY_NOT_NAME));
            request.setCategory_name(category.getName());
        Product product = productMapper.toProduct(request);
        Product p = productRepository.save(product);
        Product_Category pc = new Product_Category();
        pc.setProduct(p);
        pc.setCategory(category);
        productCategoryRepository.save(pc);
        return productMapper.toResponse(p);
    }
    @Transactional
    public ProductResponse UpdateProduct(Long id ,ProductRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Appexcption(ErrorCode.PRODUCT_NOT_FOUNF));

        productMapper.updateProduct(product,request);

        productRepository.save(product);

        return productMapper.toResponse(product);

    }
    @Transactional
    public String DeleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Appexcption(ErrorCode.PRODUCT_NOT_FOUNF));
        List<Product_Category> lpc = productCategoryRepository.findByProductId(id);

        productCategoryRepository.deleteAll(lpc);
        productRepository.delete(product);

        return "Xóa thành công";
    }
}
