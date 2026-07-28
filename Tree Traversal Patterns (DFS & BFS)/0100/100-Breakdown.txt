Initial attempt:
- Was overcomplicating and had a helper which took both child nodes of p and q trees
- Couldn't see a solution for when the helper would have to continue checking the side which isnt null - for example, when the left tree in both is null and the right tree is not, you can't put null into the helper with the way I had written it

Final attempt:
- Return the helper function from the main one
- In the helper function and check if both trees are empty, only one tree is empty and if the root node values are the same value
- Return the helper with both left && both rights

Stats (at time of submission):
- Runtime: 0 ms (Beats 100.00%)
- Memory: 42.74 MB (Beats 71.66%)
- Time taken: 19m 19s
