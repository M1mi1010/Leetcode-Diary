class Solution {
    public int compress(char[] chars) {
        
        int reader = 0;
        int writeTo = 0;

        if (chars.length == 0) return 0;

        while (reader < chars.length) {
            int currCount = 1;
            while (reader + 1 < chars.length && chars[reader] == chars[reader + 1]) {
                reader++;
                currCount++;
            }

            chars[writeTo++] = chars[reader];

            if (currCount > 1) { 
                for (char c : String.valueOf(currCount).toCharArray()) {
                    chars[writeTo++] = c;    
                }
            }
            reader++;
        }
        return writeTo;
    }
}
