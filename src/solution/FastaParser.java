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
    File f;
    String filepath;

    public FastaParser(String filepath) throws FileNotFoundException {
        this.filepath = filepath;
    }
    public FastaParser(File file) throws FileNotFoundException {
        f = file;
    }
    public String parse(String seqName) throws Exception{
        if (filepath!=null)
            bufRead = new BufferedReader(new FileReader(filepath));
        if (f!=null)
            bufRead = new BufferedReader(new FileReader(f));
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
                    bufRead.close();
                    return res.toUpperCase();
                }
            }
            curString = bufRead.readLine();
        }
        throw new Exception("Sequence not found");
    }
}
