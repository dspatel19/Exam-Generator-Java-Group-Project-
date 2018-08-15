import java.io.PrintWriter;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class MCQuestion extends Question {
    public ArrayList<MCAnswer> answers = new ArrayList<>();

    public MCQuestion(String S, double MaxCredit)
    {
        super(S, MaxCredit);
    }
    public MCQuestion(Scanner Scan)
    {
        super(Scan);

    }
    public void reorderAnswers()
    {
        Collections.shuffle(answers);
    }

    public void addAnswer(MCAnswer A)
    {
        answers.add(A);
    }

    public double getValue(MCAnswer SA)
    {
        double tot = 0.0;
        if(SA != null)
        {
            for(MCAnswer A : answers)
            {
                tot += A.getCredit(SA);
            }
        }
        return tot;
    }

    public void print()
    {
        super.print();
        for(Answer A: answers)
        {
            System.out.print("\t");
            int actualPos = Math.max(0, Math.min(26,answers.indexOf(A)));
            String Prefix = ((char)((int)'A' + (actualPos)) + ". ");
            System.out.print(Prefix);
            A.print();
        }
        System.out.println("");
    }

    public void save(PrintWriter PW){ }
}
