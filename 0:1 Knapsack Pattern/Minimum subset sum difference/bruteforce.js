//O(2^n) time | O(n) space
//where n is the total number of elements

const canPartition = (num) => {
    return canPartitionRecursive(num, 0, 0, 0);
};

const canPartitionRecursive = (num, currentIndex, sum1, sum2) => {
    //base checks
    if (currentIndex === num.length) {
        return Math.abs(sum1 - sum2);
    }

    // recursive call after including the number at the currentIndex in the first set
    const diff1 = canPartitionRecursive(num, currentIndex + 1, sum1 + num[currentIndex], sum2);

    // recursive call after including the number at the currentIndex in the second set
    const diff2 = canPartitionRecursive(num, currentIndex + 1, sum1, sum2 + num[currentIndex]);

    //console.log(`${diff1} | ${diff2} | ${sum1} | ${sum2}`);

    return Math.min(diff1, diff2);
};

console.log(`Minimum subset difference is: ---> ${canPartition([1, 2, 3, 9])}`);
console.log(`Minimum subset difference is: ---> ${canPartition([1, 2, 7, 1, 5])}`);
console.log(`Minimum subset difference is: ---> ${canPartition([1, 3, 100, 4])}`);