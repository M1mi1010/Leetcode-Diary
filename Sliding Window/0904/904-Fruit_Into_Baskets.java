class Solution {
    public int totalFruit(int[] fruits) {
        int max = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap();

        for (int right = 0; right < fruits.length; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);

            while (left < fruits.length && map.size() > 2) {
                int count = map.get(fruits[left]) - 1;

                if (count == 0) {
                    map.remove(fruits[left]);
                }
                else {
                    map.put(fruits[left], count);
                }

                left++;
            }
            
            max = right - left + 1> max ? right - left + 1 : max;
        }

        return max;
    }
}
