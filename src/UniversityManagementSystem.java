package test;



import java.util.*;
 
import javax.swing.JOptionPane;

public class UniversityManagementSystem {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Course> courses;

    public UniversityManagementSystem() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void viewStudentCourseAssignments() {
        StringBuilder message = new StringBuilder("\nStudent Course Assignments:\n");
        for (Student student : students) {
            message.append("Student Name: ").append(student.getName()).append("\n");
            message.append("Student ID: ").append(student.getId()).append("\n");
            message.append("Courses Enrolled:\n");
            boolean enrolledCoursesExist = false;
            for (Course course : courses) {
                if (course.getEnrolledStudents().contains(student)) {
                    message.append("- ").append(course.getName()).append("\n");
                    enrolledCoursesExist = true;
                }
            }
            if (!enrolledCoursesExist) {
                message.append("- No courses enrolled\n");
            }
            message.append("-----------------------\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void printTeacherDetails() {
        StringBuilder message = new StringBuilder("\nTeacher Details:\n");
        for (Teacher teacher : teachers) {
            message.append("Teacher Name: ").append(teacher.getName()).append("\n");
            message.append("Courses Taught:\n");
            for (Course course : courses) {
                if (course.getTeacher() != null && course.getTeacher().equals(teacher)) {
                    message.append("- ").append(course.getName()).append("\n");
                }
            }
            message.append("-----------------------\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void printCourseDetails() {
        StringBuilder message = new StringBuilder("\nCourse Details:\n");
        for (Course course : courses) {
            message.append("Course Name: ").append(course.getName()).append("\n");
            message.append("Teacher: ").append(course.getTeacher() != null ? course.getTeacher().getName() : "None").append("\n");
            message.append("Enrolled Students:\n");
            for (Student student : course.getEnrolledStudents()) {
                message.append("- ").append(student.getName()).append("\n");
            }
            message.append("-----------------------\n");
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void createCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudentToCourse(String studentId, String courseId) {
        Student student = findStudentById(studentId);
        Course course = findCourseById(courseId);

        if (student != null && course != null) {
            course.enrollStudent(student);
        } else {
            JOptionPane.showMessageDialog(null, "Student or course not found.");
        }
    }

    public void assignTeacherToCourse(String teacherId, String courseId) {
        Teacher teacher = findTeacherById(teacherId);
        Course course = findCourseById(courseId);

        if (teacher != null && course != null) {
            course.assignTeacher(teacher);
        } else {
            JOptionPane.showMessageDialog(null, "Teacher or course not found.");
        }
    }

    public void enterMarks(String courseId, String studentId, int marks) {
        Course course = findCourseById(courseId);
        if (course != null) {
            course.enterMarks(studentId, marks);
        } else {
            JOptionPane.showMessageDialog(null, "Course not found.");
        }
    }

    public void generateStudentReport(String studentId) {
        Student student = findStudentById(studentId);
        
        if (student != null) {
            StringBuilder message = new StringBuilder("\nStudent Report:\n");
            message.append("Name: ").append(student.getName()).append("\n");
            message.append("ID: ").append(student.getId()).append("\n");
            message.append("Roll No: ").append(student.getRollNo()).append("\n");
            message.append("Aadhar No: ").append(student.getAadharNo()).append("\n");
            message.append("Date of Birth: ").append(student.getDob()).append("\n");
            message.append("Courses and Marks:\n");

            Map<String, Double> courseMarks = student.getCourseMarks();
            if (courseMarks.isEmpty()) {
                message.append("- No marks available\n");
            } else {
                for (Map.Entry<String, Double> entry : courseMarks.entrySet()) {
                    String courseId = entry.getKey();
                    double marks = entry.getValue();
                    Course course = findCourseById(courseId);
                    String courseName = course != null ? course.getName() : "Unknown Course";
                    message.append("- ").append(courseName).append(" (").append(courseId).append("): ").append(marks).append("\n");
                }
            }
            message.append("-----------------------\n");
            JOptionPane.showMessageDialog(null, message.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Student not found.");
        }
    }

    // Utility method to find a student by ID
    private Student findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Utility method to find a teacher by ID
    private Teacher findTeacherById(String id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return teacher;
            }
        }
        return null;
    }

    // Utility method to find a course by ID
    private Course findCourseById(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null;
    }
}
