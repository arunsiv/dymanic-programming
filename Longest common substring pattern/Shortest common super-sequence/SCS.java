public class SCS {
    // O(2^m+n) time | O(m+n) space
    // m and n are length of the 2 strings
    public int findSCSLengthRecursive(String s1, String s2) {
        return findSCSLengthRecursive(s1, s2, 0, 0);
    }

    public int findSCSLengthRecursive(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length()) {
            return s2.length() - i2;
        }

        if (i2 == s2.length()) {
            return s1.length() - i1;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return 1 + findSCSLengthRecursive(s1, s2, i1 + 1, i2 + 1);
        } else {

            int length1 = 1 + findSCSLengthRecursive(s1, s2, i1 + 1, i2);
            int length2 = 1 + findSCSLengthRecursive(s1, s2, i1, i2 + 1);

            return Math.min(length1, length2);
        }
    }

    // O(2^m+n) time | O(m+n) space
    // m and n are length of the 2 strings
    public int findSCSLengthRecursive1(String s1, String s2) {
        return findSCSLengthRecursive1(s1, s2, s1.length(), s2.length());
    }

    public int findSCSLengthRecursive1(String s1, String s2, int i1, int i2) {
        // if the end of either sequence is reached, return
        // the length of another sequence
        if (i1 == 0 || i2 == 0) {
            return i1 + i2;
        }

        if (s1.charAt(i1 - 1) == s2.charAt(i2 - 1)) {
            return 1 + findSCSLengthRecursive1(s1, s2, i1 - 1, i2 - 1);
        }

        return Math.min((1 + findSCSLengthRecursive1(s1, s2, i1 - 1, i2)),
                (1 + findSCSLengthRecursive1(s1, s2, i1, i2 - 1)));
    }

    // O(m*n) time | O(m*n) space
    // m and n are length of the 2 strings
    public int findSCSLengthTopDown(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        return findSCSLengthTopDown(dp, s1, s2, 0, 0);
    }

    public int findSCSLengthTopDown(int[][] dp, String s1, String s2, int i1, int i2) {
        if (i1 == s1.length()) {
            return s2.length() - i2;
        }

        if (i2 == s2.length()) {
            return s1.length() - i1;
        }

        if (dp[i1][i2] == 0) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                dp[i1][i2] = 1 + findSCSLengthTopDown(dp, s1, s2, i1 + 1, i2 + 1);
            } else {

                int length1 = 1 + findSCSLengthTopDown(dp, s1, s2, i1 + 1, i2);
                int length2 = 1 + findSCSLengthTopDown(dp, s1, s2, i1, i2 + 1);

                dp[i1][i2] = Math.min(length1, length2);
            }
        }

        return dp[i1][i2];
    }

    // O(m*n) time | O(m*n) space
    // m and n are length of the 2 strings
    public int findSCSLengthBottomUp(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if (m == 0 || n == 0) {
            return m + n;
        }

        int[][] dp = new int[m + 1][n + 1];

        // if one of the strings is of zero length, SCS would be equal to the length of
        // the other string
        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(1 + dp[i - 1][j], 1 + dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        SCS scs = new SCS();
        System.out.println(scs.findSCSLengthRecursive("abcf", "bdcf"));
        System.out.println(scs.findSCSLengthRecursive("dynamic", "programming"));

        System.out.println(scs.findSCSLengthRecursive1("abcf", "bdcf"));
        System.out.println(scs.findSCSLengthRecursive1("dynamic", "programming"));

        System.out.println(scs.findSCSLengthTopDown("abcf", "bdcf"));
        System.out.println(scs.findSCSLengthTopDown("dynamic", "programming"));

        System.out.println(scs.findSCSLengthBottomUp("abcf", "bdcf"));
        System.out.println(scs.findSCSLengthBottomUp("dynamic", "programming"));
    }
}