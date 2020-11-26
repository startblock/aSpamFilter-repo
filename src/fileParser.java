import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileParser {


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
