import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fazle
 */

public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseList;
    private static ResultSet resultSet;

    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("INSERT into app.course (courseCode, description) values (?, ?)");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2, course.getCourseDescription());

            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
     public static ArrayList<String> getAllCourseCodes()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try
        {
            getCourseList = connection.prepareStatement("SELECT coursecode FROM app.course");
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                courseCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
     
     public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try
        {
            getCourseList = connection.prepareStatement("SELECT app.course.CourseCode, description, seats " +
            "FROM app.course, app.class WHERE semester = ? AND app.course.CourseCode = app.class.courseCode " +
            "ORDER BY app.course.CourseCode");
            
            getCourseList.setString(1, semester);
            resultSet = getCourseList.executeQuery();
            
            while(resultSet.next())
            {
                courses.add(new CourseEntry("",resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
    }
}
