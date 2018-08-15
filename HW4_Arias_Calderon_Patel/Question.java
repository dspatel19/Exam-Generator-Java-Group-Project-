import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Question{
    protected String text;
    protected double maxValue;

    protected Answer studentAnswer = null;
    protected Answer rightAnswer = null;

    public Question(String Text, double MaxValue)
    {
        text = Text;
        maxValue = MaxValue;
    }
    public Question(Scanner Scan)
    {
        maxValue = Double.parseDouble(Scan.nextLine());
        text = Scan.nextLine();
    }

    public String getHashString()
    {
        return text.split(" ")[0];
    }

    public void print()
    {
        System.out.println(text);
    }

    public abstract double getValue();

    public abstract void getAnswerFromStudent();

    public abstract Answer getNewAnswer();

    public abstract void save(PrintWriter PW);

    public void saveStudentAnswer(PrintWriter PW)
    {
        studentAnswer.save(PW);
    }

    public void restoreStudentAnswer(Scanner Sc){}

    public void setRightAnswer( Answer right )
    {
        rightAnswer = right;
        return;
    }


}