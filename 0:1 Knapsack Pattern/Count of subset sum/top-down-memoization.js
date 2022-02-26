//O(N*S) time | O(N*S) space
//where N is the total number of elements and S is the total sum of all numbers

const countSubsets = (numbers, sum) => {
    const n = numbers.length;

    //base checks
    if (n <= 0) {
        return 0;
    }

    const dp = [];

    return countSubsetsRecursive(dp, numbers, sum, 0);
};

const countSubsetsRecursive = (dp, numbers, sum, currentIndex) => {
    //base checks
    if (sum === 0) {
        return 1;
    }

    if (currentIndex >= numbers.length) {
        return 0;
    }

    dp[currentIndex] = dp[currentIndex] || [];

    // if we have not already processed a similar problem
    if (typeof dp[currentIndex][sum] === 'undefined') {
        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        let sum1 = 0;
        if (numbers[currentIndex] <= sum) {
            sum1 = countSubsetsRecursive(dp, numbers, sum - numbers[currentIndex], currentIndex + 1);
        }

        // recursive call after excluding the number at the currentIndex
        const sum2 = countSubsetsRecursive(dp, numbers, sum, currentIndex + 1);

        dp[currentIndex][sum] = sum1 + sum2;
    }

    return dp[currentIndex][sum];
};

console.log(`Count of subset sum is: ---> ${countSubsets([1, 1, 2, 3], 4)}`);
console.log(`Count of subset sum is: ---> ${countSubsets([1, 2, 7, 1, 5], 9)}`);
console.log(`Count of subset sum is: ---> ${countSubsets([1, 2, 3], 100)}`);