/**
 * Restocking Result classs acting as wrapper for Product restocked
 */
package com.commercehub.ims;

public class RestockingResult extends Result {

    private String worker;

    public RestockingResult(Product product, String worker) {
        super(product);
        this.worker = worker;
    }

    @Override
    public void printStatus() {
        System.out.println("Restocker: " + worker + " completed work on productId: " + getProduct().getProductId());
    }


}
