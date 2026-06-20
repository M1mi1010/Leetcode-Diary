class Solution {
    public int maxArea(int[] height) {
        int pointer2 = height.length - 1;
        int pointer1 = 0;
        int area = 0;

        while (pointer2 > pointer1) {
            int currArea = Math.min(height[pointer1], height[pointer2]) * (pointer2 - pointer1);
        
            if (currArea > area) {
                area = currArea;
            }

            if (height[pointer1] < height[pointer2]) {
                pointer1++;
            }
            else {
                pointer2--;
            }            
            
        }
        return area;
    }
}
