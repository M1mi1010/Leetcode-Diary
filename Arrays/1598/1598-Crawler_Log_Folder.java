class Solution {
    public int minOperations(String[] logs) {
        int noSteps = 0;
        int p = logs.length - 1;
        int skips = 0;

        while (p >= 0) {
            if (logs[p].equals("./")) {p--;}
            else if (logs[p].equals("../")) {
                skips++;
                p--;
            }
            else if (skips > 0) { //regular character but skip loading
                p--;
                skips--;
            }
            else { 
                noSteps++;
                p--;
            }
        }
        return noSteps;
    }
}
