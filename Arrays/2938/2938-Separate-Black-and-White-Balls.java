class Solution {
    public long minimumSteps(String s) {
        //Method: use two pointer approach and count how many swaps

        //Use p2 to find a 0 then p1 to find the 1s before it

        char[] chars = s.toCharArray();
        long count = 0;
        long onesSeen = 0;

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] != '0') {
                onesSeen++;
                continue;
            }

            count += onesSeen;

        }

        return count;
    }
}
