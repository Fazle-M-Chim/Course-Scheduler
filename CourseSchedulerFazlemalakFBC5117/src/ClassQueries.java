import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fazle
 */

public class ClassQueries {
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getClassList;
    private static ResultSet resultSet;

    public static ArrayList<ClassEntry> getAllClass(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<ClassEntry> classes = new ArrayList<>();
        try {
            getClassList = connection.prepareStatement("SELECT semester, coursecode, seats FROM app.class WHERE semester = ?");
            getClassList.setString(1, semester);
            resultSet = getClassList.executeQuery();

            while (resultSet.next()) {
                classes.add(new ClassEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static ArrayList<ClassEntry> getAllClassSeats(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<ClassEntry> classes = new ArrayList<>();
        try {
            getClassList = connection.prepareStatement("SELECT seats FROM app.class WHERE semester = ? ORDER BY CourseCode");
            getClassList.setString(1, semester);
            resultSet = getClassList.executeQuery();

            while (resultSet.next()) {
                classes.add(new ClassEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return classes;
    }
    
    public static void addClass(ClassEntry classEntry) {
        connection = DBConnection.getConnection();
        try {
            addClass = connection.prepareStatement("INSERT INTO app.class (semester, courseCode, seats) VALUES (?, ?, ?)");
            addClass.setString(1, classEntry.getSemester());
            addClass.setString(2, classEntry.getCourseCode());
            addClass.setInt(3, classEntry.getSeats());

            addClass.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllClasses() {
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<>();
        try {
            getClassList = connection.prepareStatement("SELECT semester, coursecode, seats FROM app.class WHERE semester = ?");
            resultSet = getClassList.executeQuery();

            while (resultSet.next()) {
                courseCodes.add(resultSet.getString(2)); 
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return courseCodes;

    }
    public static int getClassSeats(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getClassList = connection.prepareStatement("SELECT seats FROM app.class WHERE semester = ? AND coursecode = ?");
            getClassList.setString(1, semester);
            getClassList.setString(2, courseCode);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
                count = resultSet.getInt(1);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    public static ArrayList<String> getAllClassCodes(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> classCodes = new ArrayList<String>();
        try
        {
            getClassList = connection.prepareStatement("SELECT coursecode FROM app.class WHERE semester = ?");
            getClassList.setString(1, semester);
            resultSet = getClassList.executeQuery();
            
            while(resultSet.next())
            {
                classCodes.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classCodes;
    }
}
