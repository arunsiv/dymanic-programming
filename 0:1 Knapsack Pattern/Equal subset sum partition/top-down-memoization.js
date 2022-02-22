//O(N*S) time | O(N*S) space
//where N is the total number of elements and S is the total sum of all numbers

const canPartition = (numbers) => {
    const n = numbers.length;
    let sum = 0;

    //base checks
    if (n <= 0) {
        return false;
    }

    //get the sum of all the numbers
    numbers.forEach(number => {
        sum += number;
    });

    //If the sum is an odd number, it can't be partionined into equal subsets
    if (sum % 2 !== 0) {
        return false;
    }

    const dp = [];

    return canPartitionRecursive(dp, numbers, sum / 2, 0);
};

const canPartitionRecursive = (dp, numbers, sum, currentIndex) => {
    //base checks
    if (sum === 0) {
        return true;
    }

    if (currentIndex >= numbers.length) {
        return false;
    }

    dp[currentIndex] = dp[currentIndex] || [];

    //console.log(`Current Index is ${currentIndex} and sum is ${sum}`);

    // if we have not already processed a similar problem
    if (typeof dp[currentIndex][sum] === 'undefined') {
        // recursive call after choosing the number at the currentIndex
        // if the number at currentIndex exceeds the sum, we shouldn't process this
        if (numbers[currentIndex] <= sum) {
            if (canPartitionRecursive(dp, numbers, sum - numbers[currentIndex], currentIndex + 1)) {
                dp[currentIndex][sum] = true;
                return true;
            }
        }
    }

    // recursive call after excluding the number at the currentIndex
    dp[currentIndex][sum] = canPartitionRecursive(dp, numbers, sum, currentIndex + 1);

    //console.log(dp);

    return dp[currentIndex][sum];
};

console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 3, 4])}`);
console.log(`Can partitioning be done: ---> ${canPartition([1, 1, 3, 4, 7])}`);
console.log(`Can partitioning be done: ---> ${canPartition([2, 3, 4, 6])}`);