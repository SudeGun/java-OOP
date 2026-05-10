/**
 * A container class for custom exceptions used in the student management system.
 */
public class AllExceptions {

    /**
     * Thrown when a person record has an invalid type ( not "S" for Student or "F" for Faculty).
     */
    public static class InvalidPersonException extends Exception{
        public InvalidPersonException(String message){
            super(message);
        }
    }

    /**
     * Thrown when an expected person does not exist.
     */
    public static class NonExistingPersonException extends Exception{
        public NonExistingPersonException(String message){
            super(message);
        }
    }

    /**
     * Thrown when a referenced program code does not exist.
     */
    public static class InvalidProgramNameException extends Exception{
        public InvalidProgramNameException(String message){
            super(message);
        }
    }

    /**
     * Thrown when a referenced course cannot be found.
     */
    public static class NonExistingCourseException extends Exception{
        public NonExistingCourseException(String message) {
            super(message);
        }
    }

    /**
     * Thrown when an invalid letter grade is encountered (e.g., not A, B, C, etc.).
     */
    public static class InvalidLetterGrade extends Exception{
        public InvalidLetterGrade(String message){
            super(message);
        }
    }
}
