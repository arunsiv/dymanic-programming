public class CountSubstring {
    // O(N^2) time | O(N^2) space
    public int countLPSSubstringBottomUp(String str) {
        int n = str.length();
        int count = 0;

        // base check
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        for (int startIndex = n - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < n; endIndex++) {
                if (str.charAt(startIndex) == str.charAt(endIndex)) {
                    if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                        dp[startIndex][endIndex] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public int countLPSSubstringBruteForce(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int n = str.length();
        int res = 0;
        char[] c = str.toCharArray();

        for (int i = 0; i < n; i++) {
            // Odd and even lengths (Center for odd --> i , i)
            // Center for even --> i , i+1
            res += isPalindrome(i, i, c);
            res += isPalindrome(i, i + 1, c);
        }

        return res;
    }

    private int isPalindrome(int s, int e, char[] c) {
        int count = 0;
        while (s >= 0 && e < c.length && c[s--] == c[e++]) {
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        CountSubstring lpss = new CountSubstring();

        // System.out.println(lpss.countLPSSubstringBottomUp("abdbca"));
        // System.out.println(lpss.countLPSSubstringBottomUp("cddpd"));
        // System.out.println(lpss.countLPSSubstringBottomUp("pqr"));
        // System.out.println(lpss.countLPSSubstringBottomUp("malayalam"));
        // System.out.println(lpss.countLPSSubstringBottomUp(""));

        System.out.println(lpss.countLPSSubstringBruteForce("abdbca"));
        System.out.println(lpss.countLPSSubstringBruteForce("cddpd"));
        System.out.println(lpss.countLPSSubstringBruteForce("pqr"));
        System.out.println(lpss.countLPSSubstringBruteForce("malayalam"));
        System.out.println(lpss.countLPSSubstringBruteForce(""));
    }
}