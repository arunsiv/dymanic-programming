//O(2^n) time | O(n) space
//where n is the total number of elements

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

    return canPartitionRecursive(numbers, sum / 2, 0);
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

console.log(`Can partitioning be done: ---> ${canPartition([1, 2, 3, 4])}`);
console.log(`Can partitioning be done: ---> ${canPartition([1, 1, 3, 4, 7])}`);
console.log(`Can partitioning be done: ---> ${canPartition([2, 3, 4, 6])}`);