import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MCMAQuestion extends MCQuestion{
    protected ArrayList<Answer> studentAnswer = new ArrayList<>();
    public double baseCredit = 0.0;
    public MCMAQuestion(String S, double maxValue, double base)
    {
        super(S, maxValue);
        baseCredit = base;
    }

    public MCMAQuestion(Scanner Sc)
    {
        super(Sc);
        baseCredit = Double.parseDouble(Sc.nextLine());
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

    public double getValue()
    {
        double tot = baseCredit;
        for(Answer SA: studentAnswer)
        {
            double partialCredit = super.getValue((MCAnswer)SA);
            tot += partialCredit;

        }

        return tot;
    }

    public void getAnswerFromStudent()
    {
        System.out.println("Enter the number of answers you would like to select");
        int numStudentAnswers = Integer.parseInt(ScannerFactory.getKeyboardScanner().nextLine());
        for(int i = 0; i < numStudentAnswers; ++i)
        {
            System.out.println("Getting answer #" + (i+1));
            String AnswerText = ScannerFactory.getKeyboardScanner().nextLine();
            Answer A = new MCAnswer(AnswerText,0.0);
            studentAnswer.add(A);
        }
    }

    public MCMAAnswer getNewAnswer()
    {
        return new MCMAAnswer("DEFAULT", 0.0);
    }

    public MCMAAnswer getNewAnswer(String S, double answerVal)
    {
        return new MCMAAnswer(S, answerVal);
    }

    @Override
    public void saveStudentAnswer(PrintWriter PW)
    {
        PW.println("MCMAAnswer");
        if(studentAnswer.size() > 0)
        {
            PW.println(studentAnswer.size());
            for(Answer A: studentAnswer)
            {
                A.save(PW);
            }
        }
        PW.println("");
    }

    public void restoreStudentAnswer(Scanner Sc)
    {
        int numAnswers = Integer.parseInt(Sc.nextLine());
        for(int i = 0; i < numAnswers; ++i)
        {
            String AnswerText = Sc.nextLine();
            studentAnswer.add(new MCAnswer(AnswerText,0.0));
        }
    }

    public void save(PrintWriter PW)
    {
        PW.print("MCMAQuestion\n");
        PW.print(maxValue + "\n");
        PW.print(text + "\n");
        PW.print(baseCredit + "\n");
        PW.print(answers.size() + "\n");
        for(Answer A : answers)
        {
            A.save(PW);
        }
        PW.print("\n");
    }
}
