class ProductOfNumbers {

    private List<Integer> running;

    public ProductOfNumbers() {
        running = new ArrayList<>();
        running.add(1);
    }
    
    public void add(int num) {
        //Because anything past the last 0 is going to be zero you can clear the list
        if (num == 0) {
            running.clear(); //Now running always stores the last numbers up until zero was seen
            running.add(1);
        }
        else {
            running.add(num * running.get(running.size() - 1));
        }
    }
    
    public int getProduct(int k) {
        if (k >= running.size()) return 0;
        
        return running.get(running.size() - 1) / running.get(running.size() - k - 1);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
