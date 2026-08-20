class Solution {
    public int mySqrt(int x) {
        long lo = 0;
        long mid;
        long hi = x;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;

            if (mid * mid < x) lo = mid + 1;
            else if (mid * mid > x) hi = mid - 1;
            else return (int) mid;

        }

        return (int)lo - 1;
    }
}
