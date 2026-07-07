class Solution {
    public String addBinary(String a, String b) {
        int p1 = a.length() - 1;
        int p2 = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carryBit = 0;

        while (p1 >= 0 || p2 >= 0) {

            int bit1 = (p1 >= 0) ? (a.charAt(p1) - '0') : 0; // - '0' to convert to int
            int bit2 = (p2 >= 0) ? (b.charAt(p2) - '0') : 0;

            int sum = bit1 + bit2 + carryBit;
            sb.append(sum % 2);
            carryBit = sum / 2;

            p1--;
            p2--;
        }
        
        if (carryBit == 1) {sb.append(1);}

        return sb.reverse().toString();
    }
}
