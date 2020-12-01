import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class Model {

    private int spamTally=0;   //     recrods the amount of times either the maximum or minimum spam/ham counters were reached
    private int hamTally=0;

    private double spamScore=0.0;     // tallies the respective ham and spam scores, whichever score is largest indicates whether it is ham or spam
    private double hamScore=0.0;
    private Map<String,Double> spamProb;  //maps that hold the score of each word, scored for ham and spam
    private Map<String,Double> hamProb;

    public Model(){

        setHamProb(getMaps("hamScores.ser"));            //constructor to initialise the score maps
        setSpamProb(getMaps("spamScores.ser"));
    }

    public double getSpamScore() {
        return spamScore;
    }

    public double getHamScore() {
        return hamScore;
    }

    public void setSpamProb(Map<String, Double> spamProb) {
        this.spamProb = spamProb;
    }

    public void setHamProb(Map<String, Double> hamProb) {
        this.hamProb = hamProb;
    }

    public Map<String,Double> getMaps(String fileName){        //opens and reads files in order to get the score maps
        Map<String,Double> hamScores=new HashMap<>();

        try{

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            hamScores = (Map<String, Double>) in.readObject();
            in.close();
            return hamScores;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return hamScores;

    }

    /*   parses for each word in a string/docid and assignes it a hamScore(normal mail) or spamscore(spam mail)
         it makes use of naive bayes theorm to calculate each respective score, the largest score indicates
         whether it is spam or not.

         there are 2 scoring systems here.
         the first being naive bayes which multiplies each word score to calculate which one has the highest maximum
         liklehood.
         An issue with this approach is that, the scores can become so large or small that they surpass the maximum and
         min values of Double there, there i decided to reset the counter when this limit is almost reached. when the
         limit is almost reached a the counter's spamScore and hamScore are reset and a tally of which one triggered
         the reset counter is recorded, when that limit is reached the tallies hamTally and spamTally takeover
         spamScore and hamScore as the

         */




    public void spamDetector(String[] words){
        this.spamScore=0.33;
        this.hamScore=0.66;

        for (String str:words){

            try{
                if((spamScore/hamScore)<Double.MIN_VALUE*10000000000000000000000000000000000000000000000000000000000000000000.00){
                    spamScore=0.33;
                    hamScore=0.66;
                    hamTally++;

                }else if((spamScore/hamScore)*10000000000000000000000000000000000000000000000000000000000000000000.00>Double.MAX_VALUE ) {
                    spamScore=0.33;
                    hamScore=0.66;
                    spamTally++;

                }

                this.spamScore=this.spamScore*(spamProb.get(str)*1000.0);


                this.hamScore=this.hamScore*(hamProb.get(str)*1000.0);
            }

            catch (NullPointerException a){
                a.printStackTrace();
                this.hamScore=this.hamScore*1.0;
                this.spamScore=this.spamScore*1.0;


            }

        }}



    /*   this method compares the spamScore and hamScore variables in order to determine whether the input file
     *  is junk or not */

    public boolean isHam(){
        boolean isThisHam=true;

        if(hamTally==0 && spamTally==0){
            if(hamScore>=spamScore){


                isThisHam=true;
            }
            else {


                isThisHam=false;
            }}
        else if (hamTally>spamTally){
            isThisHam=true;

        }
        else {isThisHam=false;
        }

        hamTally=0;
        spamTally=0;


        return isThisHam;


    }


}