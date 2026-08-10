class Solution {
    public int[] mostCompetitive(int[] nums, int k) {
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[k];
    int removalsPossible = nums.length - k;
    
    for (int i = 0; i < nums.length; i++) {
        while (!stack.empty() &&  nums[stack.peek()] > nums[i] && removalsPossible > 0) {
            stack.pop();
            removalsPossible--;
        }
        stack.push(i);
    }

    while (removalsPossible > 0) {
        stack.pop();
        removalsPossible--;
    }

    for (int i = k - 1; i >= 0; i--) {
        result[i] = nums[stack.pop()];
    }
    return result;
    }
}
