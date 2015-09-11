package solution; /**
 * Created with IntelliJ IDEA.
 * User: Balls
 * Date: 04/09/15
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import java.util.Scanner;

public class FastaParser {

    String curString="";
    BufferedReader bufRead;

    public FastaParser(String filepath) throws FileNotFoundException {
        this.bufRead = new BufferedReader(new FileReader(filepath));
    }

    public String parse(String seqName) throws IOException{
        curString = bufRead.readLine();
        while(curString !=null){
            if (curString.startsWith(">")) {
                String[] seqInfo = curString.substring(1).split(" ");
                String seqId = seqInfo[0];
                if (seqId.equals(seqName)){ //it matches with the user's input
                    //read the rest of the lines until we find another '>' or the file finishes
                    String res = "";
                    curString = bufRead.readLine();
                    while ( (curString != null) && (!curString.startsWith(">"))){
                        res+=curString.replaceAll(" ", "");
                        curString = bufRead.readLine();
                    }
                    return res.toUpperCase();
                }
            }
            curString = bufRead.readLine();
        }
        return "String not found";
    }
}
