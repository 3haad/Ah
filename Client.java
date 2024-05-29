import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get student details
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();

        // Validate input
        if (studentId.isEmpty()) {
            System.out.println("Student ID cannot be empty.");
            return;
        }

        // Create and start the client thread
        ClientThread clientThread = new ClientThread(studentId);
        new Thread(clientThread).start();
    }
}







