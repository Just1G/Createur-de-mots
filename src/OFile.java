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
    public void createLetterTableSumFile(Statistics stats){
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("data/letterTableSum.csv"));
            //This statement is in try in case there is a problem

            for(int i=0; i<26; i++){
                for(int j=0; j<26; j++){
                    Integer value = stats.nbOfSuccessorTable[i][j]; //stores the value of a cell
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
    public void createRowsColumnsSumFile(Statistics stats){
        try {
            BufferedWriter writerRows = new BufferedWriter(new java.io.FileWriter("data/rowsLetterSum.csv"));
            BufferedWriter writerColumns = new BufferedWriter(new java.io.FileWriter("data/columnsLetterSum.csv"));
            //This statement is in try in case there is a problem

            for (int i=0; i<stats.characterNumber; i++){
                writerRows.write(('a' + i) + " " + stats.nbOfSumRows[i] + "\n");
            }
            //write "rows" in the file followed by the unicode of each letter and the sum of the current row

            for (int j=0; j<stats.characterNumber; j++){
                writerColumns.write(('a' + j) + " " + stats.nbOfSumColumns[j] + "\n");
            }
            //write "columns" in the file followed by the unicode of each letter and the sum of the current row

            writerColumns.close();
            writerRows.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNextLetterProbabilityFile(Statistics stats){
        try {
            BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("data/nextLetterProbability.csv"));
            //This statement is in try in case there is a problem

            for(int i=0; i<stats.characterNumber; i++){
                for(int j=0; j<stats.characterNumber; j++){
                    Float value = stats.SuccessorProbabiltyTable[i][j];
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
}
