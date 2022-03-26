public class LRS {
    // O(2^n) time | O(n) space
    // where n is the length of the string
    public int findLRSLengthBruteForce(String s) {
        return findLRSLengthBruteForce(s, 0, 0);
    }

    public int findLRSLengthBruteForce(String s, int i1, int i2) {
        if (i1 == s.length() || i2 == s.length()) {
            return 0;
        }

        if (i1 != i2 && s.charAt(i1) == s.charAt(i2)) {
            return 1 + findLRSLengthBruteForce(s, i1 + 1, i2 + 1);
        } else {
            int length1 = findLRSLengthBruteForce(s, i1 + 1, i2);
            int length2 = findLRSLengthBruteForce(s, i1, i2 + 1);

            return Math.max(length1, length2);
        }
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the string
    public int findLRSLengthTopDown(String s) {
        int[][] dp = new int[s.length()][s.length()];
        return findLRSLengthTopDown(dp, s, 0, 0);
    }

    public int findLRSLengthTopDown(int[][] dp, String s, int i1, int i2) {
        if (i1 == s.length() || i2 == s.length()) {
            return 0;
        }

        if (dp[i1][i2] == 0) {
            if (i1 != i2 && s.charAt(i1) == s.charAt(i2)) {
                dp[i1][i2] = 1 + findLRSLengthTopDown(dp, s, i1 + 1, i2 + 1);
            } else {
                int length1 = findLRSLengthTopDown(dp, s, i1 + 1, i2);
                int length2 = findLRSLengthTopDown(dp, s, i1, i2 + 1);

                dp[i1][i2] = Math.max(length1, length2);
            }
        }

        return dp[i1][i2];
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the string
    public int findLRSLengthBottomUp(String s) {
        int n = s.length();

        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][n + 1];
        int maxLength = 0;

        // dp[i][j] will be storing the LRS up to str[0..i-1][0..j-1]
        // this also means that subsequences of length zero (first row and column of dp[][]),
        // will always have LRS of size zero.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j && s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }

                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }

        // for (int startIndex = 0; startIndex <= n; startIndex++) {
        //     for (int endIndex = 0; endIndex <= n; endIndex++) {
        //         System.out.print(dp[startIndex][endIndex] + "\t");
        //     }
        //     System.out.println("");
        // }

        // System.out.println("----------");

        return maxLength;
    }

    public static void main(String[] args) {
        LRS lrs = new LRS();

        // System.out.println(lrs.findLRSLengthBruteForce("tomorrow"));
        // System.out.println(lrs.findLRSLengthBruteForce("aabdbcec"));
        // System.out.println(lrs.findLRSLengthBruteForce("fmff"));
        // System.out.println(lrs.findLRSLengthBruteForce("tesf"));

        // System.out.println(lrs.findLRSLengthTopDown("tomorrow"));
        // System.out.println(lrs.findLRSLengthTopDown("aabdbcec"));
        // System.out.println(lrs.findLRSLengthTopDown("fmff"));
        // System.out.println(lrs.findLRSLengthTopDown("tesf"));

        System.out.println(lrs.findLRSLengthBottomUp("tomorrow"));
        System.out.println(lrs.findLRSLengthBottomUp("aabdbcec"));
        System.out.println(lrs.findLRSLengthBottomUp("fmff"));
        System.out.println(lrs.findLRSLengthBottomUp("tesf"));
    }
}