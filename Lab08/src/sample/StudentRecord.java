package sample;

public class StudentRecord {
    private String SID;
    private float midterm;
    private float assignments;
    private float Exam;
    private double Mark;
    private char letterGrade;

    public StudentRecord(String SID, float midterm, float assignments, float Exam) {
        this.SID = SID;
        this.midterm = midterm;
        this.assignments = assignments;
        this.Exam = Exam;
        Mark = (assignments * 0.20) + (midterm * 0.30) + (Exam * 0.50);

        if(Mark  >= 0 && Mark <= 49) {
            letterGrade = 'F';
        }
        else if(Mark >= 50 && Mark <= 59) {
            letterGrade = 'D';
        }
        else if(Mark >= 60 && Mark <= 69) {
            letterGrade = 'C';
        }
        else if(Mark >= 70 && Mark <= 79) {
            letterGrade = 'B';
        }
        else {
            letterGrade = 'A';
        }
    }

    public String getSID(){return SID;}
    public float getAssignments(){return assignments;}
    public float getMidterm(){return midterm;}
    public float getExam(){return Exam;}
    public double getMark(){return Mark;}
    public char getLetterGrade(){return letterGrade;}

    public String toString(){
        return SID + "," + assignments + "," + midterm + "," + Exam;
    }
}
