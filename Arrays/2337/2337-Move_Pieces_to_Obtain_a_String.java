class Solution {
    public boolean canChange(String start, String target) {
        // Maybe I should compare each index to target

        int p1 = 0;
        int p2 = 0;

        char[] s = start.toCharArray();
        char[] t = target.toCharArray();

        while (p1 < start.length() || p2 < target.length()) {
            
            while (p1 < start.length() && s[p1] == '_') {
                p1++;
            }

            while (p2 < target.length() && t[p2] == '_') {
                p2++;
            }

            //Check if both
            if (p1 == start.length() && p2 == target.length()) {
                return true;
            }

            if (p1 == start.length() || p2 == target.length()) {
                return false;
            }

            // L and R cant cross each other
            if (s[p1] != t[p2]) {
                return false;
            }

            // This means they are the wsame
            if (s[p1] == 'L' && p1 < p2) {
                return false;
            }

            if (s[p1] == 'R' && p1 > p2) {
                return false;
            }

            p1++;
            p2++;
        }
        return (p1 == start.length() && p2 == target.length());
    }
}
