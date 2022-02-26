//O(N*S) time | O(N∗S) space
//where N is the total number of elements and S is the total sum of all numbers
const canPartition = (numbers, sum) => {
    const n = numbers.length;

    // base checks
    if (n <= 0) {
        return 0;
    }

    const dp = Array(n).fill(false).map(() => Array(sum + 1).fill(false));

    //populate the sum=0 columns, as we can always get '0' sum with an empty set
    for (let i = 0; i < n; i++) {
        dp[i][0] = true;
    }

    // with only one number, we can form a subset only when the required sum is equal to its value
    for (let s = 1; s <= sum; s++) {
        dp[0][s] = numbers[0] === s;
    }

    // process all subsets for all sums
    for (let i = 1; i < n; i++) {
        for (let s = 1; s <= sum; s++) {
            // if we can get the sum 's' without the number at index 'i'
            if (dp[i - 1][s]) {
                dp[i][s] = dp[i - 1][s];
            } else if (s >= numbers[i]) {
                // else include the number to see if we can find a subset to get remaining sum
                dp[i][s] = dp[i - 1][s - numbers[i]];
            }
        }
    }

    return dp[n - 1][sum];
};

//O(N*S) time | O(S) space
//where N is the total number of elements and S is the total sum of all numbers
const canPartitionOptimalSpace = (numbers, sum) => {
    const n = numbers.length;

    // base checks
    if (n <= 0) {
        return 0;
    }

    const dp = Array(sum + 1).fill(false);

    // handle sum=0, as we can always have '0' sum with an empty set
    dp[0] = true;

    // with only one number, we can form a subset only when the required sum is equal to its value
    for (let s = 1; s < sum; s++) {
        dp[s] = numbers[0] === s;
    }

    // process all subsets for all sums
    for (let i = 1; i < n; i++) {
        for (let s = sum; s >= 0; s--) {
            // if dp[s]==true, this means we can get the sum 's' without num[i], hence we can 
            // move on to the next number else we can include num[i] and see if we can find a 
            // subset to get the remaining sum
            if (!dp[s] && s >= numbers[i]) {
                dp[s] = dp[s - numbers[i]];
            }
        }
    }

    console.log(dp);

    return dp[sum];
};

//console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 3, 4], 6)}`);
//console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 7, 1, 5], 10)}`);
//console.log(`Can partitioning be done: ---> ${canPartition([1, 3, 4, 8], 6)}`);

console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([1, 2, 3, 4], 6)}`);
console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([1, 2, 7, 1, 5], 10)}`);
console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([1, 3, 4, 8], 6)}`);