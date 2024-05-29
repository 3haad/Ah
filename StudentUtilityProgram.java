public class StudentUtilityProgram {
    public static void main(String[] args) {
        StudentUtility utility = new StudentUtility();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks for Module 1: ");
                    double marks1 = scanner.nextDouble();
                    System.out.print("Enter Marks for Module 2: ");
                    double marks2 = scanner.nextDouble();
                    System.out.print("Enter Marks for Module 3: ");
                    double marks3 = scanner.nextDouble();
                    System.out.print("Enter CGPA: ");
                    double cgpa = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline

                    Student student = new Student(id, name, marks1, marks2, marks3, cgpa);
                    utility.addStudent(student);
                    break;
                case 2:
                    System.out.print("Enter Student ID to search: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = utility.searchStudent(searchId);
                    utility.displayStudent(foundStudent);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }
}
