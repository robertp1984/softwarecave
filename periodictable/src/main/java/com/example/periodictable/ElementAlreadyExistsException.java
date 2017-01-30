
package com.example.periodictable;

public class ElementAlreadyExistsException extends Exception {

    public ElementAlreadyExistsException(String msg) {
        super(msg);
    }

    public ElementAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
