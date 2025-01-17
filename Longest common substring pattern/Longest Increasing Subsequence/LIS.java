import java.util.ArrayList;
import java.util.List;

public class LIS {
    // O(2^n) time | O(n) space
    // where n is the length of the input array
    public int findLISLengthBruteForce(int[] nums) {
        return findLISLengthBruteForce(nums, 0, -1);
    }

    public int findLISLengthBruteForce(int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        // include the number at the currentIndex if it is greater than the number at
        // the previousIndex
        int c1 = 0;
        if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
            c1 = 1 + findLISLengthBruteForce(nums, currentIndex + 1, currentIndex);
        }

        // excluding the number at currentIndex
        int c2 = findLISLengthBruteForce(nums, currentIndex + 1, prevIndex);

        return Math.max(c1, c2);
    }

    // O(n^2) time | O(n^2) space
    // where n is the length of the input array
    // time: n * n subproblems. so, O(n^2)
    // space: O(n^2) for memoization array & O(n) for recursive stack
    // so O(n^2 + n), which is asymptotically equivalent to O(n^2)
    public int findLISLengthTopDown(int[] nums) {
        int[][] dp = new int[nums.length][nums.length + 1];
        return findLISLengthTopDown(dp, nums, 0, -1);
    }

    public int findLISLengthTopDown(int[][] dp, int[] nums, int currentIndex, int prevIndex) {
        if (currentIndex == nums.length) {
            return 0;
        }

        if (dp[currentIndex][prevIndex + 1] == 0) {
            // include the number at the currentIndex if it is greater than the number at
            // the previousIndex
            int c1 = 0;
            if (prevIndex == -1 || nums[currentIndex] > nums[prevIndex]) {
                c1 = 1 + findLISLengthTopDown(dp, nums, currentIndex + 1, currentIndex);
            }

            // excluding the number at currentIndex
            int c2 = findLISLengthTopDown(dp, nums, currentIndex + 1, prevIndex);

            dp[currentIndex][prevIndex + 1] = Math.max(c1, c2);
        }

        // for (int startIndex = 0; startIndex < nums.length; startIndex++) {
        // for (int endIndex = 0; endIndex < nums.length; endIndex++) {
        // System.out.print(dp[startIndex][endIndex] + "\t");
        // }
        // System.out.println("");
        // }

        return dp[currentIndex][prevIndex + 1];
    }

    // O(n^2) time | O(n) space
    // where n is the length of the input array
    public int findLISLengthBottomUp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;

        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[i] <= dp[j]) {
                    dp[i] = 1 + dp[j];
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        // for (int i : dp) {
        //     System.out.print(i + "\t");
        // }

        // System.out.println();

        return maxLength;
    }

    // Iterative function to find the longest increasing subsequence
    // of a given array
    public static void printLIS(int[] arr)
    {
        // base case
        if (arr == null || arr.length == 0) {
            return;
        }
    
        // LIS[i] stores the longest increasing subsequence of subarray
        // `arr[0…i]` that ends with `arr[i]`
        List<List<Integer>> LIS = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            LIS.add(new ArrayList<>());
        }
    
        // LIS[0] denotes the longest increasing subsequence ending at `arr[0]`
        LIS.get(0).add(arr[0]);
    
        // start from the second array element
        for (int i = 1; i < arr.length; i++)
        {
            // do for each element in subarray `arr[0…i-1]`
            for (int j = 0; j < i; j++)
            {
                // find the longest increasing subsequence that ends with `arr[j]`
                // where `arr[j]` is less than the current element `arr[i]`
                if (arr[j] < arr[i] && LIS.get(j).size() > LIS.get(i).size()) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                }
            }
    
            // include `arr[i]` in `LIS[i]`
            LIS.get(i).add(arr[i]);
        }
    
        // uncomment the following code to print contents of `LIS`
        // for (int i = 0; i < arr.length; i++) {
        //     System.out.println("LIS[" + i + "] — " + LIS.get(i));
        // }
    
        // `j` will store an index of LIS
        int j = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (LIS.get(j).size() < LIS.get(i).size()) {
                j = i;
            }
        }
    
        // print LIS
        System.out.print(LIS.get(j));
    }
    
    public static void main(String[] args) {
        LIS lis = new LIS();
        int[] nums = { 4, 2, 3, 6, 10, 1, 12 };

        // System.out.println(lis.findLISLengthBruteForce(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthBruteForce(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthBruteForce(nums));

        // System.out.println(lis.findLISLengthTopDown(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthTopDown(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthTopDown(nums));

        // System.out.println(lis.findLISLengthBottomUp(nums));
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthBottomUp(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthBottomUp(nums));
        // nums = new int[] {};
        // System.out.println(lis.findLISLengthBottomUp(nums));

        lis.printLIS(nums);
        // nums = new int[] { -4, 10, 3, 7, 15 };
        // System.out.println(lis.findLISLengthBottomUp(nums));
        // nums = new int[] { 1, -1 };
        // System.out.println(lis.findLISLengthBottomUp(nums));
        // nums = new int[] {};
        // System.out.println(lis.findLISLengthBottomUp(nums));
    }
}