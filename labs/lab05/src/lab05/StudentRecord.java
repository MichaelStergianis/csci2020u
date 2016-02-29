package lab05;

/**
 * Created by michael on 22/02/16.
 */
public class StudentRecord {
    private String studentID;
    private double midterm;
    private double assignments;
    private double finalExam;
    private double finalMark;
    private String letterGrade;
    private boolean finalMarkSet;

    StudentRecord(String studentID, double assignments, double midterm, double finalExam){
        setStudentID(studentID);
        setAssignments(assignments);
        setMidterm(midterm);
        setFinalExam(finalExam);
        calcFinalMark();
        calcLetterGrade();
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getMidterm() {
        return midterm;
    }

    public void setMidterm(double midterm) {
        if (midterm >= 0.0 && midterm <= 100.0)
            this.midterm = midterm;
    }

    public double getAssignments() {
        return assignments;
    }

    public void setAssignments(double assignments) {
        if (assignments >= 0.0 && assignments <= 100.0)
            this.assignments = assignments;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        if (finalExam >= 0.0 && finalExam <= 100.0)
            this.finalExam = finalExam;
    }

    public double getFinalMark() {
        return finalMark;
    }

    public void calcFinalMark() {
        finalMark = (0.2 * assignments + 0.3 * midterm + 0.5 * finalExam);
        finalMarkSet = true;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void calcLetterGrade() {
        if (!finalMarkSet) return;
        if (finalMark < 50.0) {
            letterGrade = "F";
        } else if (finalMark >= 50.0 && finalMark < 60.0) {
            letterGrade = "D";
        } else if (finalMark >= 60.0 && finalMark < 70.0){
            letterGrade = "C";
        } else if (finalMark >= 70.0 && finalMark < 80.0){
            letterGrade = "B";
        } else if (finalMark >= 80.0){
            letterGrade = "A";
        }
    }

}
