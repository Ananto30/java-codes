
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author ananto
 */
public class PostfixEvaluation {

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here

        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        ArrayList<String> expressions = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
//        System.out.println("How many variables : ");
        int number_of_variables = Integer.parseInt(sc.nextLine());
//        System.out.println("Enter them : ");
        for (int j = 0; j < number_of_variables; j++) {
            String s = sc.nextLine();
            s = s.replaceAll(" ", "");
            String[] s_array = s.split("=");
            hm.put(s_array[0], Integer.parseInt(s_array[1]));
        }
//        System.out.println("How many expressions : ");
        int number_of_expressions = Integer.parseInt(sc.nextLine());
//        System.out.println("Enter them : ");
        for (int j = 0; j < number_of_expressions; j++) {
            expressions.add(sc.nextLine());
        }
        System.out.println("------------OUTPUT------------");
        for (String k : expressions) {
            InfixToPostfix itp = new InfixToPostfix(k); // we need the InfixToPostfix class here
            String s = itp.transform();
            System.out.println(s);
            int n = postfixEvaluate(s, hm);
            if (n == 0) {
                System.out.println("Compilation error");
            } else {
                System.out.println(n);
            }
        }
        
//        InfixToPostfix itp = new InfixToPostfix("1 - 2 ^ 3 ^ 3 - ( 4 + 5 * 6 ) * 7");
//        System.out.println(itp.transform());
        
    }

    public static int postfixEvaluate(String exp, HashMap<String, Integer> hm) {
        Stack<Integer> s = new Stack<Integer>();

        exp = exp.replaceAll(" ", "");

        for (int j = 0; j < exp.length(); j++) {
            char ch = exp.charAt(j);
            if (ch >= 'a' && ch <= 'z') {
                s.push(hm.get("" + ch));
            } else {
                try {
                    int num2 = s.pop();
                    int num1 = s.pop();
                    String op = "" + ch;
                    if (op.equals("+")) {
                        s.push(num1 + num2);
                    } else if (op.equals("-")) {
                        s.push(num1 - num2);
                    } else if (op.equals("*")) {
                        s.push(num1 * num2);
                    } else if (op.equals("/")) {
                        s.push(num1 / num2);
                    }
                } catch (Exception e) {
                    return 0;
                }

            }
        }
        return s.pop();
    }

}
