const canPartition = (numbers, sum) => {
    const n = numbers.length;

    //base checks
    if (n <= 0) {
        return false;
    }

    return canPartitionRecursive(numbers, sum, 0);
};

const canPartitionRecursive = (numbers, sum, currentIndex) => {
    //base checks
    if (sum === 0) {
        return true;
    }

    if (currentIndex >= numbers.length) {
        return false;
    }

    // recursive call after choosing the number at the currentIndex
    // if the number at currentIndex exceeds the sum, we shouldn't process this
    if (numbers[currentIndex] <= sum) {
        if (canPartitionRecursive(numbers, sum - numbers[currentIndex], currentIndex + 1)) {
            return true;
        }
    }

    // recursive call after excluding the number at the currentIndex
    return canPartitionRecursive(numbers, sum, currentIndex + 1);
};

console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 3, 4], 6)}`);
console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 7, 1, 5], 10)}`);
console.log(`Can partitioning be done: ---> ${canPartition([1, 3, 4, 8], 6)}`);