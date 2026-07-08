class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list = new ArrayList<>();

        //Perform addition
        int p = num.length - 1;
        int carry = 0;

        while (p >= 0) {
            int curr = k % 10;
            k = k / 10;
            int sum = num[p] + curr + carry;

            if (sum > 9) {
                list.add(sum % 10);
                carry = sum / 10;
                p--;
            }
            else {
                list.add(sum);
                carry = 0;
                p--;
            }
        }
        
        //Left over k and left over carry
        while (k > 0 || carry > 0) {
            int currK = k % 10;
            k /= 10;

            int sum = currK + carry;
            list.add(sum % 10);

            carry = sum / 10;
        }


        Collections.reverse(list);
        return list;
    }
}
