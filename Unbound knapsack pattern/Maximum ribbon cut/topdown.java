class MaxRibbonTopDown {
    public int countRibbonPieces(int[] ribbonLengths, int total) {
        Integer [][] dp = new Integer [ribbonLengths.length][total + 1];
        int result = this.countRibbonPiecesRecursive(dp, ribbonLengths, total, 0);
        return (result == Integer.MIN_VALUE) ? -1 : result;
    }

    public int countRibbonPiecesRecursive(Integer[][] dp, int[] ribbonLengths, int total, int currentIndex) {
        //base checks
        if (total == 0) {
            return 0;
        }

        if (currentIndex >= ribbonLengths.length || ribbonLengths.length == 0) {
            return Integer.MIN_VALUE;
        }

        if (dp[currentIndex][total] == null) {
            int count1 = Integer.MIN_VALUE;
            if (ribbonLengths[currentIndex] <= total) {
                int res = countRibbonPiecesRecursive(dp, ribbonLengths, (total - ribbonLengths[currentIndex]), currentIndex);
    
                if (res != Integer.MIN_VALUE) {
                    count1 = res + 1;
                }
            }
    
            int count2 = countRibbonPiecesRecursive(dp, ribbonLengths, total, currentIndex + 1);
            
            dp[currentIndex][total] = Math.max(count1, count2);
        }

        return dp[currentIndex][total];
    }

    public static void main(String[] args) {
        MaxRibbonTopDown cr = new MaxRibbonTopDown();
        int[] ribbonLengths = {2,3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 5));
        ribbonLengths = new int[]{2,3};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
        ribbonLengths = new int[]{3,5,7};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 13));
        ribbonLengths = new int[]{3,5};
        System.out.println(cr.countRibbonPieces(ribbonLengths, 7));
      }
}