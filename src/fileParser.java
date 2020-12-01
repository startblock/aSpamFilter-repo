import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileParser {
    public static void directoryParser(String dir, Model upper){

        File path = new File(dir);
        File [] theFiles=path.listFiles();
        String fileName="";
        try{
            for (File x:theFiles){
                if (x.isFile()){ //this line weeds out other directories/folders


                    upper.spamDetector(fileData(dir+x.getName()));
                    fileName=dir+x.getName();
                    //System.out.println(fileName);

                    upper.isHam();

                }


            }} catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("----------------------------------- "+fileName);

        }


    }



    public static void directoryParser(String dir, Data training){         // parses files within a specified directory
        //      takes com.nigel.spamTrainer.Data object in order to harvest data from files

        File path = new File(dir);
        File [] theFiles=path.listFiles();
        assert theFiles != null;
        for (File s:theFiles){
            if (s.isFile()){

                training.wordUpdate(fileData(dir+s.getName()));            //acceses the com.nigel.spamTrainer.Data object's wordupdate function to add words to library for model training

            }


        }
    }



        public static String[] fileData(String dir){           //opens a file and returns data in String[] where each index is a word
        String data="";

        try {

            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data+myReader.nextLine();

            }
            myReader.close();
            return stringCleaner(data);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return stringCleaner(data);


    }




    public static String[] stringCleaner(String string){          //takes a string and splits it on a word by word basis into a String[]
        String regex=" ";
        //string=string.toLowerCase();
        return string.split(regex);

    }}
