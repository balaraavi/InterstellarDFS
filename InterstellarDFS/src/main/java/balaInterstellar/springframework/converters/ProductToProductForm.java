package balaInterstellar.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import balaInterstellar.springframework.commands.ProductForm;
import balaInterstellar.springframework.domain.Product;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setPlanetOrign(product.getPlanetOrign());
        productForm.setDistance(product.getDistance());
        productForm.setPlanetDestination(product.getPlanetDestination());
        return productForm;
    }
}
