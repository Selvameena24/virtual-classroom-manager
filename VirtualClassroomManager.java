import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

class Classroom {
    String name;
    List<Student> students = new ArrayList<>();
    List<Assignment> assignments = new ArrayList<>();

    Classroom(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "name='" + name + '\'' +
                ", students=" + students.size() +
                ", assignments=" + assignments.size() +
                '}';
    }
}

class Student {
    String id;
    List<Classroom> classrooms = new ArrayList<>();
    Map<Assignment, String> submissions = new HashMap<>(); // Key: Assignment, Value: Submission

    Student(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", classrooms=" + classrooms.size() +
                ", submissions=" + submissions.size() +
                '}';
    }
}

class Assignment {
    String title;
    String description;
    Date dueDate;

    Assignment(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}

public class VirtualClassroomManager {
    List<Classroom> classrooms = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    void addClassroom(String name) {
        Classroom classroom = new Classroom(name);
        classrooms.add(classroom);
        System.out.println("Classroom " + name + " has been created.");
    }

    void addStudent(String id, String className) {
        Student student = new Student(id);
        Classroom classroom = findClassroom(className);
        if (classroom != null) {
            student.classrooms.add(classroom);
            classroom.students.add(student);
            students.add(student);
            System.out.println("Student " + id + " has been enrolled in " + className);
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    void scheduleAssignment(String className, String title, String description, Date dueDate) {
        Classroom classroom = findClassroom(className);
        if (classroom != null) {
            Assignment assignment = new Assignment(title, description, dueDate);
            classroom.assignments.add(assignment);
            for (Student student : classroom.students) {
                student.submissions.put(assignment, ""); // Initialize empty submission
            }
            System.out.println("Assignment " + title + " scheduled for " + className);
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    void submitAssignment(String studentId, String className, String assignmentTitle, String submission) {
        Student student = findStudent(studentId);
        Classroom classroom = findClassroom(className);
        if (student != null && classroom != null) {
            Assignment assignment = findAssignment(classroom, assignmentTitle);
            if (assignment != null) {
                student.submissions.put(assignment, submission);
                System.out.println("Assignment submitted by Student " + studentId + " in " + className);
            } else {
                System.out.println("Assignment not found.");
            }
        } else {
            System.out.println("Student or Classroom not found.");
        }
    }

    void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms available.");
        } else {
            for (Classroom classroom : classrooms) {
                System.out.println(classroom);
            }
        }
    }

    void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    void listAssignments(String className) {
        Classroom classroom = findClassroom(className);
        if (classroom != null) {
            if (classroom.assignments.isEmpty()) {
                System.out.println("No assignments scheduled for " + className);
            } else {
                for (Assignment assignment : classroom.assignments) {
                    System.out.println(assignment);
                }
            }
        } else {
            System.out.println("Classroom " + className + " not found.");
        }
    }

    // Helper methods for finding objects
    Classroom findClassroom(String name) {
        for (Classroom classroom : classrooms) {
            if (classroom.name.equals(name)) {
                return classroom;
            }
        }
        return null;
    }

    Student findStudent(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    Assignment findAssignment(Classroom classroom, String title) {
        for (Assignment assignment : classroom.assignments) {
            if (assignment.title.equals(title)) {
                return assignment;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        VirtualClassroomManager manager = new VirtualClassroomManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nVirtual Classroom Manager");
            System.out.println("1. Add Classroom");
            System.out.println("2. Add Student");
            System.out.println("3. Schedule Assignment");
            System.out.println("4. Submit Assignment");
            System.out.println("5. List Classrooms");
            System.out.println("6. List Students");
            System.out.println("7. List Assignments");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter classroom name: ");
                    String className = scanner.nextLine();
                    manager.addClassroom(className);
                    break;

                case 2:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    manager.addStudent(studentId, className);
                    break;

                case 3:
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    System.out.print("Enter assignment title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter assignment description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter assignment due date (yyyy-MM-dd): ");
                    String dueDateString = scanner.nextLine();
                    try {
                        Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateString);
                        manager.scheduleAssignment(className, title, description, dueDate);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    System.out.print("Enter assignment title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter submission: ");
                    String submission = scanner.nextLine();
                    manager.submitAssignment(studentId, className, title, submission);
                    break;

                case 5:
                    manager.listClassrooms();
                    break;

                case 6:
                    manager.listStudents();
                    break;

                case 7:
                    System.out.print("Enter classroom name: ");
                    className = scanner.nextLine();
                    manager.listAssignments(className);
                    break;

                case 8:
                    exit = true;
                    System.out.println("Exiting Virtual Classroom Manager. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
