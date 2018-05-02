package invendolab.katalog.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String exception) {
        super(exception);
    }

}