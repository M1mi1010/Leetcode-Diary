class Solution {
    public List<String> commonChars(String[] words) {
        //Create a map of all the keys and occurences in word 1
        Map<String, Integer> chars = new HashMap<>();
        List<String> l = new ArrayList<>();

        for (char c : words[0].toCharArray()) {
            String s = String.valueOf(c);
            chars.put(s, chars.getOrDefault(s, 0) + 1);
        }

        for (int i = 1; i < words.length; i++) {

            Map<String, Integer> curr = new HashMap<>();

            for (char c : words[i].toCharArray()) {
                String s = String.valueOf(c);
                curr.put(s, curr.getOrDefault(s, 0) + 1);
            }
            
            for (String key : chars.keySet()) {
                if (curr.containsKey(key)) {
                    chars.put(key, Math.min(chars.get(key), curr.get(key)));
                } else {
                    chars.put(key, 0);
                }
            }
        }

        for (String key: chars.keySet()) {
            for (int j = 0; j < chars.get(key); j++) {
                l.add(key);
            }
        }

        return l;
    }
}
