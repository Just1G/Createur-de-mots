public class Statistics {
    protected int nbWords;
    protected int totalWordsLength;
    public int[][] arrayStats = new int[28][28];
    public float[] nbOfCColumns = new float[26];
    public float[] nbOfRows = new float[26];
    protected boolean hasRowsColumnsStats;


    /**
     * Initialise every foo that needs to
     */
    Statistics() {
        nbWords = 0;
        totalWordsLength = 0;
        hasRowsColumnsStats = false;
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
        Character lastChar = aWord.charAt(aWord.length()-1);
        if((firstChar - 'a' < 27 && firstChar - 'a' >= 0) &&
                (lastChar - 'a' < 27 && lastChar - 'a' >= 0) )
        {
            Integer indexFirst = firstChar.compareTo('a');
            arrayStats[indexFirst][26]++;


            Integer indexLast = lastChar.compareTo('a');
            arrayStats[indexLast][27]++;
        }




        for(int j=0;j<aWord.length()-1;j++){
            Character currentLetter =aWord.charAt(j);
            Character nextLetter =aWord.charAt(j+1);

            if((currentLetter - 'a' < 27 && currentLetter - 'a' >= 0) &&
                    (nextLetter - 'a' < 27 && nextLetter - 'a' >= 0) )
            {
                int current = currentLetter.compareTo('a');
                int next = nextLetter.compareTo('a');

                //increments the cell corresponding to the succession of letter
                arrayStats[current][next]++;
            }
        }
    }

    /**
     * Create data for the file rwsColumnsData.csv
     */
    public void createRowsColumnsStatistics(){
        hasRowsColumnsStats = true;
        for(int i=0; i<26; i++){
            for(int j=0; j<26; j++){
                nbOfRows[i]+=arrayStats[i][j]; //make the sum of the current row
                nbOfCColumns[j]+=arrayStats[i][j]; //make the sum of the current column
            }
        }
    }


    /**
     * Displays the statistics in the terminal
     */
    public void displayStatistics(){
        for(int i=0; i<26; i++){
            for(int j=0; j<26; j++){
                System.out.print(arrayStats[i][j] + " "); //print a cell
            }
            System.out.println(); //at the end of a row, next line
        }

        if(hasRowsColumnsStats) {
            //if the statistics were made for the Rows and Columns

            System.out.println("Rows :");
            for (int i = 0; i < 26; i++) {
                System.out.println(('a' + i) + " " + nbOfRows[i]);
            }
            //print each letter and its value for the row

            System.out.println("Columns :");
            for (int j = 0; j < 26; j++) {
                System.out.println(('a' + j) + " " + nbOfCColumns[j]);
            }
            //print each letter and its value for the column

        }

        System.out.println(nbWords + " words");
        System.out.println(totalWordsLength + " total characters");
    }
}
