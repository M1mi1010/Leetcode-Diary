class Solution {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        String[] brokenPath = path.split("/");
        int p1 = brokenPath.length - 1;
        int skips = 0;


        while (p1 >= 0) {
            if (brokenPath[p1].equals("") || brokenPath[p1].equals(".")) {p1--; continue;}
            if (brokenPath[p1].equals("..")) {p1--; skips++; continue;}

            if (skips > 0) {
                p1--;
                skips--;
            }
            else {
                sb.insert(0, '/' + brokenPath[p1]);
                p1--;
            }
        }

        return sb.toString().equals("") ? "/" : sb.toString();
    }
}
