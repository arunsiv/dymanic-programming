//O(n) time | O(n) space
class FibonacciTopDown {
    public int CalculateFibonacci(int num) {
        if (num < 2) {
            return num;
        }

        int [] dp = new int[num + 1];

        return CalculateFibonacciRecursive(dp, num);
    }

    public int CalculateFibonacciRecursive(int [] dp, int num) {
        if (dp[num] == 0) {
            dp[num]  = CalculateFibonacci(num - 1) + CalculateFibonacci(num - 2);
        }

        return dp[num];
    }

    public static void main(String[] args) {
        FibonacciTopDown fib = new FibonacciTopDown();
        System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacci(5));
        System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacci(6));
        System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacci(7));
    }
}