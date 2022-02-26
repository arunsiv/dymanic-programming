//O(N*S) time | O(N∗S) space
//where N is the total number of elements and S is the total sum of all numbers
const canPartition = (numbers) => {
    const n = numbers.length;
    let sum = 0;

    // base checks
    if (n <= 0) {
        return 0;
    }

    //get the sum of all the numbers
    numbers.forEach(number => {
        sum += number;
    });

    //If the sum is an odd number, it can't be partionined into equal subsets
    if (sum % 2 !== 0) {
        return false;
    }

    // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
    sum /= 2;

    const dp = Array(n).fill(false).map(() => Array((sum) + 1).fill(false));

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
                // else if we can find a subset to get the remaining sum
                dp[i][s] = dp[i - 1][s - numbers[i]];
            }
        }
    }

    //console.log(dp);

    return dp[n - 1][sum];
};

//O(N*S) time | O(S) space
//where N is the total number of elements and S is the total sum of all numbers
const canPartitionOptimalSpace = (numbers) => {
    const n = numbers.length;
    let sum = 0;

    // base checks
    if (n <= 0) {
        return 0;
    }

    //get the sum of all the numbers
    numbers.forEach(number => {
        sum += number;
    });

    //If the sum is an odd number, it can't be partionined into equal subsets
    if (sum % 2 !== 0) {
        return false;
    }

    // we are trying to find a subset of given numbers that has a total sum of ‘sum/2’.
    sum /= 2;

    const dp = Array(sum + 1).fill(false);

    // handle sum=0, as we can always have '0' sum with an empty set
    dp[0] = true;

    // with only one number, we can form a subset only when the required sum is equal to its value
    for (let s = 1; s <= sum; s++) {
        dp[s] = numbers[0] === s;
    }

    // process all subsets for all sums
    for (let i = 1; i < n; i++) {
        for (let s = sum; s >= 0; s--) {
            // if dp[s]==true, this means we can get the sum 's' without num[i], hence we can 
            // move on to the next number else we can include num[i] and see if we can find a 
            // subset to get the remaining sum
            if (!dp[s] && s > numbers[i]) {
                dp[s] = dp[s - numbers[i]];
            }
        }
    }

    console.log(dp);

    return dp[sum];
};

//console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 3, 4])}`);
//console.log(`Can partitioning be done: ---> ${canPartition([1, 1, 3, 4, 7])}`);
//console.log(`Can partitioning be done: ---> ${canPartition([2, 3, 4, 6])}`);

console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([1, 2, 3, 4])}`);
console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([1, 1, 3, 4, 7])}`);
console.log(`Can partitioning be done: ---> ${canPartitionOptimalSpace([2, 3, 4, 6])}`);