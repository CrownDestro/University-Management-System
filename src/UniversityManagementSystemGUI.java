package test;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UniversityManagementSystemGUI {
    private UniversityManagementSystem ums;

    public UniversityManagementSystemGUI() {
        this.ums = new UniversityManagementSystem();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("University Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));  // 11 options + exit

        JButton addStudentButton = new JButton("Add Student");
        JButton addTeacherButton = new JButton("Add Teacher");
        JButton createCourseButton = new JButton("Create Course");
        JButton enrollStudentButton = new JButton("Enroll Student to Course");
        JButton assignTeacherButton = new JButton("Assign Teacher to Course");
        JButton enterMarksButton = new JButton("Enter Marks");
        JButton viewStudentCoursesButton = new JButton("View Student list and Course they are in");
        JButton viewTeacherCoursesButton = new JButton("View Teacher list and Course they are in");
        JButton viewCourseListButton = new JButton("View Course List");
        JButton generateStudentReportButton = new JButton("Generate Student Report");
        JButton exitButton = new JButton("Exit");

        addStudentButton.addActionListener(e -> addStudent());
        addTeacherButton.addActionListener(e -> addTeacher());
        createCourseButton.addActionListener(e -> createCourse());
        enrollStudentButton.addActionListener(e -> enrollStudentToCourse());
        assignTeacherButton.addActionListener(e -> assignTeacherToCourse());
        enterMarksButton.addActionListener(e -> enterMarks());
        viewStudentCoursesButton.addActionListener(e -> ums.viewStudentCourseAssignments());
        viewTeacherCoursesButton.addActionListener(e -> ums.printTeacherDetails());
        viewCourseListButton.addActionListener(e -> ums.printCourseDetails());
        generateStudentReportButton.addActionListener(e -> generateStudentReport());
        exitButton.addActionListener(e -> System.exit(0));

        panel.add(addStudentButton);
        panel.add(addTeacherButton);
        panel.add(createCourseButton);
        panel.add(enrollStudentButton);
        panel.add(assignTeacherButton);
        panel.add(enterMarksButton);
        panel.add(viewStudentCoursesButton);
        panel.add(viewTeacherCoursesButton);
        panel.add(viewCourseListButton);
        panel.add(generateStudentReportButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addStudent() {
    	JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField dobField = new JTextField();
        JTextField rollNoField = new JTextField();
        JTextField aadharNoField = new JTextField();

        Object[] fields = {
            "Name:", nameField,
            "ID:", idField,
            "Date of Birth (YYYY-MM-DD):", dobField,
            "Roll No:", rollNoField,
            "Aadhar No:", aadharNoField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String id = idField.getText();
                Date dob = parseDate(dobField.getText());
                String rollNo = rollNoField.getText();
                String aadharNo = aadharNoField.getText();
                
                if (name.isEmpty() || id.isEmpty() || dob == null || rollNo.isEmpty() || aadharNo.isEmpty()) {
                    throw new IllegalArgumentException("Input fields cannot be empty.");
                }
                
                Student student = new Student(name, id, dob, rollNo, aadharNo);
                ums.addStudent(student);
                JOptionPane.showMessageDialog(null, "Student added successfully.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    private void addTeacher() {
    	JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField designationField = new JTextField();

        Object[] fields = {
            "Name:", nameField,
            "ID:", idField,
            "Designation:", designationField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Teacher", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String id = idField.getText();
                String designation = designationField.getText();
                
                if (name.isEmpty() || id.isEmpty() || designation.isEmpty()) {
                    throw new IllegalArgumentException("Input fields cannot be empty.");
                }
                
                Teacher teacher = new Teacher(name, id, designation);
                ums.addTeacher(teacher);
                JOptionPane.showMessageDialog(null, "Teacher added successfully.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    private void createCourse() {
    	JTextField nameField = new JTextField();
        JTextField idField = new JTextField();

        Object[] fields = {
            "Course Name:", nameField,
            "Course ID:", idField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Create Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String id = idField.getText();
                
                if (name.isEmpty() || id.isEmpty()) {
                    throw new IllegalArgumentException("Input fields cannot be empty.");
                }
                
                Course course = new Course(name, id);
                ums.createCourse(course);
                JOptionPane.showMessageDialog(null, "Course created successfully.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    private void enrollStudentToCourse() {
        JTextField studentIdField = new JTextField();
        JTextField courseIdField = new JTextField();

        Object[] fields = {
            "Student ID:", studentIdField,
            "Course ID:", courseIdField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Enroll Student to Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String studentId = studentIdField.getText();
            String courseId = courseIdField.getText();
            ums.enrollStudentToCourse(studentId, courseId);
            JOptionPane.showMessageDialog(null, "Student enrolled in course successfully.");
        }
    }

    private void assignTeacherToCourse() {
        JTextField teacherIdField = new JTextField();
        JTextField courseIdField = new JTextField();

        Object[] fields = {
            "Teacher ID:", teacherIdField,
            "Course ID:", courseIdField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Assign Teacher to Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String teacherId = teacherIdField.getText();
            String courseId = courseIdField.getText();
            ums.assignTeacherToCourse(teacherId, courseId);
            JOptionPane.showMessageDialog(null, "Teacher assigned to course successfully.");
        }
    }

    private void enterMarks() {
        JTextField courseIdField = new JTextField();
        JTextField studentIdField = new JTextField();
        JTextField marksField = new JTextField();

        Object[] fields = {
            "Course ID:", courseIdField,
            "Student ID:", studentIdField,
            "Marks:", marksField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Enter Marks", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String courseId = courseIdField.getText();
            String studentId = studentIdField.getText();
            int marks = Integer.parseInt(marksField.getText());
            ums.enterMarks(courseId, studentId, marks);
            JOptionPane.showMessageDialog(null, "Marks entered successfully.");
        }
    }

    private void generateStudentReport() {
        String studentId = JOptionPane.showInputDialog("Enter student ID:");
        if (studentId != null && !studentId.trim().isEmpty()) {
            ums.generateStudentReport(studentId);
        }
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Please enter in YYYY-MM-DD format.");
            return null;
        }
    }
}