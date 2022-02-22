//O(N*C) time | O(N∗C) space
//where ‘N’ is the number of items and ‘C’ is the knapsack capacity

const solveKnapsack = (profits, weights, capacity) => {
    const n = profits.length;

    // base checks
    if (n <= 0 || capacity <= 0 || weights.length != n) {
        return 0;
    }

    //construct a 2 dimensional array and fill it with '0'
    const dp = Array(n).fill(0).map(() => Array(capacity + 1).fill(0));

    //populate the capacity=0 columns; with '0' capacity we have '0' profit
    //this is not neeeded since all the elements were filled with '0' already
    /*for (let i = 0; i < n; i++) {
        dp[i][0] = 0;
    }*/

    //if we have only one weight, we will take it if it is not more than the capacity
    for (let c = 0; c < capacity + 1; c++) {
        if (weights[0] <= c) {
            dp[0][c] = profits[0];
        }
    }

    //process all sub-arrays for all the capacities
    for (let i = 1; i < n; i++) {
        for (let c = 1; c < capacity + 1; c++) {
            let profit1 = 0, profit2 = 0;

            //include the item, if it is not more than the capacity
            if (weights[i] <= c) {
                profit1 = profits[i] + dp[i - 1][c - weights[i]];
            }

            //exclude the item
            profit2 = dp[i - 1][c];

            //take the maximum profit and add it to the array
            dp[i][c] = Math.max(profit1, profit2);
        }
    }

    //print selected elements
    printSelectedElements(dp, weights, profits, capacity);

    console.log(dp);
    // maximum profit will be at the bottom-right corner.
    return dp[n - 1][capacity];
};

const printSelectedElements = (dp, weights, profits, capacity) => {
    let selectedWeights = '';
    let totalProfit = dp[weights.length - 1][capacity];
    let remainingCapacity = capacity;

    for (let i = weights.length - 1; i > 0; i--) {
        if (totalProfit != dp[i - 1][remainingCapacity]) {
            selectedWeights = `${weights[i]} ${selectedWeights}`;
            remainingCapacity -= weights[i];
            totalProfit -= profits[i];
        }
    }

    if (totalProfit != 0) {
        selectedWeights = `${weights[0]} ${selectedWeights}`;
    }

    console.log(`Selected weights: ${selectedWeights}`);
};

var profits = [1, 6, 10, 16];
var weights = [1, 2, 3, 5];
console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 7)}`);
console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);