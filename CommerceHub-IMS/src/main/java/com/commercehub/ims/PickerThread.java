/**
 * @author VenkataRamesh
 * Picker class acting as Worker Thread to perform picking of products
 */

package com.commercehub.ims;

import java.util.concurrent.Callable;

public class PickerThread implements Callable<Result> {

	private InventoryManagementSystem invMgtSystem;
	private String productId;
	private int amountToPick;

	/**
	 * constructor to inject invMgtSystem, productId and amount of stock to pick
	 * @param invMgtSystem
	 * @param productId
	 * @param amountToPick
	 */
	public PickerThread(InventoryManagementSystem invMgtSystem, String productId, int amountToPick) {
		this.invMgtSystem = invMgtSystem;
		this.productId = productId;
		this.amountToPick = amountToPick;
	}

	/**
	 * method to call pickProduct method
	 * @return
	 * @throws Exception
	 */
	@Override
	public PickingResult call() throws Exception {
		return invMgtSystem.pickProduct(productId, amountToPick);
	}

}
