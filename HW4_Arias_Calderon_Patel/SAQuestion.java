import java.io.PrintWriter;
import java.util.Scanner;

public class SAQuestion extends Question {
    public SAQuestion(String S, double MaxCredit)
    {
        super(S, MaxCredit);
    }
    public SAQuestion(Scanner Sc)
    {
        super(Sc);
        rightAnswer = new SAAnswer(Sc.nextLine());
    }

    public double getValue()
    {
        double Sum = 0.0f;
        if(studentAnswer != null)
        {
            if(((SAAnswer)studentAnswer).Text.length() > 0)
            {
                Sum = rightAnswer.getCredit(studentAnswer);
            }
        }

        return Math.sqrt(Sum) * maxValue;
    }
    public void getAnswerFromStudent()
    {
        studentAnswer = getNewAnswer(ScannerFactory.getKeyboardScanner().nextLine());
        studentAnswer.print();
    }

    public void print()
    {
        System.out.println(text);
        System.out.println("\n\n");
    }

    public Answer getNewAnswer()
    {
        return new SAAnswer("");
    }

    public Answer getNewAnswer(String S)
    {
        return new SAAnswer(S);
    }

    public void saveStudentAnswer(PrintWriter PW)
    {
        PW.println("SAAnswer");
        if(studentAnswer != null)
        {
            PW.println(((SAAnswer)studentAnswer).getText());
        }
        PW.println("");
    }

    public void save(PrintWriter PW)
    {
        PW.print("SAQuestion\n");
        PW.print(maxValue + "\n");
        PW.print(text + "\n");
        rightAnswer.save(PW);
        PW.print("\n");
    }

    public void restoreStudentAnswer(Scanner Sc)
    {
        studentAnswer = new SAAnswer(Sc.nextLine());
    }
}
