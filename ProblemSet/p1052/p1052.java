
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int[] unsatisfied = customers.clone();
        int satisfied = 0;
        // int maxIndex = 0;
        int maxValue = 0;
        int sum = 0;
        for (int i = 0; i < unsatisfied.length; i++) {
            if (grumpy[i] == 0) {
                satisfied += customers[i];
                unsatisfied[i] = 0;
            }
            // System.out.println("i [" + i + "], cus[" + customers[i] + "], angry [" + grumpy[i] + "], unsat [" + unsatisfied[i] + "]");
            if (i + 1 - X >= 0) {
            // if ((i + 1 - X >= 0) &&
            //     ((i + 1 - X == 0) || (grumpy[i - X] == 1)) &&
            //     ((i + 1 == unsatisfied.length) || (grumpy[i + 1] == 1))) {
                sum = 0;
                int[] fake = Arrays.copyOfRange(unsatisfied, i + 1 - X, i + 1);
                for (int p : fake) {
                    sum += p;
                }
                // System.out.println("sum [" + sum + "], b [" + (i + 1 - X) + "] e [" + i + "]");
                if (sum > maxValue) {
                    maxValue = sum;
                    // maxIndex = i + 1 - X;
                }
                // System.out.println("index[" + maxIndex + "] max [" + maxValue + "], satisfied[" + satisfied + "]");
            }
        }
        return maxValue + satisfied;
    }
}

public class p1052 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = in.readLine()) != null) {
                int[] customers = stringToIntegerArray(line);
                line = in.readLine();
                int[] grumpy = stringToIntegerArray(line);
                line = in.readLine();
                int X = Integer.parseInt(line);
                
                int ret = new Solution().maxSatisfied(customers, grumpy, X);
                
                String out = String.valueOf(ret);
                
                System.out.print(out);
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}