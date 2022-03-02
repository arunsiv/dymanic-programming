//O(C*T)) time | O(C*T) space
//where ‘C’ represents total coin denominations and ‘T’ is the total amount that we want to make change

const countChange = (denominations, total) => {
    //base checks
    if (total === 0) {
        return 1;
    }
    
    const n = denominations.length;
    if (n === 0) {
        return 0;
    }

    const dp = Array(n).fill(0).map(() => Array(total + 1).fill(0)); 

    // populate the total=0 columns, as we will always have an empty set for zero total
    for (let i = 0; i < n; i++) {
        dp[i][0] = 1; 
    }

    for (let t = 0; t <= total; t++) {
        dp[0][t] = denominations[0] <= total ? denominations[0] : 0; 
    }

    for (let i = 1; i < n; i++) {
        for (let t = 1; t <= total; t++) {
            let sum1 = 0, sum2 = 0;

            //include the denomination
            if (t >= denominations[i]) {
                sum1 = dp[i][t - denominations[i]]; 
            }

            //exclude the denomination
            //if (i > 0) {
                sum2 = dp[i][t] = dp[i - 1][t];
            //}

            dp[i][t] = sum1 + sum2;
        }
    }

    console.log(dp);
    return dp[n - 1][total];
}

//console.log(`Number of ways to make change: ---> ${countChange([1, 2, 3], 5)}`);
console.log(`Number of ways to make change: ---> ${countChange([1, 2, 3], 10)}`);