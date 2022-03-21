public class PalindromePartition {
    // O(2^n) time | O(n) space
    // where n is the length of the string
    public int findMPPCutsBruteForce(String str) {
        return findMPPCutsBruteForceRecursive(str, 0, str.length() - 1);
    }

    private int findMPPCutsBruteForceRecursive(String str, int startIndex, int endIndex) {
        // we don't need to cut the string if it is a palindrome
        if (startIndex >= endIndex || isPalindromeBruteForce(str, startIndex, endIndex)) {
            return 0;
        }

        // at max, we need to cut the string into its 'length-1' pieces
        int minCuts = endIndex - startIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if (isPalindromeBruteForce(str, startIndex, i)) {
                // we can cut here as we have a palindrome from 'startIndex' to 'i'
                minCuts = Math.min(minCuts, 1 + findMPPCutsBruteForceRecursive(str, i + 1, endIndex));
            }
        }

        return minCuts;
    }

    private boolean isPalindromeBruteForce(String str, int sIndex, int eIndex) {
        while (sIndex < eIndex) {
            if (str.charAt(sIndex++) != str.charAt(eIndex--)) {
                return false;
            }
        }

        return true;
    }

    // O(2^n) time | O(n) space
    // where n is the length of the string
    public int findMPPCutsTopDown(String str) {
        Integer[][] dp = new Integer[str.length()][str.length()];
        Boolean[][] dpIsPalindrome = new Boolean[str.length()][str.length()];
        return findMPPCutsTopDownRecursive(dp, dpIsPalindrome, str, 0, str.length() - 1);
    }

    private int findMPPCutsTopDownRecursive(Integer[][] dp, Boolean[][] dpIsPalindrome, String str, int startIndex,
            int endIndex) {
        // we don't need to cut the string if it is a palindrome
        if (startIndex >= endIndex || isPalindromeTopDown(dpIsPalindrome, str, startIndex, endIndex)) {
            return 0;
        }

        if (dp[startIndex][endIndex] == null) {
            // at max, we need to cut the string into its 'length-1' pieces
            int minCuts = endIndex - startIndex;
            for (int i = startIndex; i <= endIndex; i++) {
                if (isPalindromeTopDown(dpIsPalindrome, str, startIndex, i)) {
                    // we can cut here as we have a palindrome from 'startIndex' to 'i'
                    minCuts = Math.min(minCuts,
                            1 + findMPPCutsTopDownRecursive(dp, dpIsPalindrome, str, i + 1, endIndex));
                }
            }

            dp[startIndex][endIndex] = minCuts;
        }

        return dp[startIndex][endIndex];
    }

    private boolean isPalindromeTopDown(Boolean[][] dpIsPalindrome, String str, int sIndex, int eIndex) {
        if (dpIsPalindrome[sIndex][eIndex] == null) {
            dpIsPalindrome[sIndex][eIndex] = true;

            int i = sIndex, j = eIndex;

            while (i < j) {
                if (str.charAt(i++) != str.charAt(j--)) {
                    dpIsPalindrome[sIndex][eIndex] = false;
                    break;
                }

                // use memoization to find if the remaining string is a palindrome
                if (i < j && dpIsPalindrome[i][j] != null) {
                    dpIsPalindrome[sIndex][eIndex] = dpIsPalindrome[i][j];
                    break;
                }
            }
        }

        return dpIsPalindrome[sIndex][eIndex];
    }

    // O(n^2) time | O(n) space
    // where n is the length of the string
    public int findMPPCutsBottomUp(String str) {
        int n = str.length();

        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }

        for (int startIndex = n - 1; startIndex >= 0; startIndex--) {
            for (int endIndex = startIndex + 1; endIndex < n; endIndex++) {
                if (str.charAt(startIndex) == str.charAt(endIndex)) {
                    if (endIndex - startIndex == 1 || isPalindrome[startIndex + 1][endIndex - 1]) {
                        isPalindrome[startIndex][endIndex] = true;
                    }
                }
            }
        }

        int [] cuts = new int[n];
        for (int startIndex = n-1; startIndex>=0; startIndex--) {
            int minCuts = n; // maximum cuts
            for (int endIndex = n-1; endIndex>=startIndex; endIndex--) {
                if (isPalindrome[startIndex][endIndex]) {
                    minCuts = (endIndex == n -1) ? 0 : Math.min(minCuts, 1 + cuts[endIndex + 1]);
                }
            }
            cuts[startIndex] = minCuts;
        }

        for (int i = 0; i <= n - 1; i++) {
            System.out.print(cuts[i] + "\t");
        }

        System.out.println("\n---------");
        return cuts[0];
    }

    public static void main(String[] args) {
        PalindromePartition mpp = new PalindromePartition();
        // System.out.println(mpp.findMPPCutsBruteForce("abdbca"));
        // System.out.println(mpp.findMPPCutsBruteForce("cdpdd"));
        // System.out.println(mpp.findMPPCutsBruteForce("pqr"));
        // System.out.println(mpp.findMPPCutsBruteForce("pp"));

        // System.out.println(mpp.findMPPCutsTopDown("abdbca"));
        // System.out.println(mpp.findMPPCutsTopDown("cdpdd"));
        // System.out.println(mpp.findMPPCutsTopDown("pqr"));
        // System.out.println(mpp.findMPPCutsTopDown("pp"));
        // System.out.println(mpp.findMPPCutsTopDown("malay"));

        System.out.println(mpp.findMPPCutsBottomUp("abdbca"));
        System.out.println(mpp.findMPPCutsBottomUp("cdpdd"));
        System.out.println(mpp.findMPPCutsBottomUp("pqr"));
        System.out.println(mpp.findMPPCutsBottomUp("pp"));
        System.out.println(mpp.findMPPCutsBottomUp("malayalam"));
    }
}