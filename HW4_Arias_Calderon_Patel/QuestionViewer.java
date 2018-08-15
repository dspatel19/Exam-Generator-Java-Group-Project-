import java.awt.image.IndexColorModel;
import java.util.*;

public class QuestionViewer {
    BuilderExam viewing;
    int currentIndex = -1;
    protected Set<Integer> MarkedQuestions = new TreeSet<>();

    public QuestionViewer(BuilderExam exam)
    {
        viewing = exam;
    }

    public void ViewQuestionAt(int index)
    {
        currentIndex = index;
    }

    public void PreviousQuestion()
    {
        currentIndex = (currentIndex < 0) ? 0 : currentIndex-1;
    }

    public void NextQuestion()
    {
        currentIndex = (currentIndex >= viewing.getQuestionCount()) ? viewing.getQuestionCount()-1 : currentIndex+1;
    }

    public void Mark()
    {
        if(!MarkedQuestions.add(currentIndex))
        {
            System.out.println(currentIndex + " already marked!");
        }
    }

    public ArrayList<Integer> GetMarked()
    {
        Integer[] Arr = new Integer[MarkedQuestions.size()];
        Arr = MarkedQuestions.toArray(Arr);
        ArrayList<Integer> RetVal = new ArrayList<Integer>();
        for(int num : Arr)
        {
            RetVal.add(num);
        }
        return RetVal;
    }

    public int IndexOfCurrent()
    {
        return currentIndex;
    }

    public void Refresh()
    {
        if(viewing.getQuestionCount() > currentIndex)
        {
            viewing.printQuestionAt(currentIndex);
        }
        else
        {
            System.out.println("No questions loaded into exam");
        }
    }
}
