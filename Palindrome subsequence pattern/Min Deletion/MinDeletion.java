public class MinDeletion {

    public int findMinimumDeletions(String str) {
        // subtracting the length of Longest Palindromic Subsequence from the length of
        // the input string to get minimum number of deletions
        int lps = findLPSLengthBottomUp(str);
        return str.length() - lps;
    }

    public int findMinimumInsertionss(String str) {
        // subtracting the length of Longest Palindromic Subsequence from the length of
        // the input string to get minimum number of insertions
        int lps = findLPSLengthBottomUp(str);
        return str.length() - lps;
    }

    // O(N^2) time | O(N^2) space
    // where N is the length of the string
    public int findLPSLengthBottomUp(String str) {
        // base checks
        int n = str.length();
        // System.out.println("n: " + n);

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        //
        for (int startIndex = n - 1; startIndex >= 0; startIndex--) {
            // System.out.println(startIndex);
            for (int endIndex = startIndex + 1; endIndex < n; endIndex++) {
                // System.out.println(startIndex + " :: " + endIndex);
                if (str.charAt(startIndex) == str.charAt(endIndex)) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                } else {
                    dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);
                }
            }
        }

        // for (int startIndex = 0; startIndex <= n - 1; startIndex++) {
        //     for (int endIndex = 0; endIndex <= n - 1; endIndex++) {
        //         System.out.print(dp[startIndex][endIndex] + "\t");
        //     }
        //     System.out.println("");
        // }

        return dp[0][n - 1];
    }

    // O(N^2) time | O(N^2) space
    // where N is the length of the string
    public int findLPSLengthBottomUp2(String str) {
        int n = str.length();

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        String strRev = new StringBuilder(str).reverse().toString();

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i + 1][j + 1] = str.charAt(i) == strRev.charAt(j) ? dp[i][j] + 1
                        : Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }

        for (int startIndex = 0; startIndex <= n; startIndex++) {
            for (int endIndex = 0; endIndex <= n; endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return dp[n][n];
    }

    public static void main(String[] args) {
        MinDeletion minDeletion = new MinDeletion();

        System.out.println(minDeletion.findMinimumDeletions("abdbca"));
        System.out.println(minDeletion.findMinimumDeletions("cddpd"));
        System.out.println(minDeletion.findMinimumDeletions("pqr"));
        System.out.println(minDeletion.findMinimumDeletions("leetcode"));

        System.out.println(minDeletion.findMinimumInsertionss("abdbca"));
        System.out.println(minDeletion.findMinimumInsertionss("cddpd"));
        System.out.println(minDeletion.findMinimumInsertionss("pqr"));
        System.out.println(minDeletion.findMinimumInsertionss("leetcode"));
    }
}
