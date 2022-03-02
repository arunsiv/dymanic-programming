//O(C*T)) time | O(C*T) space
//where ‘C’ represents total coin denominations and ‘T’ is the total amount that we want to make change
    
const countChange = (denominations, total) => {
    const dp = [];
    return countChangeRecursive(dp, denominations, total, 0);
}

const countChangeRecursive = (dp, denominations, total, currentIndex) => {
    //base checks
    if (total === 0) {
        return 1;
    }
    
    if (denominations.length === 0 || currentIndex >= denominations.length) {
        return 0;
    }

    dp[currentIndex] = dp[currentIndex] || [];

    if (typeof dp[currentIndex][total] === 'undefined') {
        let sum1 = 0;
        if (denominations[currentIndex] <= total) {
            sum1 = countChangeRecursive(dp, denominations, total - denominations[currentIndex], currentIndex);
        }

        const sum2 = countChangeRecursive(dp, denominations, total, currentIndex + 1);

        dp[currentIndex][total] = sum1 + sum2;
    }

    return dp[currentIndex][total];
}

//console.log(`Number of ways to make change: ---> ${countChange([1, 2, 3], 5)}`);
console.log(`Number of ways to make change: ---> ${countChange([1, 2, 3], 1000)}`);