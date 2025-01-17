//O(2^N+C) time | O(N + C) space
//where ‘N’ is the number of items and 'C' is capacity
const solveKnapsack = (profits, weights, capacity) => {
    //base checks
    if (capacity <= 0 || profits.length === 0 || profits.length !== weights.length) {
        return 0;
    }

    return solveKnapsackRecursive(profits, weights, capacity, 0);
};

const solveKnapsackRecursive = (profits, weights, capacity, currentIndex) => {

    if (currentIndex >= weights.length) {
        return 0;
    }

    let profit1 = 0;

    // recursive call after choosing the items at the currentIndex, note that we 
    // recursive call on all items as we did not increment currentIndex
    if (weights[currentIndex] <= capacity) {
        profit1 = profits[currentIndex] + solveKnapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex);
    }
    
    // recursive call after excluding the element at the currentIndex
    const profit2 = solveKnapsackRecursive(profits, weights, capacity, currentIndex + 1);

    return Math.max(profit1, profit2);
};

var profits = [15, 50, 60, 90];
var weights = [1, 3, 4, 5];
console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 25)}`);

// var profits = [1, 6, 10, 16];
// var weights = [1, 2, 3, 5];
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 7)}`);
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);