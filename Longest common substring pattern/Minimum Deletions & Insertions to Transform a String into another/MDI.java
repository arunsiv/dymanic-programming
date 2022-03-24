public class MDI {
    //O(m*n) time | O(m∗n) space
    //where ‘m’ and ‘n’ are the lengths of the two input strings.
    public void findMDI(String s1, String s2) {
        LCS longestCommonSubsequence = new LCS();
        int lcs = longestCommonSubsequence.findLCSLengthBottomUpSpaceOptimization(s1, s2);

        System.out.println("Minimum deletions needed: " + (s1.length() - lcs));
        System.out.println("Minimum insertions needed: " + (s2.length() - lcs));
    }

    public static void main(String[] args) {
        MDI mdi = new MDI();
        mdi.findMDI("abc", "fbc");
        mdi.findMDI("abdca", "cbda");
        mdi.findMDI("passport", "ppsspt");
    }
}