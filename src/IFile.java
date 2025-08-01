import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IFile {

    /**
     * @param fileName the name of the file
     * @param stats the Statistics object that stores data
     */
    // Read the file and stores information inside a Statistics object
    public static void readFileAndMakeStats(String fileName, Statistics stats) {
        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(fileName));
            //This statement goes into a try in case there is a problem with the file

            String line;
            while((line = reader.readLine()) != null) { //while a word is read
                stats.analyseWord(line);                //we analyse this word in an Statistics Object
            }

            reader.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
