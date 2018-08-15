import java.io.PrintWriter;
import java.util.Scanner;

public class NumQuestion extends Question {
    public NumQuestion(String S, double MaxCredit, double Tolerance)
    {
        super(S, MaxCredit);
    }
    public NumQuestion(Scanner Sc)
    {
        super(Sc);
        rightAnswer = new NumAnswer(Sc);
    }
    public double getValue()
    {
        try
        {
            if(studentAnswer != null)
            {
                double val = studentAnswer.getCredit(rightAnswer);
                return val;
            }
        }
        catch(Exception Ex)
        {
            Ex.printStackTrace();
        }
        return 0.0;
    }

    public void print()
    {
        System.out.println(text);
        System.out.println("\n\n");
    }
    public void getAnswerFromStudent()
    {
        studentAnswer = new NumAnswer(ScannerFactory.getKeyboardScanner());
    }

    public Answer getNewAnswer()
    {
        return new NumAnswer();
    }

    public void saveStudentAnswer(PrintWriter PW)
    {
        PW.println("NumAnswer");
        if(studentAnswer != null)
        {
            studentAnswer.save(PW);
        }
        else
        {
            PW.println("");
        }
    }

    public void save(PrintWriter PW)
    {
        PW.print("NumQuestion\n");
        PW.print(maxValue + "\n");
        PW.print(text + "\n");
        rightAnswer.save(PW);
        PW.print("\n");
    }

    public void restoreStudentAnswer(Scanner Sc)
    {
        studentAnswer = new NumAnswer(Sc);
    }
}
