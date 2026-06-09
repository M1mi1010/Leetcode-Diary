class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        List<Integer> intersection = new ArrayList<>();

        for (int num : nums1) {
            if (map1.containsKey(num)) {
                map1.put(num, map1.get(num)+1);
            }

            map1.putIfAbsent(num, 1);
        }

        for (int num: nums2) {
            if (map1.containsKey(num) && (map1.get(num) != 0)) {
                intersection.add(num);
                map1.put(num, map1.get(num) - 1);
            }
        }
        
        int[] arr = new int[intersection.size()];
        int i = 0;

        for (int num: intersection) {
            arr[i] = num;
            i++;
        }

        return arr;
    }
}
