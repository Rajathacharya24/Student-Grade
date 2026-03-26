import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeManager {

    static Map<String, Student> students = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println("  📚 Student Grade Manager   ");
        System.out.println("==============================");

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Grades");
            System.out.println("4. Get Average & Remark");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Choose (1-6): ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> addGrade();
                    case 3 -> viewGrades();
                    case 4 -> getAverage();
                    case 5 -> listStudents();
                    case 6 -> {
                        System.out.println("👋 Goodbye!");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("⚠️ Invalid choice! Enter 1-6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
            } finally {
                System.out.println("------------------------------");
            }
        }
    }

    // ─── Add Student ───────────────────────────────────────
    static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        try {
            if (students.containsKey(name.toLowerCase())) {
                throw new IllegalStateException("Student '" + name + "' already exists!");
            }
            Student s = new Student(name);
            students.put(name.toLowerCase(), s);
            System.out.println("✅ Student '" + name + "' added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("⚠️ Warning: " + e.getMessage());
        } finally {
            System.out.println("Add student operation complete.");
        }
    }

    // ─── Add Grade ─────────────────────────────────────────
    static void addGrade() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        try {
            Student s = students.get(name.toLowerCase());
            if (s == null) {
                throw new IllegalStateException("Student '" + name + "' not found!");
            }

            System.out.print("Enter grade (0-100): ");
            int grade = Integer.parseInt(sc.nextLine());

            s.addGrade(grade);

        } catch (InvalidGradeException e) {
            System.out.println("❌ Grade Error: " + e.getMessage());
            System.out.println("   You entered: " + e.getGrade());
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Grade must be a number, not text!");
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("Add grade operation complete.");
        }
    }

    // ─── View Grades ───────────────────────────────────────
    static void viewGrades() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        try {
            Student s = students.get(name.toLowerCase());
            if (s == null) throw new IllegalStateException("Student '" + name + "' not found!");
            s.displayGrades();

        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("View grades operation complete.");
        }
    }

    // ─── Get Average ───────────────────────────────────────
    static void getAverage() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        try {
            Student s = students.get(name.toLowerCase());
            if (s == null) throw new IllegalStateException("Student '" + name + "' not found!");

            double avg = s.getAverage();
            System.out.printf("📈 Average for %s: %.2f%n", s.getName(), avg);
            System.out.println("🏷️  Remark: " + s.getRemark());

        } catch (ArithmeticException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            System.out.println("Get average operation complete.");
        }
    }

    // ─── List All Students ─────────────────────────────────
    static void listStudents() {
        try {
            if (students.isEmpty()) {
                throw new IllegalStateException("No students added yet!");
            }
            System.out.println("👥 All Students:");
            int i = 1;
            for (String key : students.keySet()) {
                System.out.println("  " + i++ + ". " + students.get(key).getName());
            }
        } catch (IllegalStateException e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }
}