package invendolab.katalog.controllers;

import invendolab.katalog.domain.Product;
import invendolab.katalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts(){
        return productService.listAll();
    }

    /*@ApiOperation(value = "Single product information by id", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved product"),
            @ApiResponse(code = 404, message = "There is no product in database")
    })
    @RequestMapping("getById/{id}")
    public Product getProductById(){
        return productService.listAll(@PathVariable Long id);
    } */


}
