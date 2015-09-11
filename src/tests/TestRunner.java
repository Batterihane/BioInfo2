package tests;

import java.util.Map;
import solution.*;

public class TestRunner {
    public static void main(String[] args) throws Exception
    {
        MatrixParser matrixParser = new MatrixParser();
        matrixParser.parseFile("costMatrix.txt");

        Map<CharPair, Integer> seqMatrix = matrixParser.getCostMatrix();
        int gapCostAlpha = matrixParser.getGapCostAlpha();
        int gapCostBeta = matrixParser.getGapCostBeta();

        AffineSequenceAligner seqAligner = new AffineSequenceAligner(seqMatrix, gapCostAlpha, gapCostBeta);

        FastaParser fastaParser = new FastaParser("seq1.fasta");
        char[] seq1 = fastaParser.parse("Seq1").toCharArray();
        fastaParser = new FastaParser("seq2.fasta");
        char[] seq2 = fastaParser.parse("Seq2").toCharArray();

        int res = seqAligner.calculateMinCost(seq1, seq2);

        System.out.println(res);
        System.out.println(seqAligner.backtrack(seq1, seq2));
        //solution.Prompter p = new solution.Prompter();
        //p.prompt();



    }
}
