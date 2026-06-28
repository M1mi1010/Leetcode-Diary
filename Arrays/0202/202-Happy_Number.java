class Solution {
    public boolean isHappy(int n) {

        int slow = n;
        int fast = n;
        boolean found = false;

        if (n == 1) return true;

        while (!found) {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(fast);
            fast = sumOfSquares(fast);

            if (fast == 1 || slow == 1) {
                found = true;
                return true;
            }

            if (slow == fast) {
                return false;
            }
        }
        return false;
    }

    private int sumOfSquares(int num) {
        int sum = 0;
        while (num > 0) {
            sum += Math.pow((num % 10), 2);
            num /= 10;
        }

        return sum;
    }
}
