package balaInterstellar.springframework.services;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import balaInterstellar.springframework.commands.ProductForm;
import balaInterstellar.springframework.domain.Product;

/**
 * Created by Bala Bhargha on 4/4/19.
 */
public interface ProductService {

    List<Product> listAll() throws EncryptedDocumentException, InvalidFormatException, IOException;

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);
    
    List<Product> getAll();
}
