import java.util.HashMap;
import java.util.Map;
/**
 * Created by Thomas on 27-08-2015.
 */
public class Runner {
    public static void main(String[] args) throws Exception{Map<CharPair, Integer> seqMatrix = new HashMap<CharPair, Integer>();
        seqMatrix.put(new CharPair('A', 'A'), 0);
        seqMatrix.put(new CharPair('A', 'C'), 5);
        seqMatrix.put(new CharPair('A', 'G'), 2);
        seqMatrix.put(new CharPair('A', 'T'), 5);
        seqMatrix.put(new CharPair('C', 'A'), 5);
        seqMatrix.put(new CharPair('C', 'C'), 0);
        seqMatrix.put(new CharPair('C', 'G'), 5);
        seqMatrix.put(new CharPair('C', 'T'), 2);
        seqMatrix.put(new CharPair('G', 'A'), 2);
        seqMatrix.put(new CharPair('G', 'C'), 5);
        seqMatrix.put(new CharPair('G', 'G'), 0);
        seqMatrix.put(new CharPair('G', 'T'), 5);
        seqMatrix.put(new CharPair('T', 'A'), 5);
        seqMatrix.put(new CharPair('T', 'C'), 2);
        seqMatrix.put(new CharPair('T', 'G'), 5);
        seqMatrix.put(new CharPair('T', 'T'), 0);
        int gapcost = 5;

        AffineSequenceAligner seqAligner = new AffineSequenceAligner(seqMatrix, 5, 5);
        FastaParser fastaParser = new FastaParser("seq1.fasta");
        char[] seq1 = fastaParser.parse("Seq1").toCharArray();
        fastaParser = new FastaParser("seq2.fasta");
        char[] seq2 = fastaParser.parse("Seq2").toCharArray();
        int res = seqAligner.calculateMinCost(seq1, seq2);

        System.out.println(res);
        //System.out.println(seqAligner.backtrack(seq1, seq2));
        Prompter p = new Prompter();
        p.prompt();



    }
}
