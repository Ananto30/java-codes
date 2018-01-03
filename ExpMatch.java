
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author ananto
 */
public class ExpMatch {

    public ExpMatch() {

    }
    String bracket = "";
    String third_bracket = "";
    boolean exclude = false;
    int exact_length = 0;
    String plus_check_third_bracket = "";

    boolean match(String re, String exp) {
        
//        System.out.println(re);
//        System.out.println(exp);

        
        if (re.length() == 0) {
            return exp.length() == 0;
        }
        if (re.length() == 1) {
            if (re.charAt(0) == ']') {
                if (exp.length() == exact_length) {

                    if (exclude) {
                        exp = exclusive(third_bracket, exp, exact_length);
                        exclude = false;
                        third_bracket = "";
                        exact_length = 0;
                        return match(re.substring(1), exp);
                    } else {
                        exp = inclusive(third_bracket, exp, exact_length);
                        third_bracket = "";
                        exact_length = 0;
                        return match(re.substring(1), exp);
                    }

                }

            }
            if (exp.isEmpty()) {
                return false;
            }
            if (exp.charAt(0) == re.charAt(0)) {
                return match(re.substring(1), exp.substring(1));
            } else {
                return false;
            }
        }

        if (re.charAt(1) == '{') {
            String digit = "";
            re = re.substring(0, 1) + re.substring(2, re.length());

            while (Character.isDigit(re.charAt(1))) {
                digit += re.charAt(1);
                re = re.substring(0, 1) + re.substring(2, re.length());

            }
            exact_length = Integer.parseInt(digit) + 1;
            re = re.replaceFirst("}", "");
            return match(re, exp);
        }

        if (re.charAt(1) == '*') {
            if (!third_bracket.isEmpty()) {
                if (exclude) {
                    exp = exclusive(third_bracket, exp, exact_length);
                    exclude = false;
                    third_bracket = "";
                    exact_length = 0;
                    return match(re.substring(2), exp);
                } else {
                    exp = inclusive(third_bracket, exp, exact_length);
                    third_bracket = "";
                    exact_length = 0;
                    return match(re.substring(2), exp);
                }

            }
            if (!bracket.isEmpty()) {
                exp = star(bracket, exp);
                bracket = "";
                return match(re.substring(2), exp);
            } else if (exp.isEmpty() || exp.charAt(0) != re.charAt(0)) {
                return match(re.substring(2), exp);
            } else {
                exp = star(re.charAt(0) + "", exp);
                return match(re.substring(2), exp);
            }
        }
        if (re.charAt(1) == '?') {
            if (!bracket.isEmpty() && !exp.isEmpty()) {
                if (exp.indexOf(bracket) == 0) {
                    exp = exp.substring(bracket.length());
                    bracket = "";
                }
                return match(re.substring(2), exp);
            } else if (exp.isEmpty() || exp.charAt(0) != re.charAt(0)) {
                return match(re.substring(2), exp);
            } else {
                return match(re.substring(2), exp.substring(1));
            }
        }
        if (re.charAt(1) == '+') {
            if (!bracket.isEmpty() && !exp.isEmpty() && exp.charAt(0) == bracket.charAt(0)) {
                exp = star(bracket, exp);
                bracket = "";
                return match(re.substring(2), exp);

            }
            if (exp.isEmpty() || exp.charAt(0) != re.charAt(0)) {
                for (char c : plus_check_third_bracket.toCharArray()) {
                    if (c == re.charAt(0)) {
                        return match(re.substring(2), exp);
                    }
                }
                return false;
            } else {
                exp = star(re.charAt(0) + "", exp);
                return match(re.substring(2), exp);
            }
        }
        if (re.charAt(0) == '(') {
            re = re.substring(1);

            while (re.charAt(0) != ')') {
                bracket += re.charAt(0) + "";
                re = re.substring(1);
            }
            return match(re, exp);
        }
        if (re.charAt(0) == '[') {
            re = re.substring(1);
            if (re.charAt(0) == '^') {
                exclude = true;
                re = re.substring(1);
            }
            if (re.charAt(1) == '-') {
                for (int i = re.charAt(0); i <= re.charAt(2); i++) {
                    third_bracket += (char) i;
                }
                re = re.substring(3);
            }
            while (re.charAt(0) != ']') {
                third_bracket += re.charAt(0) + "";
                re = re.substring(1);
            }
            plus_check_third_bracket = third_bracket;
            return match(re, exp);
        }
        if (re.charAt(0) == ']') {

            if (exclude) {
                exp = exclusive(third_bracket, exp, exact_length);
                exclude = false;
                third_bracket = "";
                exact_length = 0;
                return match(re.substring(1), exp);
            } else {
                exp = inclusive(third_bracket, exp, exact_length);
                third_bracket = "";
                exact_length = 0;
                return match(re.substring(1), exp);
            }

        }
        if (exp.length() == 0 && re.length() != 0) {
            return false;

        }
        if (exp.charAt(0) == re.charAt(0)) {
            return match(re.substring(1), exp.substring(1));
        }
        return false;
    }

    void print_them() {
        System.out.println("========variables=======");
        System.out.println(bracket);
        System.out.println(third_bracket);
        System.out.println(exclude);
        System.out.println(exact_length);
        System.out.println("========================");
    }

    String star(String re, String exp) {
        if (exp.indexOf(re) == 0) {
            return star(re, exp.substring(re.length()));
        } else {
            return exp;
        }
    }

    String inclusive(String re, String exp, int exactNo) {
        if (exactNo == 1 || exp.isEmpty()) {
            return exp;
        }
        for (char c : re.toCharArray()) {
            if (c == exp.charAt(0)) {
                return inclusive(re, exp.substring(1), exactNo - 1);
            }
        }
        return exp;
    }

    boolean inc(String re, String exp) {
        if (exp.length() > 1) {
            return false;
        }
        boolean has = false;
        for (char c : re.toCharArray()) {
            if (c == exp.charAt(0)) {
                has = true;
            }
        }
        return has;
    }

    boolean exc(String re, String exp) {
        if (exp.length() > 1) {
            return false;
        }
        boolean has = true;
        for (char c : re.toCharArray()) {
            if (c == exp.charAt(0)) {
                has = false;
            }
        }
        return has;
    }

    String exclusive(String re, String exp, int exactNo) {
        if (exactNo == 1) {
            return exp;
        }
        for (char c : re.toCharArray()) {
            if (c == exp.charAt(0)) {
                return exp;
            }
        }
        return exclusive(re, exp.substring(1), exactNo - 1);
    }

}
