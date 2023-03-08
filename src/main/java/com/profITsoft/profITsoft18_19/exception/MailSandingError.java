package com.profITsoft.profITsoft18_19.exception;

public class MailSandingError extends RuntimeException{
    public MailSandingError() {
        super();
    }

    public MailSandingError(String message) {
        super(message);
    }
}
