import java.util.*;

public class QuestionConstructor {
    private QuestionType Type = QuestionType.None;
    private Map<QuestionType, HashMap<RequirementType, Integer>> RequiredFields = new HashMap<>() {};

    private String TextField = "";
    private double ValueField = 0.0;
    private double BaseValueField = 0.0;
    private ArrayList<Answer> AnswerField = new ArrayList<Answer>();
    private Answer RightAnswerField = null;

    public enum QuestionType
    {
        None,
        MCMAQuestion,
        MCSAQuestion,
        NumQuestion,
        SAQuestion;

        public String AnswerType()
        {
            switch(this)
            {
                case MCMAQuestion:{return "MCMAAnswer";}
                case MCSAQuestion:{return "MCSAAnswer";}
                case NumQuestion:{return "NumAnswer";}
                case SAQuestion:{return "SAAnswer";}
                case None:
                default:{return "Answer";}
            }
        }

        public Answer GetAnswer(Answer InAnswer)
        {
            try
            {
                return (Answer)Class.forName(AnswerType()).cast(InAnswer);
            }
            catch(Exception E)
            {
                System.out.println("Error: Bad Type");
            }
            return null;
        }

        public Question CastToCorrespondingType(Question InQuestion)
        {
            try
            {
                return (Question)Class.forName(toString()).cast(InQuestion);
            }
            catch(Exception E)
            {
                System.out.println("Error: Bad Type");
            }
            return null;
        }
    }

    public enum RequirementType
    {
        Question_Type,
        Text,
        Max_Value,
        Base_Value,
        Answers,
        Right_Answer;

        @Override
        public String toString() {
            String s = super.toString();
            return s.replace("_", " ");
        }
    }

    public static void PrintQTypes()
    {
        for(QuestionType QT : QuestionType.values())
        {
            if(QT != QuestionType.None)
            {
                System.out.println(QT.toString());
            }
        }
    }

    public static QuestionType ParseQType(String S)
    {
        QuestionType Result = QuestionType.None;
        for(QuestionType QT: QuestionType.values())
        {
            if(QT != QuestionType.None)
            {
                String ChoiceString = QT.toString();
                if(ChoiceString.equalsIgnoreCase(S))
                {
                    return QT;
                }
            }
        }
        return Result;
    }

    public static RequirementType ParseRType(String S)
    {
        RequirementType Result = null;
        for(RequirementType RT: RequirementType.values())
        {
            if(RT.toString().equalsIgnoreCase(S))
            {
                return RT;
            }
        }
        return Result;
    }

    public QuestionConstructor()
    {
        HashMap<RequirementType, Integer> NoneReq = new HashMap<>(){};
        NoneReq.put(RequirementType.Question_Type, 1);

        HashMap<RequirementType, Integer> MCMAReq = new HashMap<>(){};
        MCMAReq.put(RequirementType.Text, 1);
        MCMAReq.put(RequirementType.Base_Value, 1);
        MCMAReq.put(RequirementType.Max_Value, 1);
        MCMAReq.put(RequirementType.Answers, 5);

        HashMap<RequirementType, Integer> MCSAReq = new HashMap<>(){};
        MCSAReq.put(RequirementType.Text, 1);
        MCSAReq.put(RequirementType.Max_Value, 1);
        MCSAReq.put(RequirementType.Answers, 5);

        HashMap<RequirementType, Integer> NumReq  = new HashMap<>(){};
        NumReq.put(RequirementType.Text, 1);
        NumReq.put(RequirementType.Max_Value, 1);
        NumReq.put(RequirementType.Right_Answer, 1);

        HashMap<RequirementType, Integer> SAReq   = new HashMap<>(){};
        SAReq.put(RequirementType.Text, 1);
        SAReq.put(RequirementType.Max_Value, 1);
        SAReq.put(RequirementType.Right_Answer, 1);

        RequiredFields.put(QuestionType.None, NoneReq);
        RequiredFields.put(QuestionType.MCMAQuestion, MCMAReq);
        RequiredFields.put(QuestionType.MCSAQuestion, MCSAReq);
        RequiredFields.put(QuestionType.NumQuestion, NumReq);
        RequiredFields.put(QuestionType.SAQuestion, SAReq);
    }

    public boolean Finished()
    {
        int RequirementSum = 0;
        for(int needed : RequiredFields.get(Type).values())
        {
            RequirementSum += needed;
        }
        return RequirementSum == 0;
    }

