import java.io.PrintWriter;
import java.util.Scanner;

//The MCAnswer class is the Answer equivalent to the
//MCQuestion, and provides the properties and methods
//shared by the MCSA and MCMA Answer classes.
public class MCAnswer extends Answer {
    protected String text;
    protected double creditIfSelected;

    //Constructor sets the  answer to not selected
    //by default
    protected MCAnswer(String S, double creditVal)
    {
        text = S;
        creditIfSelected = creditVal;
    }

    public MCAnswer(Scanner Sc)
    {
        try
        {
            String AnswerText = Sc.nextLine();
            String Parts[] = AnswerText.split(" ", 2);
            creditIfSelected = Double.parseDouble(Parts[0]);
            text = Parts[1];
        }
        catch(Exception E)
        {
            //nothing
        }
    }

    //the print function prints the answer
    public void print()
    {
        System.out.println(text);
    }

    public double getCredit(Answer A)
    {
        if(text.equalsIgnoreCase(((MCAnswer) A).text))
        {
            return creditIfSelected;
        }
        return 0.0;
    }

    public void save(PrintWriter PW)
    {
        PW.print(creditIfSelected + " " + text + "\n");
        System.out.println("");
    }
}
