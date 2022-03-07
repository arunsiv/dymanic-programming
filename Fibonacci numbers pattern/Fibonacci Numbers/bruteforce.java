//O(2^n) time (we are making two recursive calls in the same function) | O(n) space
class FibonacciBruteForce {
    public int CalculateFibonacci(int num) {
        if (num < 2) {
            return num;
        }

        return CalculateFibonacci(num - 1) + CalculateFibonacci(num - 2);
    }

    public static void main(String[] args) {
        FibonacciBruteForce fib = new FibonacciBruteForce();
        System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacci(5));
        System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacci(6));
        System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacci(7));
    }
}