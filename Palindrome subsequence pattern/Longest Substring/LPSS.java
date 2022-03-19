public class LPSS {
    // O(2^N) time | O(N) space
    public int findLPSSLengthBruteForce(String str) {
        return findLPSSLengthBruteForceRecursive(str, 0, str.length() - 1);
    }

    private int findLPSSLengthBruteForceRecursive(String str, int startIndex, int endIndex) {
        // base checks
        if (str.length() == 0 || startIndex > endIndex) {
            return 0;
        }

        if (startIndex == endIndex) {
            return 1;
        }

        // case 1
        if (str.charAt(startIndex) == str.charAt(endIndex)) {
            int remaininglength = endIndex - startIndex - 1;

            if (remaininglength == findLPSSLengthBruteForceRecursive(str, startIndex + 1, endIndex - 1)) {
                return remaininglength + 2;
            }
        }

        // case 2
        int c1 = findLPSSLengthBruteForceRecursive(str, startIndex + 1, endIndex);
        int c2 = findLPSSLengthBruteForceRecursive(str, startIndex, endIndex - 1);

        return Math.max(c1, c2);
    }

    // O(N^2) time -> N * N
    // O(N^2) space -> O(N^2 + N) = O(N^2) [O(N^2) for dp array & O(N) for recursive
    // stack]
    // where N is the length of the string
    public int findLPSSLengthTopDown(String str) {
        Integer[][] dp = new Integer[str.length()][str.length()];
        return findLPSSLengthTopDownRecursive(dp, str, 0, str.length() - 1);
    }

    private int findLPSSLengthTopDownRecursive(Integer[][] dp, String str, int startIndex, int endIndex) {
        // base checks
        if (startIndex > endIndex) {
            return 0;
        }

        // every string with one character is a palindrome
        if (startIndex == endIndex) {
            return 1;
        }

        if (dp[startIndex][endIndex] == null) {
            // case 1
            if (str.charAt(startIndex) == str.charAt(endIndex)) {
                int remaininglength = endIndex - startIndex - 1;

                if (remaininglength == findLPSSLengthTopDownRecursive(dp, str, startIndex + 1, endIndex - 1)) {
                    dp[startIndex][endIndex] = remaininglength + 2;
                    return dp[startIndex][endIndex];
                }
            }

            // case 2
            int c1 = findLPSSLengthTopDownRecursive(dp, str, startIndex + 1, endIndex);
            int c2 = findLPSSLengthTopDownRecursive(dp, str, startIndex, endIndex - 1);

            dp[startIndex][endIndex] = Math.max(c1, c2);
        }

        // for (int sIndex = 0; sIndex <= str.length() - 1; sIndex++) {
        // for (int eIndex = 0; eIndex <= str.length() - 1; eIndex++) {
        // System.out.print(dp[sIndex][eIndex] + "\t");
        // }
        // System.out.println("");
        // }

        return dp[startIndex][endIndex];
    }

    // O(N^2) time | O(N^2) space
    public int findLPSSLengthBottomUp(String str) {
        int n = str.length();

        // base checks
        if (n == 0) {
            return 0;
        }

        boolean[][] dp = new boolean[n][n];

        //
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        for (int startIndex = n - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < n; endIndex++) {
                if (str.charAt(startIndex) == str.charAt(endIndex)) {
                    // if it's a two character string or the remaining string is a palindrome too
                    if (endIndex - startIndex == 1 || dp[startIndex + 1][endIndex - 1]) {
                        dp[startIndex][endIndex] = true;
                        maxLength = Math.max(maxLength, endIndex - startIndex + 1);
                    }
                }
            }
        }

        for (int startIndex = 0; startIndex <= n - 1; startIndex++) {
            for (int endIndex = 0; endIndex <= n - 1; endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LPSS lpss = new LPSS();
        // System.out.println(lpss.findLPSSLengthBruteForce("abdbca"));
        // System.out.println(lpss.findLPSSLengthBruteForce("cddpd"));
        // System.out.println(lpss.findLPSSLengthBruteForce("pqr"));
        // System.out.println(lpss.findLPSSLengthBruteForce("malayalam"));
        // System.out.println(lpss.findLPSSLengthBruteForce(""));

        // System.out.println(lpss.findLPSSLengthTopDown("abdbca"));
        // System.out.println(lpss.findLPSSLengthTopDown("cddpd"));
        // System.out.println(lpss.findLPSSLengthTopDown("pqr"));
        // System.out.println(lpss.findLPSSLengthTopDown("malayalam"));
        // System.out.println(lpss.findLPSSLengthTopDown(""));

        System.out.println(lpss.findLPSSLengthBottomUp("abdbca"));
        // System.out.println(lpss.findLPSSLengthBottomUp("cddpd"));
        // System.out.println(lpss.findLPSSLengthBottomUp("pqr"));
        // System.out.println(lpss.findLPSSLengthBottomUp("malayalam"));
        // System.out.println(lpss.findLPSSLengthBottomUp(""));
    }
}