public class InvalidGradeException extends Exception {
    private int grade;

    public InvalidGradeException(int grade) {
        super("Invalid grade: " + grade + ". Grade must be between 0 and 100.");
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}