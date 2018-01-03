
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ananto
 */
public class RegularExpressionParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> re = new ArrayList<>();
        ArrayList<String> exp = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int no_re = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < no_re; i++) {
            re.add(sc.nextLine());
        }
        int no_exp = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < no_exp; i++) {
            exp.add(sc.nextLine());
        }

        ExpMatch em = new ExpMatch();

        for (int i = 1; i <= exp.size(); i++) {
            int match_no = 0;
            for (int j = 1; j <= re.size(); j++) {
                if (em.match(re.get(j - 1), exp.get(i - 1))) {
                    match_no = j;
                }
            }
            if (match_no != 0) {
                System.out.println("YES, " + match_no);
            } else {
                System.out.println("NO, 0");
            }

        }
        
        
        //some test
//        if (em.match("def[k-p]*p+", "defkmnpmpp")) {
//            System.out.println("YES, ");
//        } else {
//            System.out.println("NO, 0");
//        }
    }
}
