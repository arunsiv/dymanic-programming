//O(N*S) time | O(N*S) space
//where ‘N’ is the number of items
const solveRodCutting = (lengths, prices, rodlength) => {
    const n = lengths.length;

    //base checks
    if (rodlength <= 0 || n === 0 || n !== prices.length) {
        return 0;
    }

    const dp = Array(n).fill(0).map(() => Array(rodlength + 1).fill(0));

    for (let i = 0; i < n; i++) {
        dp[i][0] = 0;
    }

    for (let c = 1; c <= rodlength; c++) {
        dp[0][c] = (lengths[0] <= c) ? prices[0] * c : 0;
    }

    // process all sub-arrays for all capacities
    for (let i = 1; i < n; i++) {
        for (let c = 1; c <= rodlength; c++) {
            let profit1 = 0, profit2 = 0;

            if (lengths[i] <= c) {
                profit1 = prices[i] + dp[i][c - lengths[i]];
            }

            //if( i > 0 ) {
                profit2 = dp[i - 1][c];
            //}

            dp[i][c] = Math.max(profit1, profit2);
        }
    }

    //print selected elements
    printSelectedElements(dp, lengths, prices, rodlength);

    //console.log(dp);
    return dp[n - 1][rodlength];
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

    console.log(`Selected length: ${selectedWeights}`);
};

const lengths = [1, 2, 3, 4, 5];
const prices = [2, 6, 7, 10, 13];
console.log(`Maximum profit: ---> ${solveRodCutting(lengths, prices, 5)}`);