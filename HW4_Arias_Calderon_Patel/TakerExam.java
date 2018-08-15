import java.io.PrintWriter;
import java.util.Scanner;

public class TakerExam extends Exam {

    protected StudentInformation myInfo;

    public TakerExam( Scanner sc, StudentInformation si)
    {
        super(sc);
        myInfo = si;
        return;
    }

    public void saveStudentFile(PrintWriter pw)
    {
        myInfo.printInfo(pw);

        for(Question Q : questions)
        {
            Q.saveStudentAnswer(pw);
        }
        pw.close();
    }

    public void printQuestion(int index)
    {
        if(index < questions.size())
        {
            questions.get(index).print();
        }
    }

    public void getAnswer(int index)
    {
        questions.get(index).getAnswerFromStudent();
    }

    public void NoAnswerToQuestion(int index)
    {

    }
}
