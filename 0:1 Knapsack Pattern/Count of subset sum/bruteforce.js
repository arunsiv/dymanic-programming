//O(2^n) time | O(n) space
//where n is the total number of elements

const countSubsets = (numbers, sum) => {
    const n = numbers.length;

    //base checks
    if (n <= 0) {
        return 0;
    }

    return countSubsetsRecursive(numbers, sum, 0);
};

const countSubsetsRecursive = (numbers, sum, currentIndex) => {
    //base checks
    if (sum === 0) {
        return 1;
    }

    if (currentIndex >= numbers.length) {
        return 0;
    }

    // recursive call after choosing the number at the currentIndex
    // if the number at currentIndex exceeds the sum, we shouldn't process this
    let sum1 = 0;
    if (numbers[currentIndex] <= sum) {
        sum1 = countSubsetsRecursive(numbers, sum - numbers[currentIndex], currentIndex + 1);
    }

    // recursive call after excluding the number at the currentIndex
    const sum2 = countSubsetsRecursive(numbers, sum, currentIndex + 1);

    return sum1 + sum2;
};

console.log(`Count of subset sum is: ---> ${countSubsets([1, 1, 2, 3], 4)}`);
console.log(`Count of subset sum is: ---> ${countSubsets([1, 2, 7, 1, 5], 9)}`);
console.log(`Count of subset sum is: ---> ${countSubsets([1, 2, 3], 100)}`);