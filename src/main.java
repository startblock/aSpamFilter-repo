public class main {

    public static void main(String[] args) {
        Data spam=new Data();
        Data ham=new Data();
        fileParser.directoryParser("enron1/spam/",spam);
        fileParser.directoryParser("enron1/ham/",ham);

        spam.fnalScores(ham.getTrainingWords());
        ham.finalScores(spam.getTrainingWords());
        spam.scoreMap(spam.getTrainingWords(),spam.getTotalWords());
        ham.scoreMap(ham.getTrainingWords(),ham.getTotalWords());
        spam.createFile(spam.getWordScores(),"spamScores");
        ham.createFile(ham.getWordScores(),"hamScores");



    }




}