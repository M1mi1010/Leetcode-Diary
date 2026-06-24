class Solution {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int start = 0;

        while (start < cs.length - 1) {
            int p = start;
            int p2 = Math.min(start + k - 1, cs.length - 1); //Special case handling
            while (p < p2) { 
                //Reverse
                char temp = cs[p];
                cs[p] = cs[p2];
                cs[p2] = temp;
                p++;
                p2--;
            }
            start += ( 2 * k );
        }
        
        return String.valueOf(cs);

    }
}
