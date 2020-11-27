import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private Map<String,Integer> trainingWords=new HashMap<>();
    private int totalWords =0;

    public int getTotalWords() {
        return totalWords;
    }


    public void wordUpdate(String[] words){                  //  adds words from files to be added to map, that will be used for training data
        for(String word:words){
            totalWords++;
            if(trainingWords.containsKey(word)){
                trainingWords.put(word,trainingWords.get(word)+1);

            }
            else{trainingWords.put(word,2);}

        }

    }

    Map<String,Double> wordScores= new HashMap<>();


    public Map<String, Integer> getTrainingWords() {
        return trainingWords;
    }


    /*  scores each word picked up by the respective map based on how likely it is to occur(MLE) */

    public Map scoreMap(Map<String,Integer> map, int totalWordCount){

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            this.wordScores.put(entry.getKey(),(double)entry.getValue()/totalWordCount);
        }

        return this.wordScores;

    }

    public void setTrainingWords(Map<String, Integer> trainingWords) {
        this.trainingWords = trainingWords;
    }

    public Map<String, Integer> finalScores(Map<String, Integer> firstCounts, Map<String, Integer> secondCounts){    //adjust scores from both maps to make a final map

        for (Map.Entry<String, Integer> entry : firstCounts.entrySet()){
            if(secondCounts.get(entry.getKey())==null){
                secondCounts.put(entry.getKey(),1);
            }

        }
        return secondCounts;
    }
    /*   finalScores seeks to find words from the corresponding map(either ham or spam) that does not occur in their relative maps  */
    public void finalScores( Map<String, Integer> secondCounts){

        for (Map.Entry<String, Integer> entry : secondCounts.entrySet()){
            if(this.trainingWords.get(entry.getKey())==null){
                this.trainingWords.put(entry.getKey(),1);
            }

        }

    }
    public void createFile(Map<String, Double> scoreMap,String fileName){
        try {

            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(fileName+".ser")
            );
            out.writeObject(scoreMap);
            out.flush();
            out.close();

        }
        catch (FileNotFoundException e){
            e.printStackTrace();}

        catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<String, Double> getWordScores() {
        return wordScores;
    }

}