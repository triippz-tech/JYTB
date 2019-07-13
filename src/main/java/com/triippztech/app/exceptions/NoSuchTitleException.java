package com.triippztech.app.exceptions;

import org.openqa.selenium.NoSuchElementException;

public class NoSuchTitleException extends NoSuchElementException {
    public NoSuchTitleException(String reason) {
        super(reason);
    }

    public NoSuchTitleException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
