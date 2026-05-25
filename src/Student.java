package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Student  {
    private String name;
    private String id;
    private Date dob;
    private String rollNo;
    private String aadharNo;
    private Map<String, Double> courseMarks; // New field to store course marks

    public Student(String name, String id, Date dob, String rollNo, String aadharNo) {
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.rollNo = rollNo;
        this.aadharNo = aadharNo;
        this.courseMarks = new HashMap<>(); // Initialize the map
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public Map<String, Double> getCourseMarks() {
        return courseMarks;
    }

    public void setCourseMarks(Map<String, Double> courseMarks) {
        this.courseMarks = courseMarks;
    }

    public void addCourseMark(String courseId, double mark) {
        this.courseMarks.put(courseId, mark);
    }
}