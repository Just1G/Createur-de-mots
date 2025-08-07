import java.util.Random;

public class WordCreator {
    Random rand = new Random();

    public int generateRandomNumberBetween(int min, int max)
    {
        return rand.nextInt(min,max);
    }

    public float generateRandomValueBetween0and1()
    {
        return rand.nextFloat();
    }

    public String generateWord(Statistics stats)
    {
        float randomFloatNumber;
        float sumProbability;
        String genereatedWord ="";
        int wordLength= generateRandomNumberBetween(3,12);
        int charValue=0;
        int lastCharValue=0;

        randomFloatNumber = generateRandomValueBetween0and1();
        sumProbability=0;

        for(int i=0; sumProbability<randomFloatNumber; i++)
        {
            charValue = i;
            sumProbability+= stats.starterProbability[charValue];
        }

        genereatedWord = genereatedWord + stats.charOrder.get(charValue);

        for(int i=1;i<wordLength;i++)
        {
            lastCharValue = charValue;
            randomFloatNumber = generateRandomValueBetween0and1();
            sumProbability=0;

            //System.out.println("randomFloatNumber: "+randomFloatNumber);

            for(int j=0; sumProbability<randomFloatNumber; j++)
            {
                //System.out.println("sumProbability: "+sumProbability);

                charValue = j;
                sumProbability+= stats.SuccessorProbabiltyTable[lastCharValue][charValue];
            }

            genereatedWord= genereatedWord + stats.charOrder.get(charValue);
        }


        return genereatedWord;
    }

}
