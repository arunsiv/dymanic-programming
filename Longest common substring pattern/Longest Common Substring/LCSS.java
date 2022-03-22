public class LCSS {
    // O(3^m+n) time | O(m+n) space
    // where m & n are length of 2 input strings
    public int findLCSSLengthBruteForce(String s1, String s2) {
        return findLCSSLengthBruteForce(s1, s2, 0, 0, 0);
    }

    public int findLCSSLengthBruteForce(String s1, String s2, int i1, int i2, int count) {
        // base checks
        if (i1 == s1.length() || i2 == s2.length()) {
            return count;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            count = findLCSSLengthBruteForce(s1, s2, i1 + 1, i2 + 1, count + 1);
        }

        int c1 = findLCSSLengthBruteForce(s1, s2, i1 + 1, i2, 0);
        int c2 = findLCSSLengthBruteForce(s1, s2, i1, i2 + 1, 0);

        return Math.max(count, Math.max(c1, c2));
    }

    // O(?) time | O(?m*n*k?) space
    // where m & n are length of 2 input strings
    public int findLCSSLengthTopDown(String s1, String s2) {
        int maxLength = Math.min(s1.length(), s2.length());
        int[][][] dp = new int[s1.length()][s2.length()][maxLength];
        return findLCSSLengthTopDown(dp, s1, s2, 0, 0, 0);
    }

    public int findLCSSLengthTopDown(int[][][] dp, String s1, String s2, int i1, int i2, int count) {
        // base checks
        if (i1 == s1.length() || i2 == s2.length()) {
            return count;
        }

        if (dp[i1][i2][count] == 0) {
            int c1 = count;

            if (s1.charAt(i1) == s2.charAt(i2)) {
                c1 = findLCSSLengthTopDown(dp, s1, s2, i1 + 1, i2 + 1, count + 1);
            }

            int c2 = findLCSSLengthTopDown(dp, s1, s2, i1 + 1, i2, 0);
            int c3 = findLCSSLengthTopDown(dp, s1, s2, i1, i2 + 1, 0);

            dp[i1][i2][count] = Math.max(c1, Math.max(c2, c3));
        }

        return dp[i1][i2][count];
    }

    // O(m*n) time | O(m*n) space
    // where m & n are length of 2 input strings
    public int findLCSSBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }

        for (int startIndex = 0; startIndex <= s1.length(); startIndex++) {
            for (int endIndex = 0; endIndex <= s2.length(); endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return maxLength;
    }

    // O(m*n) time | O(n) space
    // where m & n are length of 2 input strings
    public int findLCSSBottomUpSpaceOptimization(String s1, String s2) {
        int[][] dp = new int[2][s2.length() + 1];

        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i % 2][j] = 0;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i % 2][j] = 1 + dp[(i - 1) % 2][j - 1];
                    maxLength = Math.max(maxLength, dp[i % 2][j]);
                }
            }
        }

        for (int startIndex = 0; startIndex <= 1; startIndex++) {
            for (int endIndex = 0; endIndex <= s2.length(); endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LCSS lcs = new LCSS();
        // System.out.println(lcs.findLCSSLengthBruteForce("abdca", "cbda"));
        // System.out.println(lcs.findLCSSLengthBruteForce("passport", "ppsspt"));
        // System.out.println(lcs.findLCSSLengthBruteForce("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSSLengthBruteForce("aaa", "bbb"));

        // System.out.println(lcs.findLCSSLengthTopDown("abdca", "cbda"));
        // System.out.println(lcs.findLCSSLengthTopDown("passport", "ppsspt"));
        // System.out.println(lcs.findLCSSLengthTopDown("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSSLengthTopDown("aaa", "abb"));

        System.out.println(lcs.findLCSSBottomUp("abdca", "cbda"));
        // System.out.println(lcs.findLCSSBottomUp("passport", "ppsspt"));
        // System.out.println(lcs.findLCSSBottomUp("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSSBottomUp("", ""));

        System.out.println(lcs.findLCSSBottomUpSpaceOptimization("abdca", "cbda"));
        // System.out.println(lcs.findLCSSBottomUpSpaceOptimization("passport", "ppsspt"));
        // System.out.println(lcs.findLCSSBottomUpSpaceOptimization("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSSBottomUpSpaceOptimization("", ""));
    }
}