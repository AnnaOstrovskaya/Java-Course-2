import configuration.ApplicationGlobalState;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller = new Controller();

    public void runApplication() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("Введите ответ: 1 - Получить текущую погоду, " +
                    "2 - Получить погоду на следующие 5 дней, " +
                    "3 - Получить погоду из базы, или " +
                    "4 - Чтобы завершить работу");
            String result = scanner.nextLine();

            try {
                validateUserInput(result);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            checkIsExit(result);

            if (result.equals("1") || result.equals("2")) {
                System.out.println("Введите название города на английском языке");
                String city = scanner.nextLine();
                setGlobalCity(city);
            }

            try {
                notifyController(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void checkIsExit(String result) throws IOException, SQLException {
        if (result.toLowerCase().equals("4")) {
            controller.getExit();
        }
    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    private void validateUserInput(String userInput) throws IOException {
        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Incorrect user input: expected one digit as answer, but actually get " + userInput);
        }
        int answer;
        try {
            answer = Integer.parseInt(userInput);
            if (answer >= 5){
                throw new IOException("Incorrect user input: character must be less then 5!");
            }
        } catch (NumberFormatException e) {
            throw new IOException("Incorrect user input: character is not numeric!");
        }
    }

    private void notifyController(String input) throws IOException, SQLException {
        controller.onUserInput(input);
    }

}
