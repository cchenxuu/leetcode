import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for singly-linked list.
 */

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


class Solution {
    private static void dumpList(ListNode l) {
        System.out.println("----");
        ListNode n = l;
        int i = 0;
        while (n != null) {
            System.out.println("node index : " + i + ", value : " + n.val);
            n = n.next;
            i++;
        }
        System.out.println("----");
    }

    private static ListNode reverseList(ListNode l) {
        ListNode prev, curr, next;
        prev = null;
        curr = l;
        next = null;
        while (curr.next != null) {
            // System.out.println("before, prev " + prev + ", curr " + curr + ", next " + next);
            // if (prev != null) {
            //     System.out.println("    prev " + prev.val);
            // }
            // if (curr != null) {
            //     System.out.println("    curr " + curr.val);
            // }
            // if (next != null) {
            //     System.out.println("    next " + next.val);
            // }
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            // System.out.println("after, prev " + prev + ", curr " + curr + ", next " + next);
            // if (prev != null) {
            //     System.out.println("    prev " + prev.val);
            // }
            // if (curr != null) {
            //     System.out.println("    curr " + curr.val);
            // }
            // if (next != null) {
            //     System.out.println("    next " + next.val);
            // }
        }
        curr.next = prev;
        return curr;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Solution.dumpList(l1);
        Solution.dumpList(l2);
        ListNode rl1 = Solution.reverseList(l1);
        Solution.dumpList(rl1);
        ListNode rl2 = Solution.reverseList(l2);
        Solution.dumpList(rl2);

        ListNode p1 = rl1;
        ListNode p2 = rl2;
        ListNode result = null, ptr = null;
        int carry = 0;
        
        while (true) {
            int v1, v2;
            if (p1 != null) {
                v1 = p1.val;
                p1 = p1.next;
            } else {
                v1 = 0;
            }
            if (p2 != null) {
                v2 = p2.val;
                p2 = p2.next;
            } else {
                v2 = 0;
            }
            int val = v1 + v2 + carry;
            System.out.println("val" + val);
            
            if (result == null) {
                result = new ListNode(val / 10);
                ptr = result;
            } else {
                ptr.next = new ListNode(val / 10);
                ptr = ptr.next;
            }

            carry = val % 10;

            if (p1 == null && p2 == null && carry == 0) {
                ptr.next = null;
                break;
            }
        }

        Solution.dumpList(result);

        return result;
    }
}

public class p2 {
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
    
    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);
    
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }
    
    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }
    
        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {

            while ((line = in.readLine()) != null) {
                ListNode l1 = stringToListNode(line);
                line = in.readLine();
                ListNode l2 = stringToListNode(line);
                
                ListNode ret = new Solution().addTwoNumbers(l1, l2);
                
                String out = listNodeToString(ret);
                
                System.out.print(out);
            }
        } catch (IOException ioe) {
            System.out.print(ioe);
        }
    }
}