package edu.backendservlet.mapper;

import edu.backendservlet.DTO.Request.ProductRequest;
import edu.backendservlet.DTO.Response.Interface.ProductResponseipm;
import edu.backendservlet.DTO.Response.ProductResponse;
import edu.backendservlet.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product  toProduct(ProductRequest request);
    ProductResponse toResponse(Product  product);

    Product toProductipm(ProductResponseipm responseipm);

    ProductResponse toResponseipm(ProductResponseipm productResponseipm);

    void updateProduct(@MappingTarget Product product ,ProductRequest request);

}
