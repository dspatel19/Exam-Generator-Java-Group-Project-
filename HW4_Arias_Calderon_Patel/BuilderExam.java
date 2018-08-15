import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class BuilderExam extends Exam {
    public BuilderExam(String title) { super(title);}

    public BuilderExam(Scanner Scan)
    {
        super(Scan);
    }

    public void addQuestion(Question Q)
    {
        questions.add(Q);
    }

    public int getQuestionCount()
    {
        return questions.size();
    }

    public void saveHardCopy(String fileName)
    {
        try
        {
            File F = new File(fileName);
            if (F.createNewFile())
            {
                System.out.println("File " + fileName + " created");
            }
            PrintWriter PW = new PrintWriter(F);
            PW.println(text);
            for(Question Q: questions)
            {
                BuilderQuestion BQ = new BuilderQuestion(Q, Integer.toString(questions.indexOf(Q)+1));
                BQ.save(PW);
                PW.print("\n");
            }
            PW.close();
        }
        catch (Exception e)
        {
            System.out.println("Error creating or loading file" + fileName);
        }
    }

    public void removeQuestionAt(int index)
    {
        try
        {
            questions.remove(index);
        }
        catch(Exception e)
        {
            System.out.println("Error removing question at index: " + index);
        }
    }

    public void printQuestionAt(int index)
    {
        questions.get(index).print();
    }
}
