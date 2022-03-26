public class SPM {
    // O(2^n) time | O(n) space
    // where n is the length of the string
    public int findSPMCountBruteForce(String str, String pattern) {
        return findSPMCountBruteForce(str, pattern, 0, 0);
    }

    public int findSPMCountBruteForce(String str, String pattern, int strIndex, int patternIndex) {
        // if we have reached the end of the pattern, which means the subsequence is
        // found
        if (patternIndex == pattern.length()) {
            return 1;
        }

        // if we've reached the end of the string but pattern has still some characters
        // left, which mean subsequence is not found
        if (strIndex == str.length()) {
            return 0;
        }

        int count1 = 0;
        if (str.charAt(strIndex) == pattern.charAt(patternIndex)) {
            count1 = findSPMCountBruteForce(str, pattern, strIndex + 1, patternIndex + 1);
        }

        int count2 = findSPMCountBruteForce(str, pattern, strIndex + 1, patternIndex);

        return count1 + count2;
    }

    // O(mn) time | O(mn) space
    // where m is the length of the string, n is the length of the pattern
    public int findSPMCountTopDown(String str, String pattern) {
        int[][] dp = new int[str.length()][pattern.length()];
        return findSPMCountTopDown(dp, str, pattern, 0, 0);
    }

    public int findSPMCountTopDown(int[][] dp, String str, String pattern, int strIndex, int patternIndex) {
        // if we have reached the end of the pattern, which means the subsequence is
        // found
        if (patternIndex == pattern.length()) {
            return 1;
        }

        // if we've reached the end of the string but pattern has still some characters
        // left, which mean subsequence is not found
        if (strIndex == str.length()) {
            return 0;
        }

        if (dp[strIndex][patternIndex] == 0) {
            int count1 = 0;
            if (str.charAt(strIndex) == pattern.charAt(patternIndex)) {
                count1 = findSPMCountTopDown(dp, str, pattern, strIndex + 1, patternIndex + 1);
            }

            int count2 = findSPMCountTopDown(dp, str, pattern, strIndex + 1, patternIndex);

            dp[strIndex][patternIndex] = count1 + count2;
        }

        return dp[strIndex][patternIndex];
    }

    // O(mn) time | O(mn) space
    // where m is the length of the string, n is the length of the pattern
    public int findSPMCountBottomUp(String str, String pattern) {
        int m = str.length();
        int n = pattern.length();

        // every empty pattern has one match
        if (n == 0) {
            return 1;
        }

        // if string is enpty or pattern length is greater than string length
        if (m == 0 || n > m) {
            return 0;
        }

        // dp[stringIndex][patternIndex] will be storing the count of SPM up to
        // str[0..stringIndex-1][0..patternIndex-1]
        int[][] dp = new int[m + 1][n + 1];

        // for the empty pattern, we have one matching
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int stringIndex = 1; stringIndex <= m; stringIndex++) {
            for (int patternIndex = 1; patternIndex <= n; patternIndex++) {
                if (str.charAt(stringIndex - 1) == pattern.charAt(patternIndex - 1)) {
                    dp[stringIndex][patternIndex] = dp[stringIndex - 1][patternIndex - 1];
                }

                dp[stringIndex][patternIndex] += dp[stringIndex - 1][patternIndex];
            }
        }

        for (int startIndex = 0; startIndex <= m; startIndex++) {
            for (int endIndex = 0; endIndex <= n; endIndex++) {
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        SPM spm = new SPM();

        // System.out.println(spm.findSPMCountBruteForce("baxmx", "ax"));
        // System.out.println(spm.findSPMCountBruteForce("tomorrow", "tor"));
        // System.out.println(spm.findSPMCountBruteForce("subsequence", "sue"));
        // System.out.println(spm.findSPMCountBruteForce("sue", "subsequence"));

        // System.out.println(spm.findSPMCountTopDown("baxmx", "ax"));
        // System.out.println(spm.findSPMCountTopDown("tomorrow", "tor"));
        // System.out.println(spm.findSPMCountTopDown("subsequence", "sue"));
        // System.out.println(spm.findSPMCountTopDown("sue", "subsequence"));

        // System.out.println(spm.findSPMCountBottomUp("baxmx", "ax"));
        // System.out.println(spm.findSPMCountBottomUp("tomorrow", "tor"));
        System.out.println(spm.findSPMCountBottomUp("subsequence", "sue"));
        // System.out.println(spm.findSPMCountBottomUp("sue", "subsequence"));
    }
}