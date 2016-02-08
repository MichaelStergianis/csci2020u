package uiDemo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDataSource {
    public static ObservableList<Student> getAllStudents() {
        ObservableList<Student> students = FXCollections.observableArrayList();

        students.add(new Student(100100100, "Janet", "Combes", 2.85));
        students.add(new Student(100100101, "Abichal", "Kaur", 1.71));
        students.add(new Student(100100102, "Cecile", "Lalonde", 3.60));
        students.add(new Student(100100103, "Pablo", "Rodriguez", 2.85));


        return students;
    }
}
