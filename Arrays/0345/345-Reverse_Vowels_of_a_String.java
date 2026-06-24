class Solution {
    public String reverseVowels(String s) {
        //Set up array
        char[] cs = s.toCharArray();

        //Set up pointers
        int p1 = 0;
        int p2 = cs.length - 1;

        //Two pointer
        
        while (p1 < p2) {
            //Check if p1 points to vowel
            while (p1 < cs.length-1 && !isVowel(cs[p1])) {
                p1++;
            }

            while (p2 > 0 && !isVowel(cs[p2])) {
                p2--;
            }

            if (p1 < p2) {
                char temp = cs[p1];
                cs[p1] = cs[p2];
                cs[p2] = temp;
                p1++;
                p2--;
            }
        }

        return String.valueOf(cs);
    }

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c == 'e' || c =='i' || c == 'o' || c == 'u') {
            return true;
        }
        return false;
    }
}
