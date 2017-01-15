/**
 * @author Venkataramesh
 * RestockerThread class acting as worker thread to handle restocking of products
 */

package com.commercehub.ims;

import java.util.concurrent.Callable;

public class RestockerThread implements Callable<Result> {

    private InventoryManagementSystem invMgtSystem;
    private String productId;
    private int amountToRestock;

    /**
     * constructor class to initialize invtMgtSystem, productId, amountToRestock
     */

    public RestockerThread(InventoryManagementSystem invMgtSystem, String productId, int amountToRestock) {
        this.invMgtSystem = invMgtSystem;
        this.productId = productId;
        this.amountToRestock = amountToRestock;
    }

    /**
     * method to call restockProduct
     *
     * @return
     * @throws Exception
     */
    @Override
    public RestockingResult call() throws Exception {
        return invMgtSystem.restockProduct(productId, amountToRestock);
    }

}
