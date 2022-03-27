public class LBS {
    // O(2^n) time | O(n) space
    // where n is the length of the input array
    public int findLBSLengthBruteForce(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int c1 = findLDSLengthBruteForce(nums, i, -1);
            int c2 = findLDSLengthReverseBruteForce(nums, i, -1);
            maxLength = Math.max(maxLength, (c1 + c2) - 1);
        }

        return maxLength;
    }

    // find LDS from currentIndex till the end of the array
    public int findLDSLengthBruteForce(int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        int c1 = 0;

        // include nums[currentIndex] if it is smaller than the previous number
        if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
            c1 = 1 + findLDSLengthBruteForce(nums, currentIndex + 1, currentIndex);
        }

        // excluding the number at currentIndex
        int c2 = findLDSLengthBruteForce(nums, currentIndex + 1, prevIndex);

        return Math.max(c1, c2);
    }

    // find LDS(reverse) from currentIndex till the begining of the array
    public int findLDSLengthReverseBruteForce(int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex < 0) {
            return 0;
        }

        int c1 = 0;

        // include nums[currentIndex] if it is smaller than the previous number
        if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
            c1 = 1 + findLDSLengthReverseBruteForce(nums, currentIndex - 1, currentIndex);
        }

        // excluding the number at currentIndex
        int c2 = findLDSLengthReverseBruteForce(nums, currentIndex - 1, prevIndex);

        return Math.max(c1, c2);
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the input array
    // time: n * n subproblems. so, O(n^2)
    // space: O(n^2) for memoization array & O(n) for recursive stack
    // so O(n^2 + n), which is asymptotically equivalent to O(n^2)
    public int findLBSLengthTopdown(int[] nums) {
        int[][] lds = new int[nums.length][nums.length + 1];
        int[][] ldsReverse = new int[nums.length][nums.length + 1];
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {
            int c1 = findLDSLengthTopDown(lds, nums, i, -1);
            int c2 = findLDSLengthReverseTopDown(ldsReverse, nums, i, -1);
            maxLength = Math.max(maxLength, (c1 + c2) - 1);
        }

        return maxLength;
    }

    // find LDS from currentIndex till the end of the array
    public int findLDSLengthTopDown(int[][] lds, int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        int c1 = 0;

        if (lds[currentIndex][prevIndex + 1] == 0) {
            // include nums[currentIndex] if it is smaller than the previous number
            if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
                c1 = 1 + findLDSLengthTopDown(lds, nums, currentIndex + 1, currentIndex);
            }

            // excluding the number at currentIndex
            int c2 = findLDSLengthTopDown(lds, nums, currentIndex + 1, prevIndex);

            lds[currentIndex][prevIndex + 1] = Math.max(c1, c2);
        }

        return lds[currentIndex][prevIndex + 1];
    }

    // find LDS(reverse) from currentIndex till the begining of the array
    public int findLDSLengthReverseTopDown(int[][] ldsReverse, int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex < 0) {
            return 0;
        }

        int c1 = 0;

        if (ldsReverse[currentIndex][prevIndex + 1] == 0) {
            // include nums[currentIndex] if it is smaller than the previous number
            if (prevIndex == -1 || nums[currentIndex] < nums[prevIndex]) {
                c1 = 1 + findLDSLengthReverseTopDown(ldsReverse, nums, currentIndex - 1, currentIndex);
            }

            // excluding the number at currentIndex
            int c2 = findLDSLengthReverseTopDown(ldsReverse, nums, currentIndex - 1, prevIndex);

            ldsReverse[currentIndex][prevIndex + 1] = Math.max(c1, c2);
        }

        return ldsReverse[currentIndex][prevIndex + 1];
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findLBSLengthBottomUp(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int[] lds = new int[n];
        int[] ldsReverse = new int[n];

        // find LDS for every index up to the beginning of the array
        for (int i = 0; i < n; i++) {
            lds[i] = 1; // every element has LDS of 1
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // find LDS for every index up to the end of the array
        for (int i = n - 1; i >= 0; i--) {
            ldsReverse[i] = 1; // every element has LDS of 1
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    ldsReverse[i] = Math.max(ldsReverse[i], ldsReverse[j] + 1);
                }
            }
        }

        for (int i : lds) {
            System.out.print(i);
        }
        System.out.println("");

        for (int i : ldsReverse) {
            System.out.print(i);
        }
        System.out.println("");

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, (lds[i] + ldsReverse[i]) - 1);
        }

        return maxLength;
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findLBSLengthBottomUpAlternateSolution(int[] nums) {
        int n = nums.length;

        if (n == 0) {
            return 0;
        }

        int[] lds = new int[n];
        int[] ldsReverse = new int[n];

        // find LIS for every index up to the beginning of the array
        lds[0] = 1;
        for (int i = 1; i < n; i++) {
            lds[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // find LDS in the reverse order of the array
        ldsReverse[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            ldsReverse[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    ldsReverse[i] = Math.max(ldsReverse[i], ldsReverse[j] + 1);
                }
            }
        }

        for (int i : lds) {
            System.out.print(i);
        }
        System.out.println("");

        for (int i : ldsReverse) {
            System.out.print(i);
        }
        System.out.println("");

        int maxLength = 0;
        for (int i = 0; i < n; i++) {
            maxLength = Math.max(maxLength, (lds[i] + ldsReverse[i]) - 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LBS lbs = new LBS();

        int[] nums = { 4, 2, 3, 6, 10, 1, 12 };
        // System.out.println(lbs.findLBSLengthBruteForce(nums));
        // nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        // System.out.println(lbs.findLBSLengthBruteForce(nums));

        // nums = new int[] { 4, 2, 3, 6, 10, 1, 12 };
        // System.out.println(lbs.findLBSLengthTopdown(nums));
        // nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        // System.out.println(lbs.findLBSLengthTopdown(nums));

        // nums = new int[] { 4, 2, 3, 6, 10, 1, 12 };
        // System.out.println(lbs.findLBSLengthBottomUp(nums));
        nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        System.out.println(lbs.findLBSLengthBottomUp(nums));

        // nums = new int[] { 4, 2, 3, 6, 10, 1, 12 };
        // System.out.println(lbs.findLBSLengthBottomUpAlternateSolution(nums));
        nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        System.out.println(lbs.findLBSLengthBottomUpAlternateSolution(nums));
    }
}