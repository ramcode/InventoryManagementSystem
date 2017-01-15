package com.commercehub.ims;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TerminalClient {

	public static final String PRODUCT_LABEL = "PRODUCT";
	public static final String PRODUCT_LOCATION = "LOCATION";
	public int initialProductCount = 0;
	public int totalLocations = 0;
	public int maxStockCount = 0;
	public int pickerCount = 0;
	public int restockerCount = 0;

	Map<String, Product> inventory = null;
	Random rand = null;

	public TerminalClient() {
		initialProductCount = 100;
		totalLocations = 10;
		maxStockCount = 20;
		init();
	}

	public Map<String, Product> getInventory() {
		return inventory;
	}

	public void setInventory(Map<String, Product> inventory) {
		this.inventory = inventory;
	}

	public TerminalClient(int initialProductCount, int totalLocations, int maxStockCount, int pickerCount,
			int restockerCount) {
		this.initialProductCount = initialProductCount;
		this.totalLocations = totalLocations;
		this.maxStockCount = maxStockCount;
		this.pickerCount = pickerCount;
		this.restockerCount = restockerCount;
	}

	private void init() {
		inventory = new HashMap<>();
		rand = new Random(1);
		for (int i = 0; i < initialProductCount; i++) {
			String productId = PRODUCT_LABEL + "_" + (i + 1);
			Product product = new Product(productId, PRODUCT_LOCATION + "_" + rand.nextInt(totalLocations + 1),
					rand.nextInt(maxStockCount + 1));
			inventory.put(productId, product);
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter No of Pickers to Work in Warehouse: ");
		int pickers = sc.nextInt();
		System.out.println("Enter No of Restockers to Work in Warehouse: ");
		int restockers = sc.nextInt();
		TerminalClient client = new TerminalClient();
		InventoryManagementSystem ims = new InventoryManagementSystemImpl(client.getInventory());
		ExecutorService service = Executors.newFixedThreadPool(pickers + restockers);
		List<Future<Result>> results = new ArrayList<>();
		Random rand = new Random(2);
		int noOfTasks = rand.nextInt(client.getInventory().size() + 1);
		for (int i = 0; i < noOfTasks; i++) {
			int worker = rand.nextInt(3);
			String productId = PRODUCT_LABEL + "_" + rand.nextInt(client.getInventory().size() + 1);
			int amountToPickOrRestock = rand.nextInt(client.maxStockCount);
			if (worker == 1) {
				Future<Result> result = service.submit(new PickerThread(ims, productId, amountToPickOrRestock));
				results.add(result);
			} else {
				Future<Result> result = service.submit(new RestockerThread(ims, productId, amountToPickOrRestock));
				results.add(result);
			}

		}

		for (Future<Result> result : results) {
			try {
				result.get().printStatus();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

		service.shutdown();

	}

}
