class Solution {
    public List<Integer> getRow(int rowIndex) {
        // Create a list
        List<List<Integer>> list = new ArrayList<>();

        if (rowIndex == 0) {
            return new ArrayList<>(Arrays.asList(1));
        }
        
        if (rowIndex == 1) {
            return new ArrayList<>(Arrays.asList(1,1));
        }

        list.add(new ArrayList<>(Arrays.asList(1)));
        list.add(new ArrayList<>(Arrays.asList(1, 1)));

        for (int i = 1; i < rowIndex; i++ ) {
            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 0; j < i; j ++) {
                cur.add(list.get(i).get(j) + list.get(i).get(j+1));
            }
            cur.add(1);
            list.add(cur);
        }
        return list.get(rowIndex);
    }
}
