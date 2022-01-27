package dev.com.thejungle.customexception;

public class TooManyCharacters extends RuntimeException {
    public TooManyCharacters() {
        super("Invalid Input Exception");
    }

    public TooManyCharacters(String message) {
        super(message);
    }
}
