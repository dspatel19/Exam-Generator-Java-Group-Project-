import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class MCSAQuestion extends MCQuestion{
    public MCSAQuestion(String S, double MaxCredit)
    {
        super(S, MaxCredit);
    }
    public MCSAQuestion(Scanner Sc)
    {
        super(Sc);
        int numAnswers = Integer.parseInt(Sc.nextLine());
        for(int i = 0; i < numAnswers; ++i)
        {
            answers.add(new MCAnswer(Sc));
        }
    }
    public void reorderAnswers()
    {
        super.reorderAnswers();
    }
    public void getAnswerFromStudent()
    {
        String AnswerText = ScannerFactory.getKeyboardScanner().nextLine();
        studentAnswer = new MCAnswer(AnswerText,0.0);
    }

    public Answer getNewAnswer()
    {
        return new MCSAAnswer("",0.0);
    }

    public Answer getNewAnswer(String S, double answerVal)
    {
        return new MCSAAnswer(S, answerVal);
    }

    public double getValue()
    {
        return super.getValue((MCAnswer)studentAnswer);
    }

    public void saveStudentAnswer(PrintWriter PW)
    {
        PW.println("MCSAAnswer");
        if(studentAnswer != null)
        {
            studentAnswer.save(PW);
        }
        PW.println("");
    }

    public void restoreStudentAnswer(Scanner Sc)
    {
        String AnswerText = Sc.nextLine();
        studentAnswer = new MCAnswer(AnswerText,0.0);
    }

    public void save(PrintWriter PW)
    {
        PW.print("MCSAQuestion\n");
        PW.print(maxValue + "\n");
        PW.print(text + "\n");
        PW.print(answers.size() + "\n");
        for(Answer A : answers)
        {
            A.save(PW);
        }
        PW.print("\n");
    }
}
