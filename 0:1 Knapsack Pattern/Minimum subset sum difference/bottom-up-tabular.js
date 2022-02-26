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

    //get sum/2
    const requiredSum = Math.floor(sum / 2);

    const dp = Array(n).fill(false).map(() => Array(requiredSum).fill(false));

    //populate the sum=0 columns, as we can always form '0' sum with an empty set
    for (let i = 0; i < n; i++) {
        dp[i][0] = false;
    }

    //with only one number, we can form a subset only when the required sum is equal to that number
    for (let s = 1; s <= requiredSum; s++) {
        dp[0][s] = (numbers[0] === s);
    }

    //process all the subsets
    for (let i = 1; i < n; i++) {
        for (let s = 1; s <= requiredSum; s++) {
            // if we can get the sum 's' without the number at index 'i'
            if (dp[i - 1][s]) {
                dp[i][s] = dp[i -  1][s];
            } else if (s >= numbers[i]) {
                // else include the number and see if we can find a subset to get remaining sum
                dp[i][s] = dp[i - 1][s - numbers[i]];
            }
        }
    }

    let sum1 = 0;

    for (let i = requiredSum; i >= 0; i--) {
        if (dp[n - 1][i] === true) {
            sum1 = i;
            break;
        }
    }

    const sum2 = sum - sum1;

    //return the difference between both the sum
    return Math.abs(sum1 - sum2);
};

console.log(`Minimum subset difference is: ---> ${canPartition([1, 2, 3, 9])}`);
console.log(`Minimum subset difference is: ---> ${canPartition([1, 2, 7, 1, 5])}`);
console.log(`Minimum subset difference is: ---> ${canPartition([1, 3, 100, 4])}`);