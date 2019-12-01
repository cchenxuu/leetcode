#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    int reverse(int x) {
        #undef DEBUG
        long rev = 0, tmp = x;
        int left = 0,  negative = 0;
        #ifdef DEBUG
        printf("pre: x %d | 0x%x\n", x, x);
        #endif
        if (x < 0) {
            negative = 1;
            tmp = ~tmp + 1;
        }
        #ifdef DEBUG
        printf("post: x %d | 0x%x, neg %d\n", x, x, negative);
        #endif
        while (tmp) {
            left = tmp % 10;
            #ifdef DEBUG
            printf("pre: left %d, rev %ld, tmp %d\n", left, rev, tmp);
            #endif
            rev = rev * 10 + left;
            tmp = tmp / 10;
            #ifdef DEBUG
            printf("post: left %d, rev %ld, tmp %d\n", left, rev, tmp);
            #endif
        }
        if (rev & 0xffffffff80000000UL) {
            #ifdef DEBUG
            printf("rev %ld overflow\n", rev);
            #endif
            return 0;
        }
        if (negative) {
            return -rev;
        } else {
            return rev;
        }
    }
};

int stringToInteger(string input) {
    return stoi(input);
}

int main() {
    string line;
    while (getline(cin, line)) {
        int x = stringToInteger(line);
        
        int ret = Solution().reverse(x);

        string out = to_string(ret);
        cout << out << endl;
    }
    return 0;
}
