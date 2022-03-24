public class LCS {
    // O(2^m+n) time | O(m+n) space
    // where m, n are length of the 2 strings
    public int findLCSLengthBruteForce(String s1, String s2) {
        return findLCSLengthBruteForce(s1, s2, 0, 0);
    }

    public int findLCSLengthBruteForce(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length() || i2 == s2.length()) {
            return 0;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + findLCSLengthBruteForce(s1, s2, i1 + 1, i2 + 1);
        } else {
            int c1 = findLCSLengthBruteForce(s1, s2, i1 + 1, i2);
            int c2 = findLCSLengthBruteForce(s1, s2, i1, i2 + 1);

            return Math.max(c1, c2);
        }
    }

    // O(m*n) time | O(m*n) space
    // where m, n are length of the 2 strings
    public int findLCSLengthTopDown(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        return findLCSLengthTopDown(dp, s1, s2, 0, 0);
    }

    public int findLCSLengthTopDown(int[][] dp, String s1, String s2, int i1, int i2) {
        if (i1 == s1.length() || i2 == s2.length()) {
            return 0;
        }

        if (dp[i1][i2] == 0) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                dp[i1][i2] = 1 + findLCSLengthTopDown(dp, s1, s2, i1 + 1, i2 + 1);
            } else {
                int c1 = findLCSLengthTopDown(dp, s1, s2, i1 + 1, i2);
                int c2 = findLCSLengthTopDown(dp, s1, s2, i1, i2 + 1);

                dp[i1][i2] = Math.max(c1, c2);
            }
        }

        return dp[i1][i2];
    }

    // O(m*n) time | O(m*n) space
    // where m, n are length of the 2 strings
    public int findLCSLengthBottomUp(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 1 + dp[i - 1][j - 1]
                        : Math.max(dp[i][j - 1], dp[i - 1][j]);

                maxLength = Math.max(maxLength, dp[i][j]);
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
    // where m, n are length of the 2 strings
    public int findLCSLengthBottomUpSpaceOptimization(String s1, String s2) {
        int[][] dp = new int[2][s2.length() + 1];

        int maxLength = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i % 2][j] = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 1 + dp[(i - 1) % 2][j - 1]
                        : Math.max(dp[i % 2][j - 1], dp[(i - 1) % 2][j]);

                maxLength = Math.max(maxLength, dp[i % 2][j]);
            }
        }

        // for (int startIndex = 0; startIndex <= 1; startIndex++) {
        //     for (int endIndex = 0; endIndex <= s2.length(); endIndex++) {
        //         System.out.print(dp[startIndex][endIndex] + "\t");
        //     }
        //     System.out.println("");
        // }

        return maxLength;
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        // System.out.println(lcs.findLCSLengthBruteForce("abdca", "cbda"));
        // System.out.println(lcs.findLCSLengthBruteForce("passport", "ppsspt"));
        // System.out.println(lcs.findLCSLengthBruteForce("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSLengthBruteForce("malayalam", "r"));

        // System.out.println(lcs.findLCSLengthTopDown("abdca", "cbda"));
        // System.out.println(lcs.findLCSLengthTopDown("passport", "ppsspt"));
        // System.out.println(lcs.findLCSLengthTopDown("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSLengthTopDown("malayalam", "r"));

        System.out.println(lcs.findLCSLengthBottomUp("abdca", "cbda"));
        // System.out.println(lcs.findLCSLengthBottomUp("passport", "ppsspt"));
        // System.out.println(lcs.findLCSLengthBottomUp("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSLengthBottomUp("malayalam", "r"));

        System.out.println(lcs.findLCSLengthBottomUpSpaceOptimization("abdca", "cbda"));
        // System.out.println(lcs.findLCSLengthBottomUpSpaceOptimization("passport", "ppsspt"));
        // System.out.println(lcs.findLCSLengthBottomUpSpaceOptimization("abcdef", "zcdemf"));
        // System.out.println(lcs.findLCSLengthBottomUpSpaceOptimization("malayalam", "r"));
    }
}