package balaInterstellar.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import balaInterstellar.springframework.commands.ProductForm;
import balaInterstellar.springframework.domain.Product;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();
        if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
            product.setId(new Long(productForm.getId()));
        }
        product.setPlanetOrign(productForm.getPlanetOrign());
        product.setDistance(productForm.getDistance());
        product.setPlanetDestination(productForm.getPlanetDestination());
        return product;
    }
}
