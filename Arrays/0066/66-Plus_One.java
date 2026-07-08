class Solution {
    public int[] plusOne(int[] digits) {
        boolean carry = false;
        if (digits[digits.length - 1] != 9) {
            digits[digits.length - 1] +=1;
            return digits;
        }

        else {
            int p = digits.length - 1;
            while (p >= 0 ) {
                if (digits[p] == 9) {
                    digits[p] = 0;
                    carry = true;
                    p--;
                }
                else {
                    digits[p] += 1;
                    carry = false;
                    break;
                }
            }
        }

        if (carry) {
            int[] newDigits = new int[digits.length + 1];

            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            newDigits[0] = 1;
            return newDigits;
        }

        return digits;
    }
}
