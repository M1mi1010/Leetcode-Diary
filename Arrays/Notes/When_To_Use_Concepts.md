# LeetCode Patterns

## Fast & Slow Pointers (Floyd's Cycle Detection)

**When to use:** Linked list cycle detection, finding where a cycle starts, detecting if a process terminates or loops, finding duplicates in an array constrained to O(1) space.

### Key Idea
- **Slow** pointer moves one step.
- **Fast** pointer moves two steps.
- If they meet, a cycle exists.
- Reset one pointer to start, move both one step at a time to find the cycle entrance.

---

## Fixed Separation (Two Pointers)

**When to use:** Finding the middle of a linked list, removing the Nth node from the end, deleting the middle node — any problem where you need a pointer at a fixed offset from another.

### Key Idea
Maintain a fixed distance between two pointers.

#### Remove the Nth Node from the End
1. Move `fast` pointer `n` steps ahead.
2. Move both pointers one step at a time.
3. When `fast` reaches the end, `slow` is at the target node.

#### Delete the Middle Node
- Use slow/fast pointers to locate the middle.
- Track the previous node so you can splice it out.

---

## String Comparison with Backspaces

**When to use:** Comparing strings after backspace characters are applied, simulating text editor input, any problem where characters can cancel preceding ones.

### Approaches

#### Stack
- Push normal characters.
- Pop on `#`.

#### Two Pointers — O(1) Space
- Start from the end of both strings.
- Maintain a skip counter for pending backspaces.
- Skip characters that would have been deleted.
- Compare the next valid characters.

---

## Plus One

**When to use:** Incrementing a number stored as a digit array, any problem simulating grade-school addition on an array where you can't convert to an integer (large numbers).

### Key Idea
Walk right to left. Increment the current digit. If it stays below 10, return immediately. If it becomes 10, set to 0 and carry. If carry exits the array, prepend a 1.

### Complexity
- Time: O(n)
- Space: O(1) normally, O(n) for all-nines case

## In-place Rotation

**When to use:** Rotating a matrix 90°, rotating an array by k steps, any problem requiring
rearrangement without allocating a new array/matrix.

### Key Idea
Avoid the naive O(n) space solution of copying into a new structure. Instead, use mathematical
relationships between element positions to rotate in-place.

---

### Rotate Matrix 90° Clockwise (Rotate Image)

Two-step process:

**Step 1 — Transpose:** Swap `matrix[i][j]` with `matrix[j][i]` (flip along the diagonal).

**Step 2 — Reverse each row:** Reverse every row left-to-right.

```
Original          Transposed        Reversed rows (result)
1 2 3             1 4 7             7 4 1
4 5 6    --->     2 5 8    --->     8 5 2
7 8 9             3 6 9             9 6 3
```

For **anti-clockwise**: reverse each row first, then transpose.

---

### Rotate Array by k Steps (Rotate Array)

Three-reversal trick — all O(1) space:

1. Reverse the entire array.
2. Reverse the first `k` elements.
3. Reverse the remaining `n-k` elements.

```
nums = [1,2,3,4,5,6,7], k = 3

Step 1 — reverse all:     [7,6,5,4,3,2,1]
Step 2 — reverse first 3: [5,6,7,4,3,2,1]
Step 3 — reverse last 4:  [5,6,7,1,2,3,4]  ✓
```

> Note: use `k = k % n` first to handle cases where k > array length.

---

### Transpose Matrix

Swap `matrix[i][j]` with `matrix[j][i]` for all `i < j` (upper triangle only — doing both
triangles cancels out).

```java
for (int i = 0; i < n; i++)
    for (int j = i + 1; j < n; j++) {
        int tmp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = tmp;
    }
```

---

### Complexity
| Problem | Time | Space |
|---------|------|-------|
| Rotate Image | O(n²) | O(1) |
| Rotate Array | O(n) | O(1) |
| Transpose Matrix | O(n²) | O(1) |

### Common Mistakes
- Forgetting `k % n` for rotate array — k can exceed array length.
- Swapping both triangles in transpose — elements return to original positions.
- Confusing clockwise vs anti-clockwise — the order of transpose and reverse flips.
