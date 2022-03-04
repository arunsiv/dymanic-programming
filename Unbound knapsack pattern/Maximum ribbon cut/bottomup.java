class MaxRibbonBottomDown {
    public int countRibbonPieces(int[] ribbonLengths, int total) {
        int n = ribbonLengths.length;

        if (n == 0 || total == 0) {
            return -1;
        }

        int [][] dp = new int [n][total + 1];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= total; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= total; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("");
        }   

        System.out.println("---------------------------------");

        for (int i = 0; i < n; i++) {
            for (int t = 1; t <= total; t++) {
                //exclude the ribbon
                if (i > 0) {
                    dp[i][t] = dp[i - 1][t];
                }

                // include the ribbon and check if the remaining length can be cut into 
                // available lengths
                if (t >= ribbonLengths[i] && dp[i][t - ribbonLengths[i]] != Integer.MIN_VALUE) {
                    dp[i][t] = Math.max(dp[i][t], dp[i][t - ribbonLengths[i]] + 1);
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= total; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("");
        }        
        
        return (dp[n - 1][total] == Integer.MIN_VALUE) ? -1 : dp[n - 1][total];
    }

    public static void main(String[] args) {
        MaxRibbonBottomDown cr = new MaxRibbonBottomDown();
        int[] ribbonLengths = {2,3,5};
        //System.out.println(cr.countRibbonPieces(ribbonLengths, 5));
        //ribbonLengths = new int[]{2,3};
        //System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
        //ribbonLengths = new int[]{3,5,7};
        //System.out.println(cr.countRibbonPieces(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
      }
}