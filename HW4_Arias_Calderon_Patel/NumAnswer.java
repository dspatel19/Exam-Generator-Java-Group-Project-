import java.io.PrintWriter;
import java.util.Scanner;

//The NumAnswer is the extension of the Answer class that
//allows for the storage of a numerical answer along with
//the tolerance
public class NumAnswer extends Answer{
    protected double value = 0.0;
    protected double tolerance = 0.0;
    //The default constructor fills no values
    public NumAnswer()
    {
        super();
    }

    public NumAnswer(Scanner Sc)
    {
        try
        {
            value = Double.parseDouble(Sc.nextLine());
            if(Sc.hasNextLine())
            {
                String Line = Sc.nextLine();
                if(Line.length() > 0)
                {
                    tolerance = Double.parseDouble(Line);
                }
            }
        }
        catch(Exception E)
        {
            //nothing
        }
    }

    //prints the space for the student to input their answer (if paper)
    public void print()
    {
        String AnswerString = Double.toString(value);
        if(tolerance != 0.0)
        {
            AnswerString += "+-" + tolerance;
        }
        System.out.println(AnswerString);
    }

    //Determines if the student answer is within the specified tolerance of the
    //correct answer and returns the a 1.0 to signify 100% credit value awarded
    public double getCredit(Answer RightAnswer)
    {
        if(RightAnswer instanceof NumAnswer)
        {
            NumAnswer NumA = (NumAnswer)RightAnswer;
            double min = NumA.value - NumA.tolerance;
            double max = NumA.value + NumA.tolerance;

            if(value >= min && value <= max)
            {
                return 1.0;
            }
        }
        return 0.0;
    }

    public void save(PrintWriter PW)
    {
        PW.print(value + "\n");
        if(tolerance != 0.0)
        {
            PW.print(tolerance + "\n");
        }
        PW.print("\n");
    }
}