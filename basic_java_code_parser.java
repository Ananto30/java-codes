import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ananto
 */
public class BasicJavaCodeParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        File f = new File("input.txt"); //your input file containing java code
        Scanner sc = new Scanner(f);

        ArrayList<String> keywords = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        ArrayList<String> logicalOperators = new ArrayList<>();
        ArrayList<String> mathOperators = new ArrayList<>();
        ArrayList<String> others = new ArrayList<>();
        ArrayList<String> id = new ArrayList<>();

        while (sc.hasNext()) {
            String s = sc.next();
            if (add_keywords(s, keywords)) {

            } else if (add_numbers(s, numbers)) {

            } else if (add_mathematical_operator(s, mathOperators)) {

            } else if (add_logical_operator(s, logicalOperators)) {

            } else if (add_identifiers(s, id, keywords, numbers, mathOperators, logicalOperators)) {

            } else {
                if (s.contains("(") && !others.contains("( )")) {
                    others.add("( )");
                }
                if (s.contains("{") && !others.contains("{ }")) {
                    others.add("{ }");
                }
                if (s.contains("[") && !others.contains("[ ]")) {
                    others.add("[ ]");
                }
                if (s.contains(";") && !others.contains(";")) {
                    others.add(";");
                }
            }

        }

        System.out.print("Keywords: ");
        for (String c : keywords) {
            System.out.print(c + ", ");
        }
        System.out.print("\nNumbers: ");
        for (String c : numbers) {
            System.out.print(c + ", ");
        }
        System.out.print("\nMathematical Operators: ");
        for (String c : mathOperators) {
            System.out.print(c + ", ");
        }
        System.out.print("\nLogical Operators: ");
        for (String c : logicalOperators) {
            System.out.print(c + ", ");
        }
        System.out.print("\nOthers: ");
        for (String c : others) {
            System.out.print(c + ", ");
        }
        System.out.print("\nIdentifiers: ");
        for (String c : id) {
            System.out.print(c + ", ");
        }
        System.out.println();
    }

    public static boolean add_keywords(String k, ArrayList kw) {
        boolean found = false;
        String[] keywords = {"abstract", "boolean", "byte", "case",
            "catch", "char", "class", "continue", "default", "do", "double",
            "else", "extends", "final", "finally", "float", "for", "if",
            "implements", "import", "instanceof", "int", "interface", "long",
            "native", "new", "package", "private", "protected", "public",
            "return", "short", "static", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "try", "void", "volatile",
            "while", "false", "true", "null"};

        for (String s : keywords) {
            if (s.equals(k) && !kw.contains(k)) {
                kw.add(k);
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean add_numbers(String n, ArrayList numbers) {
        n = n.replaceAll("[-+^:,(){};]", "");
        boolean found = false;
        char[] c = n.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (Character.isDigit(c[i]) && !numbers.contains(n)) {
                numbers.add(n);
                found = true;
            }
        }
        return found;
    }

    public static boolean add_mathematical_operator(String m, ArrayList mathOP) {
        String[] mathop = {"+", "-", "/", "*", "%", "="};
        boolean found = false;
        for (String s : mathop) {
            if (s.equals(m) && !mathOP.contains(m)) {
                mathOP.add(m);
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean add_logical_operator(String m, ArrayList logOP) {
        String[] logicalop = {"<", ">", "!=", "==", ">=", "<=", "&&", "||"};
        boolean found = false;
        for (String s : logicalop) {
            if (s.equals(m) && !logOP.contains(m)) {
                logOP.add(m);
                found = true;
                break;
            }
        }
        return found;
    }

    public static boolean add_identifiers(String id, ArrayList identifiers, ArrayList kw, ArrayList n, ArrayList mathOP, ArrayList logOP) {
        id = id.replaceAll("[;:,(){}]", "");
        boolean found = false;
        if (!kw.contains(id) && !n.contains(id) && !mathOP.contains(id) && !logOP.contains(id) && !identifiers.contains(id) && id != "") {
            identifiers.add(id);
            found = true;
        }
        return found;
    }
}