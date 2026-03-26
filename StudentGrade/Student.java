import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Integer> grades;

    public Student(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty!");
        }
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addGrade(int grade) throws InvalidGradeException {
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException(grade);
        }
        grades.add(grade);
        System.out.println("✅ Grade " + grade + " added for " + name);
    }

    public double getAverage() {
        if (grades.isEmpty()) {
            throw new ArithmeticException("No grades available to calculate average!");
        }
        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.size();
    }

    public String getRemark() {
        double avg = getAverage();
        if (avg >= 90) return "Excellent";
        else if (avg >= 75) return "Good";
        else if (avg >= 50) return "Average";
        else return "Fail";
    }

    public void displayGrades() {
        if (grades.isEmpty()) {
            System.out.println(name + " has no grades yet.");
            return;
        }
        System.out.println("📊 Grades for " + name + ": " + grades);
    }

    public String getName() { return name; }
}