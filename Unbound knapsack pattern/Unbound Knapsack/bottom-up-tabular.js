//O(N*S) time | O(N*S) space
//where ‘N’ is the number of items
const solveKnapsack = (profits, weights, capacity) => {
    const n = profits.length;

    //base checks
    if (capacity <= 0 || n === 0 || n !== weights.length) {
        return 0;
    }

    const dp = Array(n).fill(0).map(() => Array(capacity + 1).fill(0));

    for (let i = 0; i < n; i++) {
        dp[i][0] = 0;
    }

    for (let c = 1; c <= capacity; c++) {
        dp[0][c] = (weights[0] <= c) ? profits[0] * c : 0;
    }

    // process all sub-arrays for all capacities
    for (let i = 1; i < n; i++) {
        for (let c = 1; c <= capacity; c++) {
            let profit1 = 0, profit2 = 0;

            if (weights[i] <= c) {
                profit1 = profits[i] + dp[i][c - weights[i]];
            }

            //if( i > 0 ) {
                profit2 = dp[i - 1][c];
            //}

            dp[i][c] = Math.max(profit1, profit2);
        }
    }

    //print selected elements
    printSelectedElements(dp, weights, profits, capacity);

    //console.log(dp);
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

var profits = [15, 20, 50];
var weights = [1, 2, 3];
console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 5)}`);
//console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);

// var profits = [1, 6, 10, 16];
// var weights = [1, 2, 3, 5];
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 7)}`);
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);