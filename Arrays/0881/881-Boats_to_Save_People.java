class Solution {
    public int numRescueBoats(int[] people, int limit) {
        //1. Sort array
        Arrays.sort(people);

        //2. Standard two pointer
        int p1 = 0;
        int p2 = people.length - 1;

        //Context applied
        int noBoats = 0;

        while (p1 <= p2) {            
            //Handling one left over between p1 p2
            if (people[p1] + people[p2] > limit) {
                p2--;
            }
            else { 
                p1++;
                p2--;
            }
            noBoats++;
        }

        

        return noBoats;
    }
}
