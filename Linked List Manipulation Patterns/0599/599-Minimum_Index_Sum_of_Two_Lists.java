class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        int min = 2001;

        for (int i = 0; i < list1.length; i++) {
            map.putIfAbsent(list1[i], i);
        }
        
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                int index = map.get(list2[i]);
                if (min == index + i) {
                    list.add(list2[i]);
                }
                else if (min > index + i) {
                    list.clear();
                    list.add(list2[i]);
                    min = index + i;
                }
            }
        }

        String[] s = new String[list.size()];
        list.toArray(s);

        return s;
    }
}
