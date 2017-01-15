package com.commercehub.ims;

/**
 * @author VenkataRamesh
 * Copyright (c) 1999-2016 Commerce Technologies Inc. All rights reserved.
 * Implementations of this interface including access to shared data must be
 * thread-safe.
 **/
public interface InventoryManagementSystem {
	/**
	 * Deduct 'amountToPick' of the given 'productId' from inventory.
	 * 
	 * @param productId
	 *            The ID of the product to pick
	 * @param amountToPick
	 *            The quantity of the product to pick
	 * @return PickingResult
	 * 			  Wrapper of product and updated stock
	 */
	PickingResult pickProduct(String productId, int amountToPick) throws Exception;

	/**
	 * Add 'amountToRestock' of the given productId to inventory.
	 * 
	 * @param productId
	 *            The ID of the product to restock
	 * @param amountToRestock
	 *            The quantity of the product to restock
	 * @return RestockingResult
	 * 			   Wrapper of product and its updated stock
	 */
	RestockingResult restockProduct(String productId, int amountToRestock) throws Exception;
}