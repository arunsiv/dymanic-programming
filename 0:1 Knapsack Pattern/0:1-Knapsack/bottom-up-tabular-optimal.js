//O(N*C) time | O(C) space
//where ‘N’ is the number of items and ‘C’ is the knapsack capacity

const solveKnapsack = (profits, weights, capacity) => {
    const n = profits.length;

    // base checks
    if (n <= 0 || capacity <= 0 || weights.length != n) {
        return 0;
    }

    // we only need one previous row to find the optimal solution, overall we need '2' rows
    // This solution is similar to the bottom-up solution, the only difference is that 
    // we use `i%2` instead if `i` and `(i-1)%2` instead if `i-1`

    //construct a 2 dimensional array and fill it with '0'
    const dp = Array(2).fill(0).map(() => Array(capacity + 1).fill(0));

    //if we have only one weight, we will take it if it is not more than the capacity
    for (let c = 0; c < capacity + 1; c++) {
        if (weights[0] <= c) {
            //dp[0][c] = dp[1][c] = profits[0];
            dp[0][c] = profits[0];
        }
    }

    //process all sub-arrays for all the capacities
    for (let i = 1; i < n; i++) {
        for (let c = 1; c < capacity + 1; c++) {
            let profit1 = 0, profit2 = 0;

            //include the item, if it is not more than the capacity
            if (weights[i] <= c) {
                profit1 = profits[i] + dp[(i - 1) % 2][c - weights[i]];
            }

            //exclude the item
            profit2 = dp[(i - 1) % 2][c];

            //take the maximum profit and add it to the array
            dp[i % 2][c] = Math.max(profit1, profit2);
        }
    }

    // maximum profit will be at the bottom-right corner.
    console.log(dp);
    return dp[(n - 1) % 2][capacity];
};

const solveKnapsackWithSingularArray = (profits, weights, capacity) => {
    const n = profits.length;

    // base checks
    if (n <= 0 || capacity <= 0 || weights.length != n) {
        return 0;
    }

    //construct a 2 dimensional array and fill it with '0'
    const dp = Array(capacity + 1).fill(0);

    // if we have only one weight, we will take it if it is not more than the capacity
    for (let c = 0; c <= capacity; c++) {
        if (weights[0] <= c) {
            dp[c] = profits[0];
        }
    }

    //process all sub-arrays for all the capacities
    for (let i = 1; i < n; i++) {
        for (let c = capacity; c >= 0; c--) {
            let profit1 = 0, profit2 = 0;

            //include the item, if it is not more than the capacity
            if (weights[i] <= c) {
                profit1 = profits[i] + dp[c - weights[i]];
            }

            //exclude the item
            profit2 = dp[c];

            //take the maximum profit and add it to the array
            dp[c] = Math.max(profit1, profit2);
        }
    }

    console.log(dp);

    // maximum profit will be at the bottom-right corner.
    return dp[capacity];
};

var profits = [1, 6, 10, 16];
var weights = [1, 2, 3, 5];

//console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 7)}`);
//console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);

console.log(`Total knapsack profit: ---> ${solveKnapsackWithSingularArray(profits, weights, 7)}`);
console.log(`Total knapsack profit: ---> ${solveKnapsackWithSingularArray(profits, weights, 6)}`);