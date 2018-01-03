
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ananto
 */
public class JavaThreading {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        int[] n = {5, 2, 7, 4, 1, 0, 8};
        char[] c = {'t', 'o', 'q', 'c', 'b'};

        NumberSort ns = new NumberSort(n);
        AlphabetSort as = new AlphabetSort(c);

        ns.start();
        as.start();
        
        as.join();
        ns.join();
        
        PrintSort ps = new PrintSort(n, c);
        ps.start();
    }

}

class NumberSort extends Thread {

    public int[] array;

    NumberSort(int[] r) {
        array = r;
    }

    public void run() {
        java.util.Arrays.sort(array);
        for (int k : array) {
            System.out.println(k);
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(NumberSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class AlphabetSort extends Thread {

    public char[] array;

    AlphabetSort(char[] r) {
        array = r;
    }

    public void run() {
        java.util.Arrays.sort(array);
        for (char k : array) {
            System.out.println(k);
            try {
                sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(NumberSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class PrintSort extends Thread {

    public int[] nt;
    public char[] ct;

    PrintSort(int[] n, char[] c) {
        nt = n;
        ct = c;
    }

    public void run() {
        for (int i=0; i<nt.length; i++) {
            if (i>=ct.length) {
                System.out.println(nt[i] + " - ");
            }else {
               System.out.println(nt[i] + " - " + ct[i]); 
            }
            
        }
    }

}
