class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0) {
            return list;
        }

        if (numRows == 1) {
            list.add(new ArrayList<>(Arrays.asList(1)));
            return list;
        }

        list.add(new ArrayList<>(Arrays.asList(1)));
        list.add(new ArrayList<>(Arrays.asList(1,1)));

        for (int i = 1; i < numRows - 1; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 0; j < i; j++) {
                // Get each item from in the previous 
                cur.add(list.get(i).get(j) + list.get(i).get(j+1));
            }
            cur.add(1);
            list.add(cur);
        }
        return list;
    }
}
