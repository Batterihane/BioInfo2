package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import solution.*;

public class TestRunner {

    public static void main(String[] args) throws Exception
    {
        TestRunner testRunner = new TestRunner();
        System.out.println("Case 1_1 + " + (testRunner.test("1_1", 22, "acgt-gtcaacgt", "acgtcgt-agcta") ? "succeeded" : "failed"));
        System.out.println("Case 1_2 + " + (testRunner.test("1_2", 24, "acgtgtcaacgt", "acgtcgtagcta") ? "succeeded" : "failed"));
        System.out.println("Case 2_1 + " + (testRunner.test("2_1", 14, "aataat", "aa-gg-") ? "succeeded" : "failed"));
        System.out.println("Case 2_2 + " + (testRunner.test("2_2", 22, "aataat", "a--agg") ? "succeeded" : "failed"));
        System.out.println("Case 3_1 + " + (testRunner.test("3_1", 20, "tccagaga", "t-c-ga-t") ? "succeeded" : "failed"));
        System.out.println("Case 3_2 + " + (testRunner.test("3_2", 29, "tccagaga", "tc---gat") ? "succeeded" : "failed"));
        String expectedSeq1Result = "ggcctaaaggcgccggtctttcgtaccccaaaatctcg-gcattttaagataa-gtgagtgttgcgttacactagcgatctaccgcgtcttatact-taagcg-tatgccc-agatctga-ctaatcgtgcccccggattagacgggcttgatgggaaagaaca--g-ctc-g--tctgtttacgtataaacagaatcgcctgggttcgc";
        String expectedSeq2Result = "gggctaaaggttagggtctttcacactaaagagtggtgcgtatcgt-ggctaatgt-accgcttc-tggtatc-gtggctta-cg-gccagac-ctacaagtactagacctgagaactaatcttgtcgagccttc-catt-ga-ggg--taatgggagagaacatcgagtcagaagttattcttgtttacgtagaatcgcctgggtccgc";
        System.out.println("Case 4_1 + " + (testRunner.test("4_1", 325, expectedSeq1Result, expectedSeq2Result) ? "succeeded" : "failed"));
        expectedSeq1Result = "ggcctaaaggcgccggtctttcgtaccccaaaatctcggcattttaagataagtgagtgttgcgttacactagcgatctaccgcgtcttatacttaagcgtatgcccagatctgactaatcgtgcccccggattagacgggcttgatgggaaagaacagctcgtc------tgtttacgtataaacagaatcgcctgggttcgc";
        expectedSeq2Result = "gggctaaaggttagggtctttcacactaaagagtggt-gcgtatcgtggctaatgtaccgcttctggtatc-gtggcttacggc--cagacctacaagtactagacctga--gaactaatcttgtcgagccttccattgagggtaatgggagagaacatcgagtcagaagttattcttgtttacgtagaatcgcctgggtccgc";
        System.out.println("Case 4_2 + " + (testRunner.test("4_2", 395, expectedSeq1Result, expectedSeq2Result) ? "succeeded" : "failed"));
    }

    public boolean test(String caseNumber, int expectedScore, String expectedSeq1Result, String expectedSeq2Result) throws IOException {
        boolean res = true;

        String path = "testData/case" + caseNumber;

        MatrixParser matrixParser = new MatrixParser();
        matrixParser.parseFile(path + "/costMatrix.txt");

        Map<CharPair, Integer> seqMatrix = matrixParser.getCostMatrix();
        int gapCostAlpha = matrixParser.getGapCostAlpha();
        int gapCostBeta = matrixParser.getGapCostBeta();

        AffineSequenceAligner seqAligner = new AffineSequenceAligner(seqMatrix, gapCostAlpha, gapCostBeta);

        FastaParser fastaParser = new FastaParser(path + "/seq1.fasta");
        char[] seq1 = fastaParser.parse("Seq1").toCharArray();
        fastaParser = new FastaParser(path + "/seq2.fasta");
        char[] seq2 = fastaParser.parse("Seq2").toCharArray();

        expectedSeq1Result = expectedSeq1Result.toUpperCase();
        expectedSeq2Result = expectedSeq2Result.toUpperCase();

        int score = seqAligner.calculateMinCost(seq1, seq2);
        //System.out.println(score);
        String allignment = seqAligner.backtrack(seq1, seq2);
        //System.out.println(allignment);
        if(score != expectedScore) res = false;
        if(!allignment.equals(expectedSeq1Result + "\n\n" + expectedSeq2Result)) res = false;

        return res;
    }
}
