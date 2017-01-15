package com.commercehub.ims;

/**
 * @author VenkataRamesh
 *         Exception class defined to handle InsufficientStockException
 */


public class InsufficientStockException extends Exception {
    private static final long serialVersionUID = 1L;


    public InsufficientStockException() {
        super();
    }

    public InsufficientStockException(String message) {
        super(message);
    }

    public InsufficientStockException(String message, Throwable e) {
        super(message, e);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
