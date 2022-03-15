public class HouseThief {
    // O(2^n) time | O(n) space
    public int findMaxStealBruteForce(int[] wealth) {
        return findMaxStealBruteForceRecursive(wealth, 0);
    }

    private int findMaxStealBruteForceRecursive(int[] wealth, int currentIndex) {
        // base checks
        if (wealth.length == 0 || currentIndex >= wealth.length) {
            return 0;
        }

        int stealCurrentHouse = wealth[currentIndex] + findMaxStealBruteForceRecursive(wealth, currentIndex + 2);
        int skipCurrentHouse = findMaxStealBruteForceRecursive(wealth, currentIndex + 1);

        return Math.max(stealCurrentHouse, skipCurrentHouse);
    }

    // O(n) time | O(n) space
    public int findMaxStealTopDown(int[] wealth) {
        // base checks
        int n = wealth.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];
        return findMaxStealTopDownRecursive(dp, wealth, 0);
    }

    private int findMaxStealTopDownRecursive(int[] dp, int[] wealth, int currentIndex) {
        if (currentIndex >= wealth.length) {
            return 0;
        }

        if (dp[currentIndex] == 0) {
            int stealCurrentHouse = wealth[currentIndex] + findMaxStealTopDownRecursive(dp, wealth, currentIndex + 2);
            int skipCurrentHouse = findMaxStealTopDownRecursive(dp, wealth, currentIndex + 1);

            dp[currentIndex] = Math.max(stealCurrentHouse, skipCurrentHouse);
        }

        return dp[currentIndex];
    }

    // O(n) time | O(n) space
    public int findMaxStealBottomUp(int[] wealth) {
        // base checks
        int n = wealth.length;
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n + 1]; // +1 to handle the zero house
        dp[0] = 0; // if there are no houses, the thief can't steal anything
        dp[1] = wealth[0]; // only one house, so the thief have to steal from it

        // please note that dp[] has one extra element to handle zero house
        // so, start from 1
        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.max(wealth[i] + dp[i - 1], dp[i]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        HouseThief ht = new HouseThief();
        int[] wealth = { 2, 5, 1, 3, 6, 2, 4 };
        // System.out.println(ht.findMaxStealBruteForce(wealth));
        // wealth = new int[] { 2, 10, 14, 8, 1 };
        // System.out.println(ht.findMaxStealBruteForce(wealth));

        // System.out.println(ht.findMaxStealTopDown(wealth));
        // wealth = new int[] { 2, 10, 14, 8, 1 };
        // System.out.println(ht.findMaxStealTopDown(wealth));
        // wealth = new int[] { 0 };
        // System.out.println(ht.findMaxStealTopDown(wealth));

        System.out.println(ht.findMaxStealBottomUp(wealth));
        wealth = new int[] { 2, 10, 14, 8, 1 };
        System.out.println(ht.findMaxStealBottomUp(wealth));
        wealth = new int[] { };
        System.out.println(ht.findMaxStealBottomUp(wealth));
    }
}