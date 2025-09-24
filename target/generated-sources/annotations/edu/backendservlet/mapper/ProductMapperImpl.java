package edu.backendservlet.mapper;

import edu.backendservlet.DTO.Request.ProductRequest;
import edu.backendservlet.DTO.Response.Interface.ProductResponseipm;
import edu.backendservlet.DTO.Response.ProductResponse;
import edu.backendservlet.Model.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-23T18:14:08+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.totalbuy( request.getTotalbuy() );
        product.author( request.getAuthor() );
        product.pages( request.getPages() );
        product.publisher( request.getPublisher() );
        product.yearPublishing( request.getYearPublishing() );
        product.description( request.getDescription() );
        product.imageName( request.getImageName() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.totalbuy( product.getTotalbuy() );
        productResponse.author( product.getAuthor() );
        productResponse.pages( product.getPages() );
        productResponse.publisher( product.getPublisher() );
        productResponse.yearPublishing( product.getYearPublishing() );
        productResponse.description( product.getDescription() );
        productResponse.imageName( product.getImageName() );

        return productResponse.build();
    }

    @Override
    public Product toProductipm(ProductResponseipm responseipm) {
        if ( responseipm == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( responseipm.getId() );
        product.name( responseipm.getName() );
        product.totalbuy( responseipm.getTotalbuy() );
        product.author( responseipm.getAuthor() );
        product.pages( responseipm.getPages() );
        product.publisher( responseipm.getPublisher() );
        product.description( responseipm.getDescription() );
        product.imageName( responseipm.getImageName() );

        return product.build();
    }

    @Override
    public ProductResponse toResponseipm(ProductResponseipm productResponseipm) {
        if ( productResponseipm == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( productResponseipm.getId() );
        productResponse.name( productResponseipm.getName() );
        productResponse.totalbuy( productResponseipm.getTotalbuy() );
        productResponse.author( productResponseipm.getAuthor() );
        productResponse.pages( productResponseipm.getPages() );
        productResponse.publisher( productResponseipm.getPublisher() );
        productResponse.description( productResponseipm.getDescription() );
        productResponse.imageName( productResponseipm.getImageName() );

        return productResponse.build();
    }

    @Override
    public void updateProduct(Product product, ProductRequest request) {
        if ( request == null ) {
            return;
        }

        product.setName( request.getName() );
        product.setTotalbuy( request.getTotalbuy() );
        product.setAuthor( request.getAuthor() );
        product.setPages( request.getPages() );
        product.setPublisher( request.getPublisher() );
        product.setYearPublishing( request.getYearPublishing() );
        product.setDescription( request.getDescription() );
        product.setImageName( request.getImageName() );
    }
}
