class ClientThread implements Runnable {
    private String studentId;

    public ClientThread(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(Client.SERVER_ADDRESS, Client.SERVER_PORT);
             OutputStream output = socket.getOutputStream();
             InputStream input = socket.getInputStream();
             ObjectOutputStream oos = new ObjectOutputStream(output);
             ObjectInputStream ois = new ObjectInputStream(input)) {

            // Send student ID to the server
            oos.writeObject(studentId);

            // Receive results from the server
            String receivedStudentId = (String) ois.readObject();
            String studentName = (String) ois.readObject();
            double totalMarks = (double) ois.readObject();
            double cgpa = (double) ois.readObject();
            double fee = (double) ois.readObject();

            // Display results
            System.out.println("Student ID: " + receivedStudentId);
            System.out.println("Student Name: " + studentName);
            System.out.println("Total Marks: " + totalMarks);
            System.out.println("CGPA: " + cgpa);
            System.out.println("Final Fee Payable: " + fee);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}