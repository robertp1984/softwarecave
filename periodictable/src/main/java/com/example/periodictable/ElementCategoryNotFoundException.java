
package com.example.periodictable;

public class ElementCategoryNotFoundException extends Exception {

    public ElementCategoryNotFoundException(String msg) {
        super(msg);
    }

    public ElementCategoryNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
