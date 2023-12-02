/**
 *
 * @author fazle
 */

public class ClassEntry {
    private String semester;
    private String courseCode;
    private int seats;

    public ClassEntry(String semester, String courseCode, int seats) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.seats = seats;
    }
    public String getSemester()
    {
        return this.semester;
    }
    public String getCourseCode()
    {
        return this.courseCode;
    }
    public int getSeats()
    {
        return this.seats;
    }
    
}
