class FibonacciBottomUp {
    //O(n) time | O(n) space
    public int CalculateFibonacci(int num) {
        if (num < 2) {
            return num;
        }

        int [] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[num];
    }

    //O(n) time | O(1) space
    public int CalculateFibonacciOptimalSpace(int num) {
        if (num < 2) {
            return num;
        }

        int n1 = 0, n2 = 1, temp;
        for (int i = 2; i <= num; i++) {
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
        }

        return n2;
    }

    public static void main(String[] args) {
        FibonacciBottomUp fib = new FibonacciBottomUp();
        // System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacci(5));
        // System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacci(6));
        // System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacci(7));

        System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacciOptimalSpace(5));
        System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacciOptimalSpace(6));
        System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacciOptimalSpace(70));
    }
}