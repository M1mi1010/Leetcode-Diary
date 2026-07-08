# LeetCode Patterns

## Fast & Slow Pointers (Floyd's Cycle Detection)

### Common Uses
- Detect if a linked list has a cycle.
- Detect if a process repeats forever or eventually terminates.
- Find duplicate numbers in an array with **O(1)** extra space (e.g. LeetCode 287).

### Key Idea
- **Slow** pointer moves one step.
- **Fast** pointer moves two steps.
- If they meet, a cycle exists.
- Reset one pointer to the start and move both one step at a time to find the cycle entrance.

---

## Fixed Separation (Two Pointers)

### Common Uses
- Find the middle of a linked list.
- Remove the Nth node from the end.
- Delete the middle node.

### Key Idea
Maintain a fixed distance between two pointers.

#### Remove the Nth Node from the End
1. Move the `fast` pointer `n` steps ahead.
2. Move both pointers one step at a time.
3. When `fast` reaches the end, `slow` is at the target node.

#### Delete the Middle Node
- Use slow/fast pointers to locate the middle.
- Keep track of the previous node so you can remove the middle node.

---

## String Comparison with Backspaces

### Common Uses
- Compare user input after typo corrections.
- Simulate text editor backspaces.
- Handle undo/backspace operations.

### Approaches

#### Stack
- Push normal characters.
- Pop when encountering `#`.

#### Two Pointers (O(1) Space)
- Start from the end of both strings.
- Maintain a counter for pending backspaces.
- Skip characters that would have been deleted.
- Compare the next valid characters.
```
