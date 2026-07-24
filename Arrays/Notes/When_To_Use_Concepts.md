# LeetCode Patterns

# LeetCode Patterns

## Table of Contents

| # | Pattern | Key Problems |
|---|---------|--------------|
| 1 | [Fast & Slow Pointers](#fast--slow-pointers-floyds-cycle-detection) | Linked List Cycle, Find Duplicate |
| 2 | [Fixed Separation (Two Pointers)](#fixed-separation-two-pointers) | Remove Nth From End, Middle of List |
| 3 | [String Comparison with Backspaces](#string-comparison-with-backspaces) | Backspace String Compare |
| 4 | [Plus One](#plus-one) | Plus One, Add Binary |
| 5 | [In-place Rotation](#in-place-rotation) | Rotate Image, Rotate Array, Transpose Matrix |
| 6 | [Spiral Traversal](#spiral-traversal) | Spiral Matrix I-IV |
| 7 | [Matrix In-Place State Tracking](#matrix-in-place-state-tracking) | Set Matrix Zeroes, Game of Life |
| 8 | [Product of Array Except Self](#product-of-array-except-self) | Product Except Self, Longest Mountain |
| 9 | [Hashing — Frequency Map / Counting](#hashing--frequency-map--counting) | Group Anagrams, Top K Frequent |
| 10 | [Hashing — Seen Check](#hashing--seen-check) | Happy Number, Contains Duplicate II |

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

## Product of Array Except Self

**When to use:** Computing a value for each element based on all other elements without
division, problems where brute force would recompute overlapping ranges repeatedly.

---

### The Problem

For each index `i` in an array, find the product of every element except the one at `i`.
The constraint that makes it interesting — no division allowed, and must be O(n).

---

### Why Not Division?

The obvious approach is total product ÷ current element, but this breaks the moment
a zero appears in the array. The prefix/suffix approach handles zeros naturally.

---

### Core Concept: Prefix & Suffix Products

For any index `i`, the product of everything except `i` is just:

> **everything to its left** × **everything to its right**

So instead of recomputing from scratch for every index (O(n²)), you precompute both
sides separately and combine them.

**Left product at i** — the running product of all elements before index i.
The leftmost element has nothing to its left, so it starts as 1.

**Right product at i** — the running product of all elements after index i.
The rightmost element has nothing to its right, so it starts as 1.

```
array:         [2,  3,  4,  5]

left products: [1,  2,  6,  24]   (product of everything to the left)
right products:[60, 20, 5,  1]    (product of everything to the right)

result:        [60, 40, 30, 24]   (left × right at each index)
```

---

### The Two-Pass Principle

1. Traverse left to right, building up the left products.
2. Traverse right to left, building up the right products and combining as you go.

These two passes together give every element full knowledge of its neighbours on
both sides — in O(n) time and O(1) extra space if you reuse the output array.

---

### Why This Works

Each element is included in every other index's product exactly once — either as part
of a left product or a right product. No element contributes to its own result.

---

### Edge Cases to Watch
- **Single zero:** Only the index of the zero gets a non-zero result (the product of
  everything else). All other indices multiply in the zero and get 0.
- **Multiple zeros:** Every index gets 0, since every product includes at least one zero.
- **Negative numbers:** No special handling needed — products carry sign automatically.

## Hashing — Seen Check

**When to use:** Detecting if something has been encountered before, finding duplicates,
checking membership, detecting cycles. Look for "have I seen this?", "does this repeat?",
or "is this already in the collection?".

---

### Key Idea

Instead of scanning backwards through the array each time (O(n) per check), store every
element you've visited in a hash set. Membership checks then become O(1) — you just ask
"is this in the set?" before adding it.

---

### Core Variations

**Duplicate detection:** Add elements one by one. Before adding, check if it's already
in the set. If yes, you've found a duplicate.

**Cycle detection:** In a sequence where each value points to the next (like a linked list
or a number transformation), store every visited state. If you reach a state you've seen
before, a cycle exists.

**Intersection / membership:** Build a set from one collection, then check each element
of the second collection against it. Avoids nested loops.

**Sliding window seen check:** Track what's currently inside a window. When the window
slides, remove the element that left and add the one that entered.

---

### Set vs Map

| Use a Set when... | Use a Map when... |
|---|---|
| You only need to know if something was seen | You need to know when or where it was seen |
| Intersection, cycle detection, basic duplicates | "Within k distance", index-based constraints |

Example: Contains Duplicate II needs to know the **index** of the previous occurrence,
so a map (element → last seen index) is needed over a plain set.

---

### The Sliding Window Extension

Some seen-check problems add a constraint like "within k indices" or "within a window
of size k". The set then represents only the current window — add incoming elements,
remove outgoing ones as the window slides.

---

### Edge Cases to Watch
- Negative numbers and zeros are valid keys — sets and maps handle them fine.
- The same element appearing more than twice — a seen check catches it on the second
  occurrence regardless of how many times it appears after.
- Cycle detection via hashing is O(n) space — if the problem requires O(1) space,
  think fast/slow pointers instead (Floyd's algorithm).
- Linked list cycles — you're storing node references, not values, since two different
  nodes could hold the same value.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Seen check (set) | O(1) per lookup | O(n) |
| Sliding window seen check | O(1) amortised per element | O(k) window size |
| Cycle detection via hashing | O(n) | O(n) |
| Cycle detection via fast/slow | O(n) | O(1) |

## Prefix Sum

**When to use:** Multiple range sum queries on the same array, problems where brute force
repeatedly sums overlapping sections, sliding window aggregates, splitting arrays into
balanced partitions.

---

### Key Idea

Precompute a running total so any range sum can be answered in O(1) by subtraction —
like reading two odometer values and subtracting to find distance traveled.

For any range `[i, j]`:
> `range sum = prefix[j+1] - prefix[i]`

---

### Building the Prefix Array

The prefix array is one element longer than the input, with `prefix[0] = 0`. Each position
holds the sum of all elements up to that point:

```
nums:   [2,  4,  6,  8]
prefix: [0,  2,  6,  12, 20]
```

Then `sum(1, 3)` = `prefix[4] - prefix[1]` = `20 - 2` = `18`.

---

### Why the Extra Zero?

Starting with `prefix[0] = 0` eliminates the edge case of querying from index 0 — no
special handling needed, the formula works uniformly for all ranges.

---

### 2D Prefix Sum

The same idea extends to matrices for submatrix sum queries. Each cell stores the sum
of the entire rectangle from `(0,0)` to `(i,j)`. A submatrix sum is then computed using
inclusion-exclusion across four corners.

---

### Edge Cases to Watch
- Off-by-one errors — the most common bug. `prefix[j+1] - prefix[i]` for 0-indexed input.
- Products with zeros — division-based range products break when zeros are present;
  track zero counts separately.
- Single element queries — the formula still works,
