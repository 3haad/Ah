class Student {
    private String studentId;
    private String studentName;
    private double marksModule1;
    private double marksModule2;
    private double marksModule3;
    private double cgpa;

    public Student(String studentId, String studentName, double marksModule1, double marksModule2, double marksModule3, double cgpa) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marksModule1 = marksModule1;
        this.marksModule2 = marksModule2;
        this.marksModule3 = marksModule3;
        this.cgpa = cgpa;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getMarksModule1() {
        return marksModule1;
    }

    public double getMarksModule2() {
        return marksModule2;
    }

    public double getMarksModule3() {
        return marksModule3;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return studentId != null ? studentId.equals(student.studentId) : student.studentId == null;
    }

    @Override
    public int hashCode() {
        return studentId != null ? studentId.hashCode() : 0;
    }
}
