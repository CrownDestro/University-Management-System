package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Course {
    private String name;
    private String courseId;
    private List<Student> enrolledStudents;
    private Teacher teacher;

    public Course(String name, String courseId) {
        this.name = name;
        this.courseId = courseId;
        this.enrolledStudents = new ArrayList<>();
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void enterMarks(String studentId, int marks) {
        for (Student student : enrolledStudents) {
            if (student.getId().equals(studentId)) {
                student.addCourseMark(this.courseId, marks);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Student not enrolled in this course.");
    }
}