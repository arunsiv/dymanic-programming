class MaxRibbonBruteForce {
    public int countRibbonPieces(int[] ribbonLengths, int total) {
        int result = this.countRibbonPiecesRecursive(ribbonLengths, total, 0);
        return (result == Integer.MIN_VALUE) ? -1 : result;
    }

    public int countRibbonPiecesRecursive(int[] ribbonLengths, int total, int currentIndex) {
        //base checks
        if (total == 0) {
            return 0;
        }

        if (currentIndex >= ribbonLengths.length || ribbonLengths.length == 0) {
            return Integer.MIN_VALUE;
        }

        int count1 = Integer.MIN_VALUE;
        if (ribbonLengths[currentIndex] <= total) {
            int res = countRibbonPiecesRecursive(ribbonLengths, (total - ribbonLengths[currentIndex]), currentIndex);

            if (res != Integer.MIN_VALUE) {
                count1 = res + 1;
            }
        }

        int count2 = countRibbonPiecesRecursive(ribbonLengths, total, currentIndex + 1);
        
        return Math.max(count1, count2);
    }

    public static void main(String[] args) {
        MaxRibbonBruteForce cr = new MaxRibbonBruteForce();
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