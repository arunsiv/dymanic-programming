//O(N*S) time | O(N∗S) space
//where N is the total number of elements and S is the total sum of all numbers
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

    return countSubsets(numbers, (sum + totalSum) / 2);
};

const countSubsets = (numbers, sum) => {
    const n = numbers.length;

    // base checks
    if (n <= 0) {
        return 0;
    }

    const dp = Array(n).fill(0).map(() => Array(sum + 1).fill(0));

    for (let i = 0; i < n; i++) {
        dp[i][0] = 1;
    }

    for (let s = 1; s <= sum; s++) {
        dp[0][s] = (numbers[0] === s) ? 1 : 0;
    }

    for (let i = 1; i < n; i++) {
        for (let s = 1; s <= sum; s++) {
            //exclude the number
            dp[i][s] = dp[i - 1][s];

            //include the number if it does not exceed the sum
            if (s >= numbers[i]) {
                dp[i][s] += dp[i - 1][s - numbers[i]];
            }
        }
    }

    //console.log(dp);

    return dp[n - 1][sum];
};

//O(N*S) time | O(S) space
//where N is the total number of elements and S is the total sum of all numbers
const findTargetSubsetsSpaceOptimal = (numbers, sum) => {
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

    return countSubsetsSpaceOptimal(numbers, (sum + totalSum) / 2);
};

const countSubsetsSpaceOptimal = (numbers, sum) => {
    const n = numbers.length;

    // base checks
    if (n <= 0) {
        return 0;
    }

    const dp = Array(n).fill(0);
    dp[0] = 1;

    for (let s = 1; s <= sum; s++) {
        dp[s] = (numbers[0] === s) ? 1 : 0;
    }

    for (let i = 1; i < n; i++) {
        for (let s = sum; s >= 0; s--) {
            //console.log(`${i} : ${s}`);
            //console.log(`Before ${dp}`);

            if (s >= numbers[i]) {
                dp[s] += dp[s - numbers[i]];
            }

            //console.log(`After ${dp}`);
        }
    }

    //console.log(`----------`);
    //console.log(`Final: ${dp}`);

    return dp[sum];
};

// console.log(`Count of Target sum is: ---> ${findTargetSubsets([1, 1, 2, 3], 1)}`);
// console.log(`Count of Target sum is: ---> ${findTargetSubsets([1, 2, 7, 1], 9)}`);
// console.log(`Count of Target sum is: ---> ${findTargetSubsets([50, 50, 25, 75, 100], 200)}`);

console.log(`Count of Target sum is: ---> ${findTargetSubsetsSpaceOptimal([1, 1, 2, 3], 1)}`);
console.log(`Count of Target sum is: ---> ${findTargetSubsetsSpaceOptimal([1, 2, 7, 1], 9)}`);
console.log(`Count of Target sum is: ---> ${findTargetSubsetsSpaceOptimal([50, 50, 25, 75, 100], 200)}`);