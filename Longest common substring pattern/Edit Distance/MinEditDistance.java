public class MinEditDistance {
    // O(3^m+n) time | O(m+n) space
    // where m and n are length of 2 input strings
    public int findMinOperationsBruteForce(String s1, String s2) {
        return findMinOperationsBruteForce(s1, s2, 0, 0);
    }

    public int findMinOperationsBruteForce(String s1, String s2, int i1, int i2) {
        if (i1 == s1.length()) {
            return s2.length() - i2;
        }

        if (i2 == s2.length()) {
            return s1.length() - i1;
        }

        if (s1.charAt(i1) == s2.charAt(i2)) {
            return findMinOperationsBruteForce(s1, s2, i1 + 1, i2 + 1);
        } else {
            int c1 = 1 + findMinOperationsBruteForce(s1, s2, i1 + 1, i2);
            int c2 = 1 + findMinOperationsBruteForce(s1, s2, i1, i2 + 1);
            int c3 = 1 + findMinOperationsBruteForce(s1, s2, i1 + 1, i2 + 1);

            return Math.min(c1, Math.min(c2, c3));
        }
    }

    // O(m*n) time | O(m*n) space
    // where m and n are length of 2 input strings
    public int findMinOperationsTopDown(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        return findMinOperationsTopDown(dp, s1, s2, 0, 0);
    }

    public int findMinOperationsTopDown(int[][] dp, String s1, String s2, int i1, int i2) {
        if (i1 == s1.length()) {
            return s2.length() - i2;
        }

        if (i2 == s2.length()) {
            return s1.length() - i1;
        }

        if (dp[i1][i2] == 0) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                dp[i1][i2] = findMinOperationsTopDown(dp, s1, s2, i1 + 1, i2 + 1);
            } else {
                int c1 = 1 + findMinOperationsTopDown(dp, s1, s2, i1 + 1, i2);
                int c2 = 1 + findMinOperationsTopDown(dp, s1, s2, i1, i2 + 1);
                int c3 = 1 + findMinOperationsTopDown(dp, s1, s2, i1 + 1, i2 + 1);

                dp[i1][i2] = Math.min(c1, Math.min(c2, c3));
            }
        }

        return dp[i1][i2];
    }

    // O(m*n) time | O(m*n) space
    // where m and n are length of 2 input strings
    public int findMinOperationsBottomUp(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        int insert = 0, delete = 0, replace = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    insert = dp[i][j - 1];
                    delete = dp[i - 1][j];
                    replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(insert, Math.min(delete, replace));
                }
            }
        }

        // for (int startIndex = 0; startIndex <= m; startIndex++) {
        // for (int endIndex = 0; endIndex <= n; endIndex++) {
        // System.out.print(dp[startIndex][endIndex] + "\t");
        // }
        // System.out.println("");
        // }

        printEdits(dp, s1.toCharArray(), s2.toCharArray());

        return dp[m][n];
    }

    public void printEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;

        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                System.out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }
        }
    }

    public static void main(String[] args) {
        MinEditDistance editDisatnce = new MinEditDistance();

        // System.out.println(editDisatnce.findMinOperationsBruteForce("bat", "but"));
        // System.out.println(editDisatnce.findMinOperationsBruteForce("abdca",
        // "cbda"));
        // System.out.println(editDisatnce.findMinOperationsBruteForce("passpot",
        // "ppsspqrt"));

        // System.out.println(editDisatnce.findMinOperationsTopDown("bat", "but"));
        // System.out.println(editDisatnce.findMinOperationsTopDown("abdca", "cbda"));
        // System.out.println(editDisatnce.findMinOperationsTopDown("passpot",
        // "ppsspqrt"));

        // System.out.println(editDisatnce.findMinOperationsBottomUp("bat", "but"));
        // System.out.println(editDisatnce.findMinOperationsBottomUp("abdca", "cbda"));
        // System.out.println(editDisatnce.findMinOperationsBottomUp("passpot", "ppsspqrt"));
        System.out.println(editDisatnce.findMinOperationsBottomUp("abc", "abcd"));
    }
}