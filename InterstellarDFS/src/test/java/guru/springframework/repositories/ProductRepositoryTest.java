package guru.springframework.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import balaInterstellar.springframework.domain.Product;
import balaInterstellar.springframework.repositories.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    private static final Double DISTANCE = Double.valueOf(10.00);
    private static final String PLANET_ORIGN = "A";
    private static final String PLANET_DESTINATION = "B";

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Product product = new Product();
        product.setPlanetOrign(PLANET_ORIGN);
        product.setPlanetDestination(PLANET_DESTINATION);
        product.setDistance(DISTANCE);

        //when
        productRepository.save(product);

        //then
        Assert.assertNotNull(product.getId());
        Product newProduct = productRepository.findOne(product.getId());
        Assert.assertEquals((Long) 1L, newProduct.getId());
        Assert.assertEquals(PLANET_ORIGN, newProduct.getPlanetOrign());
        Assert.assertEquals(DISTANCE.compareTo(newProduct.getDistance()), 0);
        Assert.assertEquals(PLANET_DESTINATION, newProduct.getPlanetDestination());
    }
}