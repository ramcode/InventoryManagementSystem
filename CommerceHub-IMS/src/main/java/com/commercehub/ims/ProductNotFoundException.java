/**
 * @author venkataramesh
 * Exception class to handle product not found in inventory
 */

package com.commercehub.ims;

public class ProductNotFoundException extends Exception {

    public String productId;

    private static final long serialVersionUID = 1L;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String productId, String message) {
        super(message);
        this.productId = productId;
    }

    public ProductNotFoundException(String message, Throwable e) {
        super(message, e);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
