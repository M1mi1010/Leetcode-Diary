class Solution {
    public int minSwaps(String s) {
        int balance = 0;
        int unmatched = 0;

        for (char c : s.toCharArray()) {
            if (c == '[') {
                balance++;
            } else {
                balance--;
            }

            if (balance < 0) {
                unmatched++;
                balance = 0;
            }
        }

        return (unmatched + 1) / 2;
    }
}
