/**
 * @author venkataramesh
 * This class acts as wrapper for Product picked and its updated stock
 */
package com.commercehub.ims;

public class PickingResult extends Result {

    private String worker;

    /**
     * constructor for initializing product
     *
     * @param product
     */
    public PickingResult(Product product, String worker) {
        super(product);
        this.worker = worker;
    }

    /**
     * method to print the status
     */
    @Override
    public void printStatus() {
        System.out.println("Picker: " + worker + " completed work on productId: " + getProduct().getProductId());
    }

}
