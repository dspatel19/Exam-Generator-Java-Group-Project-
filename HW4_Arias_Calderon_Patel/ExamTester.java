import java.util.Random;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;

public class ExamTester {
    public static void main(String[] args)
    {

        System.out.println("Ruy Calderon | rcalde6");
        System.out.println(" ");
        try {
            Scanner FileScanner = new Scanner(new File("InputFile.txt"));
            Exam E = new Exam(FileScanner);
            FileScanner.close();
            E.print();
            int OldCheckSum = E.GetCheckSum();
            System.out.println("Hash: " + OldCheckSum);
            E.reorderQuestions();
            E.reorderMCAnswers(-1);
            System.out.println("New Hash: " + E.GetCheckSum());
            System.out.println("old is same? " + E.CheckSum(OldCheckSum));
            System.out.println("new is same? " + E.CheckSum(E.GetCheckSum()));

            E.save(new PrintWriter(new File("Exam1.txt")));
            E.print();

            System.out.println("Taking Exam");

            for (int i = 1; i <= 4; ++i) {
                E.getAnswerFromStudent(i);
            }


            E.saveStudentAnswers(new PrintWriter(new File("Attempt1.txt")));


            FileScanner = new Scanner(new File("Exam1.txt"));
            E = new Exam(FileScanner);
            FileScanner.close();
            FileScanner = new Scanner(new File("Attempt1.txt"));
            E.restoreStudentAnswers(FileScanner);
            FileScanner.close();
            E.reportQuestionValues();
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace();
        }


        return;
    }
}