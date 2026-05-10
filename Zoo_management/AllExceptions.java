/**
 * A container class for all custom exception types used in the animal feeding and visitation system.
 */
public class AllExceptions {
    /**
     * Exception thrown when there is not enough food available to feed an animal.
     */
    public static class NotEnoughFoodException extends Exception {
        public NotEnoughFoodException(String message) {
            super(message);
        }
    }
    /**
     * Exception thrown when a visitor attempts to feed an animal, which is not allowed.
     */
    public static class VisitorFeedException extends Exception {
        public VisitorFeedException(String message) {
            super(message);
        }
    }
    /**
     * Exception thrown when a referenced input (person or animal) does not exist in the system.
     */
    public static class NonexistingInputException extends Exception {
        public NonexistingInputException (String message) {
            super(message);
        }
    }
}
