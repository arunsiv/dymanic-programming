//O(2^N) time | O(N) space
//where ‘N’ is the number of items

const solveKnapsack = (profits, weights, capacity) => {
    return knapsackRecursive(profits, weights, capacity, 0);
};

const knapsackRecursive = (profits, weights, capacity, currentIndex) => {
    // base checks
    if (capacity <= 0 || currentIndex >= profits.length) return 0;

    // recursive call after choosing the element at the currentIndex
    // if the weight of the element at currentIndex exceeds the capacity, we shouldn't 
    // process this
    let profit1 = 0;
    if (weights[currentIndex] <= capacity) {
        profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
            capacity - weights[currentIndex], currentIndex + 1);
    }

    // recursive call after excluding the element at the currentIndex
    const profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

    return Math.max(profit1, profit2);
};

// var profits = [1, 6, 10, 16];
// var weights = [1, 2, 3, 5];

// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 7)}`);
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 6)}`);
// console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 0)}`);

var profits = [15, 50, 60, 90];
var weights = [1, 3, 4, 5];
console.log(`Total knapsack profit: ---> ${solveKnapsack(profits, weights, 25)}`);