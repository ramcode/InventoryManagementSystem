/**
 * 
 */
package com.commercehub.ims;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;



/**
 * @author VenkataRamesh
 *
 */
public class InventoryManagementSystemTest {
	
	static InventoryManagementSystem ims = null;
	static Map<String, Product> inventory = null;
	
	@BeforeClass
	public static void init(){
		inventory = new HashMap<>();
		inventory.put("PRODUCT_1", new Product("PRODUCT_1", "LOC_10",5));
		inventory.put("PRODUCT_2", new Product("PRODUCT_2", "LOC_5",15));
		inventory.put("PRODUCT_3", new Product("PRODUCT_3", "LOC_8",11));
		ims = new InventoryManagementSystemImpl(inventory);
	}
	
	@Test
	public void testPickProduct() throws Exception {
		Result pickResult = ims.pickProduct("PRODUCT_1", 3);
		assertNotNull(pickResult);
		Product product = pickResult.getProduct();
		assertEquals(product.getProductId(), "PRODUCT_1");
		assertEquals(product.getLocation(), "LOC_10");
		assertEquals(product.getStockLevel(), 2);
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testPickProductProductNotFoundException() throws Exception{
		Result pickResult = ims.pickProduct("PRODUCT_4", 10);
		assertNull(pickResult);
	}
	
	@Test(expected = ProductOutofStockException.class)
	public void testPickProductOutofStockException() throws Exception{
		Result pickResultFirst = ims.pickProduct("PRODUCT_2", 15);
		assertNotNull(pickResultFirst);
		Product product = pickResultFirst.getProduct();
		assertEquals(product.getProductId(), "PRODUCT_2");
		assertEquals(product.getStockLevel(),0);
		Result pickResultSecond = ims.pickProduct("PRODUCT_2", 3);
		assertNull(pickResultSecond);
	}
	
	@After
	public void resetProduct2(){
		inventory.get("PRODUCT_2").setStockLevel(15);
	}
	
	@Test(expected = InsufficientStockException.class)
	public void testPickProductInsufficientStockException() throws Exception{
		Result pickResult = ims.pickProduct("PRODUCT_3", 12);
		assertNull(pickResult);
	}
	
	@Test
	public void testRestockProduct() throws Exception{
		int initialStock = inventory.get("PRODUCT_1").getStockLevel();
		Result result = ims.restockProduct("PRODUCT_1", 10);
		assertNotNull(result);
		Product product = result.getProduct();
		assertEquals(product.getStockLevel(), initialStock+10);
	}
	
	
	

}
