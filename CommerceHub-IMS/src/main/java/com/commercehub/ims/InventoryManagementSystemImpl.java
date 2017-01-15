package com.commercehub.ims;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VenkataRamesh
 *         Implementation class for {@link InventoryManagementSystem}
 */

public class InventoryManagementSystemImpl implements InventoryManagementSystem {

    /**
     * instance variable to store the inventory
     */

    private Map<String, Product> inventory = null;

    /**
     * constructor to initialize inventory
     */
    public InventoryManagementSystemImpl() {

        this.inventory = new HashMap<>();
    }

    /**
     * parameterized constructor for initializing inventory
     *
     * @param inventory
     */

    public InventoryManagementSystemImpl(Map<String, Product> inventory) {
        this.inventory = inventory;
    }


    /**
     * @param productId    The ID of the product to pick
     * @param amountToPick The quantity of the product to pick
     * @return
     * @throws ProductNotFoundException
     * @throws ProductOutofStockException
     * @throws InsufficientStockException
     * @throws InterruptedException
     */
    @Override
    public PickingResult pickProduct(String productId, int amountToPick) throws ProductNotFoundException,
            ProductOutofStockException, InsufficientStockException, InterruptedException {
        if (!inventory.containsKey(productId)) {
            throw new ProductNotFoundException("Product Id: " + productId + " not found in inventory");
        }
        Product product = inventory.get(productId);
        synchronized (product) {
            String worker = Thread.currentThread().getName();
            System.out.println("Picker :" + worker + " working on ProductId: " + productId);
            if (product.getStockLevel() == 0) {
                throw new ProductOutofStockException("Product out of stock", productId);
            }
            if (product.getStockLevel() < amountToPick) {
                throw new InsufficientStockException("Insufficient stock for ProductId: " + productId);
            }
            Thread.sleep(amountToPick * 100);
            product.setStockLevel(product.getStockLevel() - amountToPick);
            return new PickingResult(product, worker);
        }

    }


    /**
     * @param productId       The ID of the product to restock
     * @param amountToRestock The quantity of the product to restock
     * @return
     * @throws ProductNotFoundException
     * @throws InterruptedException
     */
    @Override
    public RestockingResult restockProduct(String productId, int amountToRestock)
            throws ProductNotFoundException, InterruptedException {
        if (!inventory.containsKey(productId)) {
            throw new ProductNotFoundException("Product Id: " + productId + " not found in inventory");
        }
        Product product = inventory.get(productId);
        synchronized (product) {
            String worker = Thread.currentThread().getName();
            System.out
                    .println("Restocker :" + worker + " working on ProductId: " + productId);
            Thread.sleep(amountToRestock * 100);
            product.setStockLevel(product.getStockLevel() + amountToRestock);
            return new RestockingResult(product, worker);
        }
    }

    /**
     * Method to add a new product to inventory
     *
     * @param productId
     * @param newProduct
     */
    public void addNewProductToInventory(String productId, Product newProduct) {
        if (!inventory.containsKey(productId)) {
            inventory.put(productId, newProduct);
        }
    }

    /**
     * Method to return all inventory products
     *
     * @return List<Product>
     */
    public List<Product> getAllInventoryProducts() {
        return new ArrayList<Product>(inventory.values());
    }


    /**
     * Method to remove a given product from inventory
     *
     * @param productId
     */
    public void removeProductFromInventory(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        }
    }

}
