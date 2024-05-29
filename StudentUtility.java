import java.util.HashSet;
import java.util.Scanner;

class StudentUtility {
    private HashSet<Student> studentSet;

    public StudentUtility() {
        studentSet = new HashSet<>();
    }

    // Method to add a student to the HashSet
    public void addStudent(Student student) {
        if (studentSet.add(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Student already exists.");
        }
    }

    // Method to search for a student by student ID
    public Student searchStudent(String studentId) {
        for (Student student : studentSet) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Method to display student details
    public void displayStudent(Student student) {
        if (student != null) {
            System.out.println("Student ID: " + student.getStudentId());
            System.out.println("Student Name: " + student.getStudentName());
            System.out.println("Total Marks: " + (student.getMarksModule1() + student.getMarksModule2() + student.getMarksModule3()));
            System.out.println("CGPA: " + student.getCgpa());
        } else {
            System.out.println("Student not found.");
        }
    }
}
