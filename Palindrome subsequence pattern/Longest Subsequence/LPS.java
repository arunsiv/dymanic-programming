public class LPS {
    // O(3^n) time | O(n) space
    public int findLPSLengthBruteForce(String str) {
        return findLPSLengthBruteForceRecursive(str, 0, str.length() - 1);
    }

    private int findLPSLengthBruteForceRecursive(String str, int startIndex, int endIndex) {
        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return 1;
        }

        // case 1 -> elements at the beginning and the end are the same
        if (str.charAt(startIndex) == str.charAt(endIndex)) {
            return 2 + findLPSLengthBruteForceRecursive(str, startIndex + 1, endIndex - 1);
        } else {
            // case 2 -> skip one element either from the beginning or the end
            int c1 = findLPSLengthBruteForceRecursive(str, startIndex + 1, endIndex);
            int c2 = findLPSLengthBruteForceRecursive(str, startIndex, endIndex - 1);

            return Math.max(c1, c2);
        }
    }

    // O(N^2) time -> N * N
    // O(N^2) space -> O(N^2 + N) = O(N^2) [O(N^2) for dp array & O(N) for recursive stack]
    // where N is the length of the string
    public int findLPSLengthTopDown(String str) {
        Integer[][] dp = new Integer[str.length()][str.length()];
        return findLPSLengthTopDownRecursive(dp, str, 0, str.length() - 1);
    }

    private int findLPSLengthTopDownRecursive(Integer[][] dp, String str, int startIndex, int endIndex) {
        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return 1;
        }

        if (dp[startIndex][endIndex] == null) {
            // case 1 -> elements at the beginning and the end are the same
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                dp[startIndex][endIndex] = 2 + findLPSLengthBruteForceRecursive(str, startIndex + 1, endIndex - 1);
            } else {
                // case 2 -> skip one element either from the beginning or the end
                int c1 = findLPSLengthBruteForceRecursive(str, startIndex + 1, endIndex);
                int c2 = findLPSLengthBruteForceRecursive(str, startIndex, endIndex - 1);

                dp[startIndex][endIndex] = Math.max(c1, c2);
            }
        }

        return dp[startIndex][endIndex];
    }

    // O(N^2) time | O(N^2) space
    // where N is the length of the string
    public int findLPSLengthBottomUp(String str) {
        // base checks
        int n = str.length();
        //System.out.println("n: " + n);

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
            //System.out.println(startIndex);
            for (int endIndex = startIndex + 1; endIndex < n; endIndex++) {
                //System.out.println(startIndex + " :: " + endIndex);
                if (str.charAt(startIndex) == str.charAt(endIndex)) {
                    dp[startIndex][endIndex] = 2 + dp[startIndex + 1][endIndex - 1];
                } else {
                    dp[startIndex][endIndex] = Math.max(dp[startIndex + 1][endIndex], dp[startIndex][endIndex - 1]);
                }
            }
        }

        for (int startIndex = 0; startIndex <= n - 1; startIndex++) {
            for (int endIndex = 0; endIndex <= n - 1; endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        LPS lps = new LPS();
        // System.out.println(lps.findLPSLengthBruteForce("abdbca"));
        // System.out.println(lps.findLPSLengthBruteForce("cddpd"));
        // System.out.println(lps.findLPSLengthBruteForce("pqr"));
        // System.out.println(lps.findLPSLengthBruteForce(""));

        // System.out.println(lps.findLPSLengthTopDown("abdbca"));
        // System.out.println(lps.findLPSLengthTopDown("cddpd"));
        // System.out.println(lps.findLPSLengthTopDown("pqr"));
        // System.out.println(lps.findLPSLengthTopDown(""));

        //System.out.println(lps.findLPSLengthBottomUp("abdbca"));
        //System.out.println(lps.findLPSLengthBottomUp("cddpd"));
        System.out.println(lps.findLPSLengthBottomUp("pqr"));
        System.out.println(lps.findLPSLengthBottomUp(""));
    }
}