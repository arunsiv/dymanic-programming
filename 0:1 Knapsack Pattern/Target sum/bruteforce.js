//O(2^n) time | O(n) space
//where n is the total number of elements
const findTargetSubsets = (numbers, sum) => {
    const n = numbers.length;
    let totalSum = 0;

    numbers.forEach(number => {
        totalSum += number;
    });

    //base checks
    if (n <= 0) {
        return 0;
    }

    // if 's + totalSum' is odd, we can't find a subset with sum equal 
    // to '(s + totalSum) / 2'
    if (totalSum < sum || (sum + totalSum) % 2 === 1) {
        return 0;
    }

    return findTargetSubsetsRecursive(numbers, (sum + totalSum) / 2, 0);
};

const findTargetSubsetsRecursive = (numbers, sum, currentIndex) => {
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
        sum1 = findTargetSubsetsRecursive(numbers, sum - numbers[currentIndex], currentIndex + 1);
    }

    // recursive call after excluding the number at the currentIndex
    const sum2 = findTargetSubsetsRecursive(numbers, sum, currentIndex + 1);

    return sum1 + sum2;
};

console.log(`Count of Target sum is: ---> ${findTargetSubsets([1, 1, 2, 3], 1)}`);
console.log(`Count of Target sum is: ---> ${findTargetSubsets([1, 2, 7, 1], 9)}`);
console.log(`Count of Target sum is: ---> ${findTargetSubsets([50, 50, 25, 75, 100], 200)}`);