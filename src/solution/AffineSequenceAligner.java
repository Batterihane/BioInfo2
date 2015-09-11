package solution;

import java.util.Map;

/**
 * Created by Thomas on 27-08-2015.
 */
public class AffineSequenceAligner {
    Map<CharPair, Integer> seqMatrix;
    int gapCostAlpha, gapCostBeta;
    int[][] resultMap, deletionMap, insertionMap;

    public AffineSequenceAligner(Map<CharPair, Integer> seqMatrix, int gapCostAlpha, int gapCostBeta) {
        this.seqMatrix = seqMatrix;
        this.gapCostAlpha = gapCostAlpha;
        this.gapCostBeta = gapCostBeta;
    }

    public int calculateMinCost(char[] seq1, char[] seq2) {
        resultMap = new int[seq1.length + 1][seq2.length + 1];
        deletionMap = new int[seq1.length + 1][seq2.length + 1];
        insertionMap = new int[seq1.length + 1][seq2.length + 1];
        for (int i = 0; i < seq1.length + 1; i++) {
            for (int j = 0; j < seq2.length + 1; j++) {
                resultMap[i][j] = -99;
                deletionMap[i][j] = -99;
                insertionMap[i][j] = -99;
            }
        }
        return cost(seq1, seq2, seq1.length, seq2.length);
    }

    private int cost(char[] seq1, char[] seq2, int i, int j) {
        if (resultMap[i][j] != -99) {
            return resultMap[i][j];
        }

        int v1, v2, v3, v4;
        v1 = v2 = v3 = v4 = Integer.MAX_VALUE;
        if (i > 0 && j > 0) { //substitution
            v1 = cost(seq1, seq2, i - 1, j - 1) + seqMatrix.get(new CharPair(seq1[i - 1], seq2[j - 1]));
        }
        if (i > 0 && j >= 0) { //deletion
            int singleBlockCost = cost(seq1, seq2, i - 1, j) + (gapCostAlpha + gapCostBeta);
            if (i > 1) {
                v2 = Math.min(singleBlockCost, deletionMap[i - 1][j] + gapCostAlpha);
            } else {
                v2 = singleBlockCost;
            }
            deletionMap[i][j] = v2;
        }
        if (i >= 0 && j > 0) { //insertion
            int singleBlockCost = cost(seq1, seq2, i, j - 1) + (gapCostAlpha + gapCostBeta);
            if (j > 1) {
                v3 = Math.min(singleBlockCost, insertionMap[i][j - 1] + gapCostAlpha);
            } else {
                v3 = singleBlockCost;
            }
            insertionMap[i][j] = v3;
        }
        if (i == 0 && j == 0) {
            v4 = 0;
        }

        resultMap[i][j] = Math.min(v1, Math.min(v2, Math.min(v3, v4)));
        return resultMap[i][j];
    }

    public String backtrack(char[] seq1, char[] seq2) {
        int i = seq1.length;
        int j = seq2.length;
        String res1 = "";
        String res2 = "";
        while (i > 0 || j > 0) {
            if ((i > 0 && j > 0) && (resultMap[i][j] == resultMap[i - 1][j - 1] + seqMatrix.get(new CharPair(seq1[i - 1], seq2[j - 1])))) {
                //end in match is optimal
                res1 = seq1[i - 1] + res1;
                res2 = seq2[j - 1] + res2;
                i--;
                j--;
            } else {
                //end in ins or del is optimal
                int k = 1;
                while (true) {
                    if (i >= k && (resultMap[i][j] == resultMap[i - k][j] + (gapCostAlpha * k + gapCostBeta))) {
                        //end in del gap length k is optimal
                        for (int l = i; l > i - k; l--) {
                            res1 = seq1[l-1] + res1;
                            res2 = "-" + res2;
                        }
                        i = i - k;
                        break;
                    }
                    if (j >= k && (resultMap[i][j] == resultMap[i][j - k] + (gapCostAlpha * k + gapCostBeta))) {
                        //end in ins gap length k is optimal
                        for (int l = j; l > j - k; l--) {
                            res1 = "-" + res1;
                            res2 = seq2[l-1] + res2;
                        }
                        j = j - k;
                        break;
                    }
                    k++;
                }
            }
        }
        return res1+"\n\n"+res2;
    }
}


