public class Grade {
    private String studentId;
    private String courseCode;
    private String gradeValue;

    public Grade(String studentId, String courseCode, String gradeValue) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.gradeValue = gradeValue;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(String gradeValue) {
        this.gradeValue = gradeValue;
    }

    @Override
    public String toString() {
        return studentId + "," + courseCode + "," + gradeValue;
    }
}
