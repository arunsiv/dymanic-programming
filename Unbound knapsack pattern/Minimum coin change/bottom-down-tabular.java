//O(C*T)) time | O(C*T) space
//where ‘C’ represents total coin denominations and ‘T’ is the total amount that we want to make change
class MinCoinChangeTabular {
    public int countChange(int[] denominations, int total) {
        int n = denominations.length;

        //base checks
        if (n == 0 || total <= 0) {
            return -1;
        }

        int [][] dp = new int[n][total + 1];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= total; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // populate the total=0 columns, as we don't need any coin to make zero total
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for (int t = 1; t <= total; t++) {
            dp[0][t] = (denominations[0] <= t) ? (denominations[0] * t) : 0;
        }

        int count1 = Integer.MAX_VALUE, count2 = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= total; t++) {
                count1 = Integer.MAX_VALUE;
                count2 = Integer.MAX_VALUE;

                //exclude the coin
                //if (i > 0) {
                    //dp[i][t] = dp[i - 1][t];
                    count1 = dp[i - 1][t];
                //}

                //include the coin
                if (t >= denominations[i]) {
                    if (dp[i][t-denominations[i]] != Integer.MAX_VALUE) {
                        //dp[i][t] = Math.min(dp[i][t], dp[i][t-denominations[i]]+1);
                        count2 = dp[i][t-denominations[i]] + 1;
                    }
                }

                System.out.println(count1 + " :: " + count2);

                dp[i][t] = Math.min(count1, count2);
            }
        }

        return (dp[n - 1][total] == Integer.MAX_VALUE ? -1 : dp[n - 1][total]);
    }

    public static void main(String[] args) {
        MinCoinChangeTabular cc = new MinCoinChangeTabular();
        int[] denominations = { 1, 2, 3 };
        System.out.println(cc.countChange(denominations, 5));
        // System.out.println(cc.countChange(denominations, 11));
        // System.out.println(cc.countChange(denominations, 7));
        // denominations = new int[] {};
        // System.out.println(cc.countChange(denominations, 7));
    }
}