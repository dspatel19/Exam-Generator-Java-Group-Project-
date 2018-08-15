import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SAAnswer extends Answer {
    protected String Text = "";

    public SAAnswer(String S)
    {
        Text = S;
    }

    public SAAnswer(Scanner Sc)
    {
        Text = Sc.nextLine();
    }

    public void print()
    {
        System.out.println(Text);
    }

    //do check on number of words the same in both, doesn't account for missed spellings,
    //but at least it's a little more resistant to variation than the naive method I had
    //before
    public double getCredit(Answer StudentAnswer)
    {
        if(StudentAnswer instanceof SAAnswer)
        {
            if(Text.equalsIgnoreCase(((SAAnswer) StudentAnswer).getText()))
            {
                return 1.0;
            }
        }
        return 0.0;
    }

    public String getText()
    {
        return Text;
    }

    public void save(PrintWriter PW)
    {
        PW.print(Text + "\n");
    }

}