    public Answer GetAnswer()
    {
        Answer A = null;
        switch(Type.AnswerType())
        {
            case "MCSAAnswer":
            case "MCMAAnswer":
            {

                while(A == null)
                {
                    System.out.println("Please input the answer value and text separated by a space");
                    try
                    {
                        A = new MCAnswer(ScannerFactory.getKeyboardScanner());
                    }
                    catch(Exception E)
                    {
                        System.out.println("Error parsing, please try again and make sure to have the" +
                                "value come first");
                    }
                }
            }break;
            case "SAAnswer":
            {
                System.out.println("Please input the SAAnswer text on a single line");
                while(A == null)
                {
                    try
                    {
                        A = new SAAnswer(ScannerFactory.getKeyboardScanner());
                    }
                    catch(Exception E)
                    {
                        System.out.println("Error parsing, please try again and make sure to have the" +
                                "value come first");
                    }
                }
            }break;
            case "NumAnswer":
            {
                while(A == null)
                {
                    System.out.println("Please input the Correct answer for the NumQuestion on one line " +
                            "followed by the tolerance on the next line");
                    System.out.println("Example: ");
                    System.out.println("123.45");
                    System.out.println("10.0");
                    System.out.println("In this example, an answer between 113.45 and 133.45 will be deemed correct");
                    try
                    {
                        A = new NumAnswer(ScannerFactory.getKeyboardScanner());
                    }
                    catch(Exception E)
                    {
                        System.out.println("Error parsing, please try again and make sure to have the" +
                                "value come first");
                    }
                }
            }break;
            default:{
                System.out.println("Error parsing answer, please press enter to continue");
                ScannerFactory.getKeyboardScanner().nextLine();
            }break;
        }
        return A;
    }

    public void BuildQuestion()
    {
        System.out.println("Current Requirements until question is valid:");

        while(!Finished())
        {
            RequirementType[] QuestionArr = RequiredFields.get(Type).keySet().toArray(new RequirementType[]{});
            int Index = 1;
            for(RequirementType Requirement : QuestionArr)
            {
                System.out.println("\t" + Index + ".)" + Requirement.toString()+ " -> " + RequiredFields.get(Type).get(Requirement) + " more needed");
                Index++;
            }

            try
            {
                System.out.println("Please select a requirement to satisfy");
                String InLine = ScannerFactory.getKeyboardScanner().nextLine();
                RequirementType Req = ParseRType(InLine);

                System.out.println("Selected: " + Req.toString());
                switch(Req)
                {
                    case Question_Type:
                    {
                        QuestionType Selected = QuestionType.None;
                        while(Selected == QuestionType.None)
                        {
                            System.out.println("Available question types are: ");
                            PrintQTypes();
                            Selected = ParseQType(ScannerFactory.getKeyboardScanner().nextLine());
                        }
                        Type = Selected;
                    }break;
                    case Text:
                    {
                        System.out.println("As a reminder, this question type is: " + Type.toString());
                        TextField = ScannerFactory.getKeyboardScanner().nextLine();
                    }break;
                    case Max_Value:
                    {
                        InLine = ScannerFactory.getKeyboardScanner().nextLine();
                        ValueField = Double.parseDouble(InLine);
                    }break;
                    case Base_Value:
                    {
                        InLine = ScannerFactory.getKeyboardScanner().nextLine();
                        BaseValueField = Double.parseDouble(InLine);
                    }break;
                    case Answers:
                    {
                        System.out.println("As a reminder, this question type is: " + Type.toString());
                        int numToAdd = RequiredFields.get(Type).get(RequirementType.Answers);
                        while(numToAdd > 0)
                        {
                            System.out.println("You need to provide " + numToAdd + " more answers");
                            System.out.println("You must provide answers of type " + Type.AnswerType());

                            AnswerField.add(GetAnswer());
                            numToAdd--;
                        }
                    }break;
                    case Right_Answer:
                    {
                        System.out.println("As a reminder, this question type is: " + Type.toString());
                        System.out.print("You must provide a single answer of type " + Type.AnswerType());

                        RightAnswerField = GetAnswer();
                    }break;
                    default:
                    {
                        System.out.println("Error, could not parse option");
                    }
                }
                RequiredFields.get(Type).replace(Req, 0);
            }
            catch(Exception E)
            {
                System.out.println("Error parsing input, please try again");
            }
        }
    }

    public Question Deliver()
    {
        if(Finished())
        {
            switch(Type)
            {
                case MCMAQuestion:
                {
                    MCMAQuestion Q = new MCMAQuestion(TextField, ValueField, BaseValueField);
                    for(Answer A: AnswerField)
                    {
                        Q.addAnswer((MCAnswer)A);
                    }
                    return Q;
                }
                case MCSAQuestion:
                {
                    MCSAQuestion Q = new MCSAQuestion(TextField, ValueField);
                    for(Answer A: AnswerField)
                    {
                        Q.addAnswer((MCAnswer)A);
                    }
                    return Q;
                }
                case SAQuestion:
                {
                    SAQuestion Q = new SAQuestion(TextField, ValueField);
                    Q.setRightAnswer(RightAnswerField);
                    return Q;
                }
                case NumQuestion:
                {
                    NumQuestion Q = new NumQuestion(TextField,ValueField,0.0);
                    Q.setRightAnswer(RightAnswerField);
                    return Q;

                }
                default:
                {
                    System.out.println("Error: Invalid question type found");
                }break;
            }
        }
        return null;
    }
}
