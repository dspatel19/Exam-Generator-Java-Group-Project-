//Alfonso Arias aarias9

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExamTaker
{
    public static void main(String[] args)
    {
        try {
            StudentInformation si = new StudentInformation(ScannerFactory.getKeyboardScanner());
            System.out.println("PLease enter an Exam file to begin");
            Scanner FileScanner = new Scanner(new File(ScannerFactory.getKeyboardScanner().nextLine()));
            TakerExam exam = new TakerExam(FileScanner, si);
            SkipQuestion SQ = new SkipQuestion(exam);

            SQ.ViewQuestionAt(0);

            do {
                exam.printQuestion(SQ.CurIndex());
                System.out.print("Do you want to Skip the Question? ");
                System.out.println("Please enter Y for yes or N for no");

                String choice = ScannerFactory.getKeyboardScanner().nextLine();
                switch (choice.toLowerCase()) {
                    case "y": {

                        SQ.setQuestionSkipped();
                    }
                    break;
                    case "n": {
                        System.out.println("Please Select your Answer");
                        exam.getAnswer(SQ.CurIndex());
                    }
                    break;
                    default: {
                        System.out.println("Invalid Choice, please select Y or N");
                        break;
                    }
                }

            } while (SQ.NextQuestion());
            // call while loop to the end of exam.

            ArrayList<Integer> skipped = SQ.getQuestionSkipped();
            while (skipped.size() > 0)
            {
                exam.printQuestion(skipped.get(0));

                System.out.println("Finale chance, would you like to answer this question?, Y for yes N for no");

                String choice = ScannerFactory.getKeyboardScanner().nextLine();
                switch (choice.toLowerCase())
                {
                    case "y": {
                        System.out.println("Please Select your Answer");
                        exam.getAnswer(skipped.get(0));
                        skipped.remove(0);
                    }
                    case "n": {
                        System.out.println("Question Not Answered");

                        // ExamGrader will handle how to give credit for unanswered questions
                        // exam.NoAnswerToQuestion(skipped.get(0));

                        skipped.remove(0);
                    }
                    default: {
                        System.out.println("Invalid Choice, please select Y or N");
                        break;
                    }
                }
            }

            System.out.println("Please Save your file as 'FristNameLastNameExam#'.txt, without quotes and # Exam number.");
            String fileName = ScannerFactory.getKeyboardScanner().nextLine();
            PrintWriter pw = new PrintWriter(new File(fileName));

            //Print hash code to answer file and Name
            // and saves student answers
            pw.println(exam.GetCheckSum());
            exam.saveStudentFile(pw);

            FileScanner.close();
        }

        catch (Exception Ex)
        {
            Ex.printStackTrace();
        }
    }

}

