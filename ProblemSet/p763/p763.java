import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
// import com.eclipsesource.json.Json;

class Solution {
    public List<Integer> partitionLabels(String S) {
        int len = S.length();
        //System.out.println(S + ", total lenght : " + len);

        List<Integer> result = new ArrayList<Integer>();

        int i = 0;
        int start = 0;
        int end = 0;
        while (i < len) {
            if (i > end) {
                System.out.println("start : " + start + ", end : " + end + ", size : " + (end - start + 1));
                result.add(end - start + 1);
                start = i;
            }
            char currChar = S.charAt(i);
            int lastPos = S.lastIndexOf(currChar);
            // System.out.println("curr char " + currChar + ", last char " + lastPos);
            if (lastPos > end) {
                end = lastPos;
            }
            i++;
        }
        //System.out.println("start : " + start + ", end : " + end + ", size : " + (end - start + 1));
        result.add(end - start + 1);
        return result;
    }
}

// public class MainClass {
public class p763 {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        // System.out.println("yyy : " + Json.value(input).getClass().getName());
        // console# yyy : com.eclipsesource.json.JsonString
        // return Json.value(input).toString();
        return input;
    }
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String S = stringToString(line);
            
            List<Integer> ret = new Solution().partitionLabels(S);
            
            String out = integerArrayListToString(ret);
            
            System.out.print(out);
        }
    }
}
