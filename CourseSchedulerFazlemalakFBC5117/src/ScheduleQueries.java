import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 
 * @author fazle
 */

public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static ResultSet resultSet;

    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, studentid, coursecode, status, timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(3, entry.getCourseCode());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        ArrayList<ScheduleEntry> faculty = new ArrayList<ScheduleEntry>();
        connection = DBConnection.getConnection();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where studentid = ? and semester = ?");
            getScheduleByStudent.setString(1, studentID);
            getScheduleByStudent.setString(2, semester);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                String getSem = resultSet.getString(1);
                String getCoursecode = resultSet.getString(2);
                String getID = resultSet.getString(3);
                String getStatus = resultSet.getString(4);
                //sql.Timestamp getSem = resultSet.getTimestamp(5);
                faculty.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5)));
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
        return faculty;
    }

    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        int count = 0;
        connection = DBConnection.getConnection();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid from app.schedule where semester = ? and courseCode = ?");
            getScheduleByStudent.setString(1, currentSemester);
            getScheduleByStudent.setString(2, courseCode);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                count++;
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }

    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> retList = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select studentid, status, timestamp from app.schedule "
                    + "where semester = ? and courseCode = ? order by timestamp");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, courseCode);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                retList.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), resultSet.getString(2), resultSet.getTimestamp(3)));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return retList;
    }
}
