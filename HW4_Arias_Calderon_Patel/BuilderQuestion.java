import javafx.util.Builder;

import java.io.PrintWriter;
import java.util.ArrayList;

public class BuilderQuestion extends Question {
    protected String Prefix = "";
    protected String ExtraMessage = "";
    protected ArrayList<MCAnswer> PrintableAnswers = new ArrayList<>();


    public BuilderQuestion(Question Q, String enumeration)
    {
        super(Q.text, 0.0);
        Prefix = enumeration;

        if(Q instanceof MCMAQuestion)
        {
            ExtraMessage = "\n\tPlease select one or more answers";
            PrintableAnswers = ((MCQuestion)Q).answers;
        }
        else if(Q instanceof MCSAQuestion)
        {
            ExtraMessage = "\n\tPlease select a single answer";
            PrintableAnswers = ((MCQuestion)Q).answers;
        }
        else if(Q instanceof SAQuestion)
        {
            ExtraMessage = "\n\tPlease write in the space provided below\n\n\n";
        }
        else if(Q instanceof NumQuestion)
        {
            ExtraMessage = "\n\tPlease show your work\n\n\n";
        }
    }

    public Answer getNewAnswer(){ return null; }
    public void getAnswerFromStudent(){ return; }
    public double getValue(){return 0.0;}
    public void save(PrintWriter PW)
    {
        PW.println(Prefix + ". " + text + ExtraMessage);
        if(PrintableAnswers.size() > 0)
        {
            for(MCAnswer A : PrintableAnswers)
            {
                PW.print("\t");
                PW.print(((char)((int)'A' + (PrintableAnswers.indexOf(A))) + ". "));
                BuilderAnswer BA = new BuilderAnswer(A);
                BA.save(PW);
            }
        }
    }
}
