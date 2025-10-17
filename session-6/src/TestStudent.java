public class TestStudent {
    public static void main(String[] args) {
        // Membuat beberapa objek Student
        Student s1 = new Student(101, "Alice");
        Student s2 = new Student(102, "Bob");

        // Mengatur GPA
        s1.setGpa(3.8);
        s2.setGpa(4.5);

        // Menampilkan profil
        s1.displayProfile();
        s2.displayProfile();


    }
}
