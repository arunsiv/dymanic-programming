import java.util.ArrayList;
import java.util.List;

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

    public static void printLBS(int[] nums) {
        int n = nums.length;

        // base case
        if (n == 0) {
            return;
        }

        // `I[i]` stores the longest increasing subsequence, ending at `nums[i]`
        List<List<Integer>> I = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            I.add(new ArrayList<>());
        }

        I.get(0).add(nums[0]);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (I.get(i).size() < I.get(j).size() && nums[i] > nums[j]) {
                    I.set(i, new ArrayList<>(I.get(j)));
                }
            }
            I.get(i).add(nums[i]);
        }

        // `D[i]` stores the longest decreasing subsequence, starting from `nums[i]`
        List<List<Integer>> D = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            D.add(new ArrayList<>());
        }
        D.get(n - 1).add(0, nums[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (D.get(i).size() < D.get(j).size() && nums[i] > nums[j]) {
                    D.set(i, new ArrayList<>(D.get(j)));
                }
            }
            D.get(i).add(0, nums[i]);
        }

        // find the peak element index
        int peak = 0;
        for (int i = 1; i < n; i++) {
            if ((I.get(i).size() + D.get(i).size()) > (I.get(peak).size() + D.get(peak).size())) {
                peak = i;
            }
        }

        System.out.print("The longest bitonic subsequence is ");

        // print longest increasing subsequence ending at peak element
        System.out.print(I.get(peak));

        // pop the front element of LDS as it points to the same element as the
        // rear of LIS
        D.get(peak).remove(0);

        // print longest decreasing subsequence starting from the peak element
        System.out.println(D.get(peak));
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
        // nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        // System.out.println(lbs.findLBSLengthBottomUp(nums));

        // nums = new int[] { 4, 2, 3, 6, 10, 1, 12 };
        // System.out.println(lbs.findLBSLengthBottomUpAlternateSolution(nums));
        // nums = new int[] { 4, 2, 5, 9, 7, 6, 10, 3, 1 };
        // System.out.println(lbs.findLBSLengthBottomUpAlternateSolution(nums));
        nums = new int[] { 1, 2, 3, 4, 3, 2, 1 };
        System.out.println(lbs.findLBSLengthBottomUpAlternateSolution(nums));

        LBS.printLBS(nums);
    }
}