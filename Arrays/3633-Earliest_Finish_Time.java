class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        //Pick the earliest start time?
        int minCombo = 10000000;
  
        for (int i = 0; i < landStartTime.length; i ++) {
            int finishTime = landStartTime[i] + landDuration[i]; //finishTime stores the finishing time

            for (int j = 0; j < waterStartTime.length; j ++) {
                int waitingTime = waterStartTime[j] - finishTime;
                waitingTime = waitingTime < 0 ? 0 : waitingTime;
                int totalTime = finishTime + waitingTime + waterDuration[j];
                minCombo = totalTime < minCombo ? totalTime : minCombo;
            }

        }

        for (int i = 0; i < waterStartTime.length; i ++) {
            int finishTime = waterStartTime[i] + waterDuration[i]; //finishTime stores the finishing time

            for (int j = 0; j < landStartTime.length; j ++) {
                int waitingTime = landStartTime[j] - finishTime;
                waitingTime = waitingTime < 0 ? 0 : waitingTime;
                int totalTime = finishTime + waitingTime + landDuration[j];
                minCombo = totalTime < minCombo ? totalTime : minCombo;
            }

        }

        return minCombo;
    }
}
