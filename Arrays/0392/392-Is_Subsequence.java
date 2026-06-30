class Solution {
    public boolean isSubsequence(String s, String t) {
        int p1 = 0;
        int p2 = 0;

        if (s.length() == 0) return true;

        if (t.length() == 0) return false;

        char[] ts = t.toCharArray();
        char[] ss = s.toCharArray();

        while (p1 < ss.length || p2 < ts.length) {

            while (p2 < ts.length && p1 < ss.length && ss[p1] != ts[p2]) {
                p2++;
            }

            if (p2 == ts.length) {
                return false;
            }

            else {
                p2++;
                p1++;
            }
        }

        return true;
    }
}
