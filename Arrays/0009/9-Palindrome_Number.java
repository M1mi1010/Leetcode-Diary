class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        String num = String.valueOf(x);

        int p1 = 0;
        int p2 = num.length() - 1;

        while (p1 < p2) {
            if (num.charAt(p1) != num.charAt(p2)) return false;
            p1++;
            p2--;
        }

        return true;
    }

}
