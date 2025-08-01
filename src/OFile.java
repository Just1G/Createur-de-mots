import java.io.BufferedWriter;
import java.io.IOException;

public class OFile {

    /**
     * create a file containing a table
     * the file is build like a table anf reflects a two-dimensions array
     * Each column represent the current letter
     * Each row represent the next letter
     * The number of each cell represent how many times the next letter follows the current letter
     * Letters are in alphabetical order
     *
     * @param stats the Object containing the statistics
     */
    public void createFileLettersData(Statistics stats){
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("lettersData.csv"));
            //This statement is in try in case there is a problem

            for(int i=0; i<26; i++){
                for(int j=0; j<26; j++){
                    Integer value = stats.arrayStats[i][j]; //stores the value of a cell
                    writer.write(value.toString());         //write the value in the file
                    writer.write(" ");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Create a file containing information about the rows and columns of the first file
     * For each column or row letter, it displays the sum of the column or row
     * @param stats the Object containing the statistics
     */
    public void createFileRowsColumnsData(Statistics stats){
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("rowsColumnsData.csv"));
            //This statement is in try in case there is a problem

            writer.write("Rows" + "\n");
            for (int i=0; i<26; i++){
                writer.write(('a' + i) + " " + stats.nbOfRows[i] + "\n");
            }
            //write "rows" in the file followed by the unicode of each letter and the sum of the current row

            writer.write("Columns" + "\n");
            for (int j=0; j<26; j++){
                writer.write(('a' + j) + " " + stats.nbOfCColumns[j] + "\n");
            }
            //write "columns" in the file followed by the unicode of each letter and the sum of the current row

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
