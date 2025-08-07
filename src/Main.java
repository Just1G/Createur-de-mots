public class Main {
    public static void main(String[] args) {

        //Objects are created
        Statistics myStatistics = new Statistics();
        IFile myFileReader = new IFile();

        myFileReader.getNbOfDifferentCharacters(myStatistics, "inputData/mots.txt");
        myStatistics.createTables();

        //Statistics are created using the file specified
        myFileReader.readFileAndMakeStats(myStatistics, "inputData/mots.txt");
        myStatistics.createRowsColumnsStatistics();
        myStatistics.createSuccessorProbabiltyStatistics();
        //myStatistics.displayStatistics(); //this methods could be called to display statistics

        //Two files are created generating statistics
        OFile myFileWriter = new OFile();
        myFileWriter.createLetterTableSumFile(myStatistics);
        myFileWriter.createRowsColumnsSumFile(myStatistics);
        myFileWriter.createNextLetterProbabilityFile(myStatistics);

        WordCreator myWordCreator = new WordCreator();
        for (int i = 0; i < 20; i++) {
            System.out.println(myWordCreator.generateWord(myStatistics));
        }
    }
}