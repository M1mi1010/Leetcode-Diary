class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s: strs) {
           //Sort the string and then add its 
           char[] chars = s.toCharArray();
           Arrays.sort(chars);
           String cs = String.valueOf(chars);
           if (!map.containsKey(cs)) {
                map.put(cs, new ArrayList<>());
            }
            map.get(cs).add(s);
        
        }

        return new ArrayList<>(map.values());
    }
}
