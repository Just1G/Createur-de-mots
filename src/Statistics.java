import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Statistics {
    protected int nbWords;
    protected int totalWordsLength = 0;
    public int[][] nbOfSuccessorTable;
    public int[] nbOfFirstLetter;
    public int[] nbOfLastLetter;
    public float[][] SuccessorProbabiltyTable;
    public float[] nbOfSumColumns;
    public float[] nbOfSumRows;
    protected boolean hasRowsColumnsStats;
    protected float[] starterProbability;
    protected float[] finisherProbability;
    protected List<Character> charOrder = new ArrayList<>();
    protected HashMap<Character,Integer> characterSet = new HashMap<>();
    int characterNumber;



    /**
     * Initialise every foo that needs to
     */
    Statistics() {
        nbWords = 0;
        totalWordsLength = 0;
        hasRowsColumnsStats = false;
        characterNumber =0;

    }

    public void getNbOfCharacters(String line)
    {
        String aWord = line.toLowerCase();
        for(char aChar : aWord.toCharArray()){
            if(!characterSet.containsKey(aChar)){
                characterSet.put(aChar,characterNumber++);
                charOrder.add(aChar);
            }
        }
    }

    public void createTables(){
        nbOfSuccessorTable = new int[characterNumber][characterNumber];
        nbOfFirstLetter = new int[characterNumber];
        nbOfLastLetter = new int[characterNumber];
        SuccessorProbabiltyTable  = new float[characterNumber][characterNumber];
        nbOfSumColumns = new float[characterNumber];
        nbOfSumRows = new float[characterNumber];
        starterProbability = new float[characterNumber];
        finisherProbability = new float[characterNumber];
    }

    /**
     * @param aWord One word of the file
     */
    public void analyseWord(String aWord){
        aWord = aWord.toLowerCase();

        //get some data
        nbWords++;
        totalWordsLength+=aWord.length();



        //get statistics about the first and last letter of each word
        Character firstChar = aWord.charAt(0);
        if(characterSet.containsKey(firstChar))
        {
            Integer indexFirst = characterSet.get(firstChar);
            nbOfFirstLetter[indexFirst]++;
        }

        Character lastChar = aWord.charAt(aWord.length()-1);
        if(characterSet.containsKey(lastChar))
        {
            Integer indexLast = characterSet.get(lastChar);
            nbOfLastLetter[indexLast]++;
        }

        for(int j=0;j<aWord.length()-1;j++){
            Character currentLetter =aWord.charAt(j);
            Character nextLetter =aWord.charAt(j+1);

            int current;
            if(characterSet.containsKey(currentLetter))
            {
                current = characterSet.get(currentLetter);
            }
            else {
                continue;
            }

            int next;
            if(characterSet.containsKey(nextLetter))
            {
                next = characterSet.get(nextLetter);
            }
            else {
                continue;
            }

            nbOfSuccessorTable[current][next]++;
        }
    }

    /**
     * Create data for the file rwsColumnsData.csv
     */
    public void createRowsColumnsStatistics(){
        hasRowsColumnsStats = true;
        for(int i=0; i<characterNumber; i++){
            for(int j=0; j<characterNumber; j++){
                nbOfSumColumns[i]+=nbOfSuccessorTable[i][j]; //make the sum of the current row
                nbOfSumRows[j]+=nbOfSuccessorTable[i][j]; //make the sum of the current column
            }
        }
    }
    

    public void createSuccessorProbabiltyStatistics(){
        for(int i=0; i<characterNumber; i++){
            starterProbability[i]= (float)nbOfFirstLetter[i]/nbWords;
            finisherProbability[i]= (float)nbOfLastLetter[i]/nbWords;
        }

        for(int i=0; i<characterNumber; i++){
            for(int j=0; j<characterNumber; j++){
                Float value = nbOfSuccessorTable[i][j]/nbOfSumColumns[i];
                SuccessorProbabiltyTable[i][j] = value;
            }
        }

    }


    /**
     * Displays the statistics in the terminal
     */
    public void displayStatistics(){
        for(int i=0; i<characterNumber; i++){
            for(int j=0; j<characterNumber; j++){
                System.out.print(nbOfSuccessorTable[i][j] + " "); //print a cell
            }
            System.out.println(); //at the end of a row, next line
        }

        if(hasRowsColumnsStats) {
            //if the statistics were made for the Rows and Columns





            System.out.println("Rows :");
            for (char myKey : characterSet.keySet()) {
                System.out.print(myKey + " ");
                System.out.println(nbOfSumRows[characterSet.get(myKey)]);

            }
            //print each letter and its value for the row

            System.out.println("Columns :");
            for (char myKey : characterSet.keySet()) {
                System.out.print(myKey + " ");
                System.out.println(nbOfSumColumns[characterSet.get(myKey)]);
            }
            //print each letter and its value for the column

            for(char myKey : characterSet.keySet()){
                System.out.print(myKey + " ");
                System.out.print(nbOfFirstLetter[characterSet.get(myKey)] + " ");
                System.out.println(nbOfLastLetter[characterSet.get(myKey)] + " ");
            }

        }

        System.out.println(nbWords + " words");
        System.out.println(totalWordsLength + " total characters");
    }
}
