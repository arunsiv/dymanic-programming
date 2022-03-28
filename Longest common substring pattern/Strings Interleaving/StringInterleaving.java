import java.util.HashMap;
import java.util.Map;

public class StringInterleaving {
    // O(2^m+n) time | O(m+n) space
    // where m, n are length of the 2 strings
    public boolean findSIBruteForce(String m, String n, String p) {
        return findSIBruteForce(m, n, p, 0, 0, 0);
    }

    public boolean findSIBruteForce(String m, String n, String p, int mIndex, int nIndex, int pIndex) {
        // if we have reached the end of the all the strings
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) {
            return true;
        }

        // if we have reached the end of 'p' but 'm' or 'n' still have some characters
        // left
        if (pIndex == p.length()) {
            return false;
        }

        boolean c1 = false, c2 = false;

        // if m.mindex char matches with p.pindex, process the remaining chars
        if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
            c1 = findSIBruteForce(m, n, p, mIndex + 1, nIndex, pIndex + 1);
        }

        // if n.nindex char matches with p.pindex, process the remaining chars
        if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
            c2 = findSIBruteForce(m, n, p, mIndex, nIndex + 1, pIndex + 1);
        }

        return c1 || c2;
    }

    // O(?) time | O(?) space
    // where m, n are length of the 2 strings
    public boolean findSITopDown(String m, String n, String p) {
        Map<String, Boolean> dp = new HashMap<>();
        return findSITopDown(dp, m, n, p, 0, 0, 0);
    }

    public boolean findSITopDown(Map<String, Boolean> dp, String m, String n, String p, int mIndex, int nIndex,
            int pIndex) {
        // if we have reached the end of the all the strings
        if (mIndex == m.length() && nIndex == n.length() && pIndex == p.length()) {
            return true;
        }

        // if we have reached the end of 'p' but 'm' or 'n' still have some characters
        // left
        if (pIndex == p.length()) {
            return false;
        }

        boolean c1 = false, c2 = false;

        String key = mIndex + "-" + nIndex + "-" + pIndex;
        if (!dp.containsKey(key)) {
            // if m.mindex char matches with p.pindex, process the remaining chars
            if (mIndex < m.length() && m.charAt(mIndex) == p.charAt(pIndex)) {
                c1 = findSITopDown(dp, m, n, p, mIndex + 1, nIndex, pIndex + 1);
            }

            // if n.nindex char matches with p.pindex, process the remaining chars
            if (nIndex < n.length() && n.charAt(nIndex) == p.charAt(pIndex)) {
                c2 = findSITopDown(dp, m, n, p, mIndex, nIndex + 1, pIndex + 1);
            }

            dp.put(key, c1 || c2);
        }

        return dp.get(key);
    }

    // O(m*n) time | O(m*n) space
    // where m, n are length of the 2 strings
    public boolean findSIBottomUp(String m, String n, String p) {
        int mLength = m.length();
        int nLength = n.length();
        int pLength = p.length();

        // dp[mIndex][nIndex] will be storing the result of string interleaving
        // up to p[0..mIndex+nIndex-1]
        boolean[][] dp = new boolean[mLength + 1][nLength + 1];

        // base check
        // make sure the lengths of the strings add up
        if (mLength + nLength != pLength) {
            return false;
        }

        int pIndex = 0;
        for (int mIndex = 0; mIndex <= mLength; mIndex++) {
            for (int nIndex = 0; nIndex <= nLength; nIndex++) {
                pIndex = mIndex + nIndex - 1;

                if (mIndex == 0 && nIndex == 0) {
                    // if 'm' and 'n' are empty, then 'p' must be empty too.
                    dp[mIndex][nIndex] = true;
                } else if (mIndex == 0 && n.charAt(nIndex - 1) == p.charAt(pIndex)) {
                    // if 'm' is empty, we need to check the interleaving with 'n' only
                    dp[mIndex][nIndex] = dp[mIndex][nIndex - 1];
                } else if (nIndex == 0 && m.charAt(mIndex - 1) == p.charAt(pIndex)) {
                    // if 'n' is empty, we need to check the interleaving with 'm' only
                    dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                } else {
                    // dp[mIndex][nIndex] = (mIndex > 0 && m.charAt(mIndex - 1) == p.charAt(pIndex)
                    // ? dp[mIndex - 1][nIndex]
                    // : false)
                    // || (nIndex > 0 && n.charAt(nIndex - 1) == p.charAt(pIndex) ?
                    // dp[mIndex][nIndex - 1]
                    // : false);

                    // if letter of 'm' and 'p' match, we take whatever is matched till mIndex-1
                    if (mIndex > 0 && m.charAt(mIndex - 1) == p.charAt(pIndex)) {
                        dp[mIndex][nIndex] = dp[mIndex - 1][nIndex];
                    }

                    // if letter of 'n' and 'p' match, we take whatever is matched till nIndex-1
                    // note the '|=', this is required when we have common letters
                    if (nIndex > 0 && n.charAt(nIndex - 1) == p.charAt(pIndex)) {
                        dp[mIndex][nIndex] |= dp[mIndex][nIndex - 1];
                    }
                }
            }
        }

        for (int startIndex = 0; startIndex <= mLength; startIndex++) {
            for (int endIndex = 0; endIndex <= nLength; endIndex++) {
                // System.out.println(startIndex + "-----------" + endIndex);
                System.out.print(dp[startIndex][endIndex] + "\t");
            }
            System.out.println("");
        }
        System.out.println("-----------");

        return dp[mLength][nLength];
    }

    public static void main(String[] args) {
        StringInterleaving si = new StringInterleaving();

        // System.out.println(si.findSIBruteForce("abd", "cef", "abcdef"));
        // System.out.println(si.findSIBruteForce("abd", "cef", "adcbef"));
        // System.out.println(si.findSIBruteForce("abc", "def", "abdccf"));
        // System.out.println(si.findSIBruteForce("abcdef", "mnop", "mnaobcdepf"));

        // System.out.println(si.findSITopDown("abd", "cef", "abcdef"));
        // System.out.println(si.findSITopDown("abd", "cef", "adcbef"));
        // System.out.println(si.findSITopDown("abc", "def", "abdccf"));
        // System.out.println(si.findSITopDown("abcdef", "mnop", "mnaobcdepf"));

        // System.out.println(si.findSIBottomUp("abd", "cef", "abcdef"));
        // System.out.println(si.findSIBottomUp("abd", "cef", "adcbef"));
        // System.out.println(si.findSIBottomUp("abc", "def", "abdccf"));
        System.out.println(si.findSIBottomUp("abcdef", "mnop", "mnaobcdepf"));
    }
}