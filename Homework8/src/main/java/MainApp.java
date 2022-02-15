import java.io.IOException;
import java.sql.SQLException;


public class MainApp {

    public static void main(String[] args) throws SQLException, IOException {
        UserInterface userInterface = new UserInterface();
        userInterface.runApplication();
    }
}
