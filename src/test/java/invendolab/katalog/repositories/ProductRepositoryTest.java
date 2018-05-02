package invendolab.katalog.repositories;

import invendolab.katalog.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    private static final Float FLOAT_100 = 100.0f;
    private static final String PRODUCT_DESCRIPTION = "a cool product";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Product product = new Product();
        product.setDescription(PRODUCT_DESCRIPTION);
        product.setImageUrl(IMAGE_URL);
        product.setPrice(FLOAT_100);

        //when
        productRepository.save(product);

        //then
        Assert.assertNotNull(product.getId());
        Product newProduct = productRepository.findById(product.getId()).orElse(null);
        Assert.assertEquals((Long) 1L, newProduct.getId());
        Assert.assertEquals(PRODUCT_DESCRIPTION, newProduct.getDescription());
        Assert.assertEquals(FLOAT_100.compareTo(newProduct.getPrice()), 0);
        Assert.assertEquals(IMAGE_URL, newProduct.getImageUrl());
    }
}
