/**
 * @author Venkataramesh
 * Exception class to handle product out of stock exception
 */
package com.commercehub.ims;

public class ProductOutofStockException extends Exception {

    private static final long serialVersionUID = 1L;

    public String productId;

    public ProductOutofStockException() {
        super();
    }

    public ProductOutofStockException(String message) {
        super(message);
    }

    public ProductOutofStockException(String message, String productId) {
        super(message);
        this.productId = productId;
    }

    public ProductOutofStockException(String message, Throwable e) {
        super(message, e);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " Product Id: " + productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
