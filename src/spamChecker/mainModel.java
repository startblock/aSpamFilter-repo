package spamChecker;

import spamChecker.Model;

import java.util.Scanner;

public class mainModel {
    public static void main(String[] args) {
        Model tester = new Model();

        System.out.println("please input the file name and directory");
        Scanner userInputDir=new Scanner(System.in);
        String dir=userInputDir.nextLine();
        tester.spamDetector(fileParser.fileData(dir+"/"));
        System.out.println("is this normal email? "+ tester.isHam());

        // 97.6% accurate!  especially good at recognising ham!

    }
}