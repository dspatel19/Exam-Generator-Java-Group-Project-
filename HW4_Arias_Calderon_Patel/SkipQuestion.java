import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class SkipQuestion {

    Exam TakingExam;

    int currentIndex = -1;

    protected Set<Integer> questionSkipped = new TreeSet<>();

    public SkipQuestion(Exam e)
    {
        TakingExam = e;
        return;
    }

    public void ViewQuestionAt(int index)
    {
        currentIndex = index;
    }

    public boolean NextQuestion()
    {
        currentIndex = (currentIndex >= TakingExam.questions.size()) ? (TakingExam.questions.size() - 1) : (currentIndex + 1);
        if(currentIndex < TakingExam.questions.size())
            return true;
        else
            return false;
    }

    public void setQuestionSkipped()
    {
        if(!questionSkipped.add(currentIndex))
        {
            System.out.println(currentIndex + " Already Answered!");
        }
    }

    public ArrayList<Integer> getQuestionSkipped()
    {
        Integer[] Arr = new Integer[questionSkipped.size()];
        Arr = questionSkipped.toArray(Arr);
        ArrayList<Integer> RetVal = new ArrayList<Integer>();
        for(int num : Arr)
        {
            RetVal.add(num);
        }
        return RetVal;
    }

    public int CurIndex()
    {
        return currentIndex;
    }


}
