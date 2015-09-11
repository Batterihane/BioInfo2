import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Balls on 06/09/2015.
 */
public class Prompter {

    String seq1="";
    String seq2="";

    Scanner sc;
    public Prompter(){
        sc = new Scanner(System.in);

    }

    public void prompt(){
        String s = ("type:\n\t" +
                "\"cost\" to see cost matrix and gap cost\n\t" +
                "\"file\" to select a fasta file\n\t"+
                "\"seq\" to select sequences from the current file\n\t"+
                "\"run\" to compute a minimum global alignment on selected sequences with the current score matrix and gap cost\n\t" +
                "\"backtrack\" to compute and print out an optimal alignment after using \"run\""
        );
        System.out.println(s);
        String inp = "";
        while(!(inp.equals("Q")|| inp.equals("QUIT"))){

            inp = sc.next().toUpperCase();

            switch (inp) {
                case "COST":
                    System.out.println("");
                    break;
                case "FILE":
                    System.out.println("Please input name of the fasta file");
                    inp = sc.next();
                   // if
                    try{FastaParser fp = new FastaParser(inp);}
                    catch(FileNotFoundException fnf){}
                    break;
                case "SEQ":
                    System.out.println("Please input name of the first sequence");
                    seq1 = sc.next().toUpperCase();
                    System.out.println("Please input name of the second sequence");
                    seq2 = sc.next().toUpperCase();
                    break;
                case "RUN":
                    System.out.println("");
                    break;
                case "BACKTRACK":
                    System.out.println("");
                    break;

            }
        }





    }
    public void printstuff(){


    }
}
