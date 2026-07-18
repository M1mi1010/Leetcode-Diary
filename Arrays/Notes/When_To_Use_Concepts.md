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


## Spiral Traversal

**When to use:** Traversing or filling a matrix in spiral order (outside in), generating
spiral-ordered sequences, any problem where you need to visit every element in a clockwise
inward path.

---

### Key Idea

Define four boundaries — top, bottom, left, right — and shrink them inward as you complete
each edge of the spiral. Traverse in four directions repeatedly until the boundaries cross.

```
→ → → →
        ↓
← ← ← ↓
↑       ↓
↑ → → ↑
```

Each full loop of the spiral processes one "ring" of the matrix, then the boundaries
contract inward by one on all sides.

---

### The Four Boundaries

- **Top** — the first unvisited row (moves down after traversing right)
- **Bottom** — the last unvisited row (moves up after traversing left)
- **Left** — the first unvisited column (moves right after traversing down)
- **Right** — the last unvisited column (moves left after traversing up)

After walking each edge, shrink that boundary inward before changing direction.

---

### Traversal Order (Clockwise)

1. Left → Right along the top row, then top++
2. Top → Bottom along the right column, then right--
3. Right → Left along the bottom row, then bottom--
4. Bottom → Top along the left column, then left++
5. Repeat until top > bottom or left > right

---

### Why Shrink Boundaries?

Once you've visited a row or column, you never want to revisit it. Shrinking the boundary
after each pass is what prevents overlap and ensures every cell is visited exactly once.

---

### Spiral Fill vs Spiral Read

These problems come in two flavours:

**Read (Spiral Matrix I, III):** Given a filled matrix, extract elements in spiral order
into a list.

**Fill (Spiral Matrix II, IV):** Given an empty matrix (or a linked list), place values
into positions in spiral order.

The boundary-shrinking logic is identical — the only difference is whether you're reading
from or writing to each cell.

---

### Edge Cases to Watch
- Non-square matrices (m × n) — top/bottom track rows, left/right track columns independently.
- Single row or single column — the spiral degenerates into a straight line; boundary checks
  prevent double-traversal.
- After shrinking, always check `top <= bottom` and `left <= right` before each directional
  pass within the same loop

## Matrix In-Place State Tracking

**When to use:** Modifying a matrix based on its current state where changes must not affect
the reading of other cells, propagating a condition across rows/columns, traversing a matrix
in a non-standard order (diagonal, spiral).

---

### Key Idea

The core challenge in these problems is that naively modifying cells as you read them
corrupts the information you still need. You need a way to record what changes are needed
first, then apply them in a second pass — ideally without extra space.

---

### Set Matrix Zeroes

**Problem:** If a cell is 0, set its entire row and column to 0.

**Naive approach:** Use two extra arrays (or a set) to record which rows and columns contain
a zero, then zero them out in a second pass. O(m + n) space.

**Optimal approach:** Use the matrix itself as storage. The first row and first column can
act as markers — if a cell should be zeroed, mark its row header and column header in row 0
and column 0. Then use those markers to zero out the rest. Handle the first row and column
separately since they're being used as markers.

**The two-pass principle:**
1. Scan the matrix, recording which rows and columns need zeroing using markers.
2. Apply the zeroing based on those markers.

Never zero cells during the scan —

## Prefix & Suffix Products

**When to use:** Computing a value for each element based on all other elements, problems
where you need to "look left and right" from every position simultaneously, any problem
where a brute force would recompute overlapping ranges repeatedly.

---

### Key Idea

Instead of recomputing the product of all other elements for each index (O(n²)), precompute
running products from the left and right separately, then combine them.

For any index `i`, the product of everything except `i` is:
> (product of everything to the left of i) × (product of everything to the right of i)

---

### Product of Array Except Self

**Naive approach:** For each element, loop through the entire array multiplying everything
else. O(n²) time.

**Optimal approach:** Two-pass prefix/suffix technique.

**Pass 1 — Left products:** Build an array where each position holds the product of all
elements to its left. The leftmost element has no left neighbours so its value is 1.

**Pass 2 — Right products:** Traverse right to left, maintaining a running product of
everything to the right. Multiply this into the left-product array at each position.

The result array at each index ends up holding left product × right product — exactly
the product of all other elements.

**Why no division?** The obvious shortcut is total product ÷ current element, but this
breaks when zeros are present (division by zero). The prefix/suffix approach handles zeros
naturally.

---

### The Prefix/Suffix Pattern

This same idea generalises beyond products:

| Problem type | Left pass | Right pass |
|---|---|---|
| Product except self | Running product | Running product |
| Sum except self | Running sum | Running sum |
| Max to the left/right | Running max | Running max |

Any time a problem asks "for each element, compute something about all other elements",
think about whether a left pass and a right pass can each capture half the information,
then be combined.

---

### Longest Mountain in Array

**Problem:** Find the longest subarray that strictly increases then strictly decreases
(a "mountain" shape). Must have at least one element on each side of the peak.

**Key Insight:** This is also a prefix/suffix problem in disguise.

**Pass 1 — Left slopes:** For each index, compute how long the strictly increasing run
is coming from the left. Flat or decreasing resets this to 0.

**Pass 2 — Right slopes:** For each index, compute how long the strictly decreasing run
is coming from the right.

**Combine:** A valid mountain peak at index `i` requires both left[i] > 0 and right[i] > 0.
The mountain length is left[i] + right[i] + 1. Take the maximum across all valid peaks.

---

### Common Thread

Both problems avoid O(n²) by splitting "look at everything else" into two cheaper passes:

| Problem | What left pass captures | What right pass captures |
|---------|------------------------|--------------------------|
| Product except self | Product of left neighbours | Product of right neighbours |
| Longest mountain | Length of ascending run from left | Length of descending run from right |

---

### Edge Cases to Watch
- Product except self: zeros in the array — the prefix/suffix approach handles these
  correctly where division would not.
- Product except self: multiple zeros — every result will be 0 except potentially none.
- Longest mountain: a strictly flat section resets both slope counters — equal adjacent
  elements are neither ascending nor descending.
- Longest mountain: a valid mountain needs at least one step up and one step down — peaks
  at the edges of the array can never form a valid mountain.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Product except self (with output array) | O(n) | O(1) extra |
| Longest mountain | O(n) | O(n) for slope arrays, O(1) if computed on the fly |
