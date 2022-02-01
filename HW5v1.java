import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HW5v1 {

    public static void main(String[] args) {

        AppData appData = new AppData(new String[]{}, new int[][]{});
        appData.readFromCsv("test.csv");
        appData.writeToCsv("out.csv");

    }

    public static class AppData {
        private String[] header;
        private int[][] data;

        public AppData(String[] header, int[][] data) {
            this.header = header;
            this.data = data;
        }

        public void readFromCsv(String csvFilename) {
            ArrayList<String> linesFromCsv = new ArrayList<String>();
            try(BufferedReader br = new BufferedReader(new FileReader(csvFilename))) {
                String line = br.readLine();
                while (line != null) {
                    linesFromCsv.add(line);
                    line = br.readLine();
                }
            } catch (IOException e) {
                System.out.println(e.toString());
            }

            this.data = new int[linesFromCsv.size()-1][];

            for(int lineNumber = 0;lineNumber < linesFromCsv.size(); lineNumber++)
            {
                String[] csvRowValues = linesFromCsv.get(lineNumber).split(";");
                if (lineNumber == 0) {
                    this.header = csvRowValues;
                } else {
                    int[] convertedRow = Arrays.stream(csvRowValues).mapToInt(Integer::parseInt).toArray();
                    this.data[lineNumber-1] = convertedRow;
                }
            }

        }

        public void writeToCsv(String filename) {
            String separator = ";";

            ArrayList<String> linesToWrite = new ArrayList<String>();

            linesToWrite.add(String.join(separator, this.header));

            for (int[] dataRow: this.data) {

                String[] convertedRow = Arrays.stream(dataRow)
                        .mapToObj(String::valueOf)
                        .toArray(String[]::new);
                linesToWrite.add(String.join(separator, convertedRow));
            }

            File outputFile = new File(filename);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

                for (String outputRow: linesToWrite) {
                    bw.write(outputRow);
                    bw.newLine();
                }
                bw.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

}

