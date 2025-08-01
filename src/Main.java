public class Main {
    public static void main(String[] args) {

        //Objects are created
        Statistics myStatistics = new Statistics();
        IFile myFileReader = new IFile();

        //Statistics are created using the file specified
        myFileReader.readFileAndMakeStats("mots.txt",myStatistics);
        myStatistics.createRowsColumnsStatistics();
        myStatistics.displayStatistics(); //this methods could be called to display statistics

        //Two files are created generating statistics
        OFile myFileWriter = new OFile();
        myFileWriter.createFileLettersData(myStatistics);
        myFileWriter.createFileRowsColumnsData(myStatistics);


    }
}