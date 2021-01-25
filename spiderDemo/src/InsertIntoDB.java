import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertIntoDB {
    public void insert(ArrayList<String> data)
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection connection= DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"\\db\\result.db");
            Statement statement=connection.createStatement();
            statement.execute("DELETE FROM result");
            for(int i=0;i<data.size();i++)
            {
                statement.execute(data.get(i));
            }
            statement.close();
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

