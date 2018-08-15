import javax.xml.transform.Source;
import java.io.File;
import java.util.Scanner;

public class ScannerFactory {
    static Scanner keyboardScanner = null;

    static Scanner getKeyboardScanner()
    {
        if(keyboardScanner == null)
        {
            keyboardScanner = new Scanner(System.in);
        }
        return keyboardScanner;
    }
}