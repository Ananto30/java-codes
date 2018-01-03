
import java.util.Stack;

/**
 *
 * @author ananto
 */
public class InfixToPostfix {

    public String input;
    public String output = "";

    Stack<Character> s = new Stack<Character>();

    public InfixToPostfix(String input) {
        input = input.replaceAll(" ", "");
        this.input = input;
    }

    String transform() {
        for (int j = 0; j < input.length(); j++) {
            char ch = input.charAt(j);
            if (isAlpha(ch)) {
                output += ch + " ";
            } else if (ch == '(') {
                s.push(ch);
            } else if (operator(ch)) {
                if (s.empty()) {
                    s.push(ch);
                } else if (s.peek() == '(') {
                    s.push(ch);
                } else if (presidence(ch) > presidence(s.peek())) {
                    s.push(ch);
                } else if (presidence(ch) < presidence(s.peek())) {
                    while (!s.isEmpty() && presidence(ch) <= presidence(s.peek())) {
                        output += s.pop() + " ";
                    }
                    s.push(ch);
                } else {
                    output += s.pop() + " ";
                    s.push(ch);
                }
            } else if (ch == ')') {
                while (s.peek() != '(') {
                    output += s.pop() + " ";
                }
                s.pop();
            }
        }
        while (!s.isEmpty()) {
            output += s.pop() + " ";
        }
        return output;
    }

    int presidence(char ch) {
        switch (ch) {
            case '-':
                return 1;
            case '+':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
        }
        return 0;
    }

    boolean operator(char ch) {
        if (ch == '/' || ch == '*' || ch == '+' || ch == '-') {
            return true;
        } else {
            return false;
        }
    }

    boolean isAlpha(char ch) {
        if (ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
            return true;
        } else {
            return false;
        }
    }

}
