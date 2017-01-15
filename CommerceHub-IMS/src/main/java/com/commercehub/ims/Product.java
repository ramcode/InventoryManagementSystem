package com.commercehub.ims;

/*
 * @author Ramesh
 * Product class which is used to track a product location and inventory level within 
 * the warehouse
 * Product has properties productId, location and inventory stock level
 */
public class Product {

	private String productId;
	private String location;
	private int stockLevel;

	public Product(String productId, String location, int stockLevel) {
		this.productId = productId;
		this.location = location;
		this.stockLevel = stockLevel;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", location=" + location + ", stockLevel=" + stockLevel + "]";
	}

}
