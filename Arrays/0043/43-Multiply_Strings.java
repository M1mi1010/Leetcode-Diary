class Solution {
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] result = new int[num1.length() + num2.length()];

        // Multiply
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {

                int res = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                int pos2 = i + j + 1; // ones place
                int pos1 = i + j;     // carry place

                int sum = res + result[pos2];

                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        // Convert array to String
        StringBuilder sb = new StringBuilder();

        for (int digit : result) {
            // Skip leading zeros
            if (sb.length() == 0 && digit == 0) {
                continue;
            }
            sb.append(digit);
        }

        return sb.toString();
    }
}
