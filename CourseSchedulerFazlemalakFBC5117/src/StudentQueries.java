import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fazle
 */

public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static ResultSet resultSet;

    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("INSERT into app.student (studentid, firstname, lastname) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static StudentEntry getStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("SELECT studentID, firstname, lastname FROM app.student WHERE studentID = ?");
            getAllStudents.setString(1, studentID);
            resultSet = getAllStudents.executeQuery();
            
            resultSet.next();
            return new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        ArrayList<StudentEntry> faculty = new ArrayList<StudentEntry>();
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("SELECT studentID, firstname, lastname FROM app.student");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                faculty.add(new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
        return faculty;
    }
    
    public static void dropStudent(String studentID)
    {
        connection = DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("delete from app.student where studentid = ?");
            getAllStudents.setString(1, studentID);
            getAllStudents.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
