class ClientHandler extends Thread {
    private Socket socket;
    private Connection dbConnection;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream();
             ObjectInputStream ois = new ObjectInputStream(input);
             ObjectOutputStream oos = new ObjectOutputStream(output)) {

            // Receive student ID from the client
            String studentId = (String) ois.readObject();

            // Connect to the database
            connectToDatabase();

            // Retrieve student data
            Student student = getStudentData(studentId);

            // Calculate total marks and CGPA
            if (student != null) {
                double totalMarks = student.getMarksModule1() + student.getMarksModule2() + student.getMarksModule3();
                double avgMarks = totalMarks / 3;
                double cgpa = calculateCGPA(avgMarks);

                // Calculate fees based on some assumption
                double monthlyFee = 1000;  // Assumed monthly fee
                double halfYearlyFee = monthlyFee * 6 * 0.9;  // 10% discount
                double yearlyFee = monthlyFee * 12 * 0.8;  // 20% discount

                // Sending back data to the client
                oos.writeObject(student.getStudentId());
                oos.writeObject(student.getStudentName());
                oos.writeObject(totalMarks);
                oos.writeObject(cgpa);
                oos.writeObject(yearlyFee);  // Assuming yearly payment for simplicity
            } else {
                oos.writeObject("Student not found");
            }

            // Close the database connection
            closeDatabaseConnection();

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectToDatabase() throws SQLException {
        String url = "jdbc:sqlite:students.db";  // SQLite database
        dbConnection = DriverManager.getConnection(url);
    }

    private Student getStudentData(String studentId) throws SQLException {
        String query = "SELECT * FROM students WHERE Student_id = ?";
        try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString("Student_name");
                double marksModule1 = rs.getDouble("Marks_module1");
                double marksModule2 = rs.getDouble("Marks_module2");
                double marksModule3 = rs.getDouble("Marks_module3");

                return new Student(studentId, studentName, marksModule1, marksModule2, marksModule3);
            }
        }
        return null;
    }

    private double calculateCGPA(double avgMarks) {
        if (avgMarks >= 90) {
            return 4.0;
        } else if (avgMarks >= 80) {
            return 3.0;
        } else if (avgMarks >= 60) {
            return 2.0;
        } else if (avgMarks >= 50) {
            return 1.0;
        } else {
            return 0.0;  // CGPA not applicable
        }
    }

    private void closeDatabaseConnection() throws SQLException {
        if (dbConnection != null && !dbConnection.isClosed()) {
            dbConnection.close();
        }
    }
}
