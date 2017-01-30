
package com.example.periodictable;

public class ElementNotFoundException extends Exception {

    public ElementNotFoundException(String msg) {
        super(msg);
    }

    public ElementNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
