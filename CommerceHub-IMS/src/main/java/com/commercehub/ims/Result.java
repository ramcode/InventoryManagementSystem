/**
 * @author Venkataramesh
 * Result class acting as super class for Picker and Restocker classes
 */
package com.commercehub.ims;


public class Result {

    public Result(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    private Product product;

    public void printStatus() {
        System.out.println("Completed");
    }

}
