public class Student {


    private int studentId;
    private String name;
    private double gpa;

    // 2. Constructor
    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = 0.0;
    }

    // 3. Getter dan Setter
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        } else {
            System.out.println("Invalid GPA. It must be between 0.0 and 4.0");
        }
    }

    // 4. displayProfile method
    public void displayProfile() {
        System.out.println("=== Student Profile ===");
        System.out.println("ID   : " + studentId);
        System.out.println("Name : " + name);
        System.out.println("GPA  : " + gpa);
        System.out.println();
    }
}
