import java.io.PrintWriter;

public class BuilderAnswer extends MCAnswer {
    public BuilderAnswer(MCAnswer A)
    {
        super(A.text, 0.0);
    }

    @Override
    public void save(PrintWriter PW)
    {
        PW.println(text);
    }
}
