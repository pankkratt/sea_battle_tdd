import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JavaToPlantUml {
    String fromSource;
    StringBuilder javaClassCode;

    public JavaToPlantUml() {
        javaToPlantUml();
    }

    public void javaToPlantUml() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the path to the *.java: ");
        fromSource = scanner.nextLine();
        readClassCode();
        removeMethodBodies();
        removeEmptyRows();
        removeSemicolons();
        changeModifiers();
        setClipboard(String.valueOf(javaClassCode));
        System.out.println("The PlantUml is in your clipboard right now. Be happy.");

    }

    private void readClassCode() {
        FileInputStream in = null;
        javaClassCode = new StringBuilder();

        try {
            in = new FileInputStream(fromSource);
            int c = in.read();
            while (c != -1) {
                javaClassCode.append((char) c);
                c = in.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeMethodBodies() {
        int cursor = javaClassCode.indexOf("{") + 1;
        while (javaClassCode.indexOf("{", cursor) != -1) {
            int from = javaClassCode.indexOf("{", cursor);
            int to = javaClassCode.indexOf("}", cursor);
            int nextFrom = javaClassCode.indexOf("{", from + 1);
            while (true) {
                if (nextFrom != -1 && nextFrom < to) {
                    nextFrom = javaClassCode.indexOf("{", nextFrom + 1);
                    to = javaClassCode.indexOf("}", to + 1);
                } else {
                    break;
                }
            }
            javaClassCode.delete(from, to + 1);
        }
    }

    private void removeEmptyRows() {
        int start = javaClassCode.indexOf(")");
        while (true) {
            int i = javaClassCode.indexOf("\r\n\r\n", start);
            if (i != -1) {
                javaClassCode.delete(i, i + 2);
            } else {
                break;
            }
        }
    }

    private void removeSemicolons() {
        while (true) {
            int i = javaClassCode.indexOf(";");
            if (i != -1) {
                javaClassCode.deleteCharAt(i);
            } else {
                break;
            }
        }
    }

    private void changeModifiers() {
        Map<String, String> modifiers = new HashMap<>();
        modifiers.put("public ", "+");
        modifiers.put("private ", "-");
        modifiers.put("protected ", "#");
        modifiers.put("static ", "{static}");

        for (String modifier : modifiers.keySet()) {
            while (true) {
                int i = javaClassCode.indexOf(modifier);
                if (i != -1) {
                    javaClassCode.replace(i, i + modifier.length(), modifiers.get(modifier));
                } else {
                    break;
                }
            }
        }
    }

    // This method writes a string to the system clipboard.
    // otherwise it returns null.
    public void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }
}
