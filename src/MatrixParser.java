import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Nikolaj on 04-09-2015.
 */
public class MatrixParser {

    public List<Integer> getCostMatrix(String fileName)
    {
        try {
            Path filePath = Paths.get(fileName);
            Scanner scanner = new Scanner(filePath);
            List<Integer> costMatrix = new ArrayList<>();
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    costMatrix.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }

            return costMatrix;
        }
        catch (Exception e) {
            System.out.println("File could not be found! noobz");
            return null;
        }
    }

    public static void main(String[] args)
    {
        MatrixParser matrixParser = new MatrixParser();
        List<Integer> matrix = matrixParser.getCostMatrix("costMatrix.txt");
        System.out.println(matrix.toString());
    }

}
