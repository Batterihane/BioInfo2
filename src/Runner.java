import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 27-08-2015.
 */
public class Runner {
    public static void main(String[] args) throws Exception{
        Map<CharPair, Integer> seqMatrix = new HashMap<CharPair, Integer>();
        seqMatrix.put(new CharPair('A', 'A'), -10);
        seqMatrix.put(new CharPair('A', 'C'), -2);
        seqMatrix.put(new CharPair('A', 'G'), -5);
        seqMatrix.put(new CharPair('A', 'T'), -2);
        seqMatrix.put(new CharPair('C', 'A'), -2);
        seqMatrix.put(new CharPair('C', 'C'), -10);
        seqMatrix.put(new CharPair('C', 'G'), -2);
        seqMatrix.put(new CharPair('C', 'T'), -5);
        seqMatrix.put(new CharPair('G', 'A'), -5);
        seqMatrix.put(new CharPair('G', 'C'), -2);
        seqMatrix.put(new CharPair('G', 'G'), -10);
        seqMatrix.put(new CharPair('G', 'T'), -2);
        seqMatrix.put(new CharPair('T', 'A'), -2);
        seqMatrix.put(new CharPair('T', 'C'), -5);
        seqMatrix.put(new CharPair('T', 'G'), -2);
        seqMatrix.put(new CharPair('T', 'T'), -10);
        int gapcost = 5;

        LinearSequenceAligner seqAligner = new LinearSequenceAligner(seqMatrix, gapcost);
        FastaParser fastaParser = new FastaParser("seq2.fasta");
        fastaParser.parse("Seq2");
        /*char[] seq1 = ("GGCCTAAAGGCGCCGGTCTTTCGTACCCCAAAATCTCGGCATTTTAAGAT" +
                "AAGTGAGTGTTGCGTTACACTAGCGATCTACCGCGTCTTATACTTAAGCG" +
                "TATGCCCAGATCTGACTAATCGTGCCCCCGGATTAGACGGGCTTGATGGG" +
                "AAAGAACAGCTCGTCTGTTTACGTATAAACAGAATCGCCTGGGTTCGC").toCharArray();
        char[] seq2 = ("GGGCTAAAGGTTAGGGTCTTTCACACTAAAGAGTGGTGCGTATCGTGGCT" +
                "AATGTACCGCTTCTGGTATCGTGGCTTACGGCCAGACCTACAAGTACTAG" +
                "ACCTGAGAACTAATCTTGTCGAGCCTTCCATTGAGGGTAATGGGAGAGAA" +
                "CATCGAGTCAGAAGTTATTCTTGTTTACGTAGAATCGCCTGGGTCCGC").toCharArray();
        //char[] seq1 = "AATAAT".toCharArray();
        //char[] seq2 = "AAGG".toCharArray();
        int res = seqAligner.calculateMinCost(seq1, seq2);

        System.out.println(res);
        System.out.println(seqAligner.backtrack(seq1, seq2));*/
    }
}
