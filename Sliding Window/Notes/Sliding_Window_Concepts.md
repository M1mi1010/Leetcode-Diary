## Sliding Window

**When to use:** Problems involving contiguous subarrays or substrings, finding
optimal/valid windows, anything phrased as "longest", "shortest", "maximum sum",
"at most k distinct", or "all subarrays of size k". The window avoids recomputing
from scratch by reusing previous work as it slides.

---

## Fixed Size Window

**When to use:** The window size `k` is given. You need a statistic (sum, average,
max, frequency) over every subarray of exactly that size.

### Key Idea

Build the first window of size `k`, then slide it one step at a time — add the
incoming element on the right, remove the outgoing element on the left. Each step
is O(1) instead of recomputing the whole window.

```
k = 3, array = [1, 3, 2, 6, 4]

Window 1: [1, 3, 2] → sum = 6
Slide: +6, -1 → [3, 2, 6] → sum = 11
Slide: +4, -3 → [2, 6, 4] → sum = 12
```

### Edge Cases
- `k > array length` — no valid window exists.
- After building the first window, the loop runs `n - k` more times, not `n`.

### Complexity
| | Time | Space |
|--|------|-------|
| Fixed window | O(n) | O(1) |

---

## Variable Size Window

**When to use:** The window size isn't fixed — you expand and shrink based on a
condition. "Longest subarray where...", "shortest subarray with sum ≥ target",
"at most k distinct characters".

### Key Idea

Two pointers `left` and `right`. Expand `right` to include new elements. When the
window violates the condition, advance `left` to shrink it until it's valid again.
The window always represents the current best candidate.

### Two Flavours

**Longest valid window:** Expand greedily. Shrink only when the condition is broken.
Track the max length seen.

**Shortest valid window:** Expand until valid, then shrink as much as possible while
still valid. Track the min length seen.

```
Longest subarray with sum ≤ target:
→ expand right freely, shrink left when sum exceeds target

Shortest subarray with sum ≥ target:
→ expand until sum reaches target, then shrink left as far as possible
```

### The Shrink Condition

The hardest part is defining exactly when to shrink and by how much. Ask:
- Can I shrink one step at a time (most cases)?
- Or do I need to jump left to a specific position (seen-check problems)?

### Common Window States to Track
- Running sum (add/subtract as elements enter/leave)
- Frequency map (increment on entry, decrement on exit, remove at 0)
- Count of distinct elements
- Count of "bad" elements (elements violating the condition)

### The "At Most K" Trick

Some problems ask for subarrays with **exactly k** of something. This is hard to
track directly. The trick:

> **exactly k** = at most k − at most (k-1)

Solve two easier "at most" problems and subtract.

### Edge Cases
- Empty array or `k = 0` — handle before the loop.
- Window that never becomes valid — return 0 or -1 depending on the problem.
- Negative numbers in sum problems — a larger window isn't always better, which
  can break the two-pointer approach entirely (need a different strategy).

### Complexity
| | Time | Space |
|--|------|-------|
| Variable window | O(n) | O(1) to O(k) depending on state tracked |

---

## Character Frequency Matching

**When to use:** Checking if a window of a string matches a target's character
frequencies — anagram detection, permutation checking.

### Key Idea

A fixed-size window slides across the string. At each position, check if the window's
character frequencies match the target's. Instead of comparing the full frequency map
each time (O(26)), maintain a counter of how many characters are currently "satisfied"
and update it as elements enter and leave.

### The Satisfied Counter Pattern

Keep a variable `matches` = number of characters whose frequency in the window equals
the target. When an element enters, check if its new count matches the target — if so,
increment `matches`. When an element leaves, check if its old count was matching — if
so, decrement `matches`. When `matches == number of unique chars in target`, the window
is a valid anagram.

This reduces the per-step check from O(26) to O(1).

### Edge Cases
- Window smaller than target — skip entirely, no valid anagram possible.
- Characters not in the target — they don't affect `matches` but still shift the window.

### Complexity
| | Time | Space |
|--|------|-------|
| Character matching | O(n) | O(1) — at most 26 characters |

---

## Monotonic Queue (Sliding Window Max/Min)

**When to use:** Finding the maximum or minimum within every window of size `k`,
or problems where you need to efficiently query the best element in a sliding range.

### Key Idea

A regular sliding window can't answer "what's the max in this window?" in O(1) —
removing the old max requires scanning the window. A **monotonic deque** solves this
by maintaining elements in sorted order, so the front is always the current max/min.

### The Deque Invariant

For a **maximum** window:
- When adding a new element, remove all elements from the back of the deque that are
  smaller than the new element — they can never be the max while this element is in
  the window.
- The front of the deque is always the maximum of the current window.
- Before reading the max, check if the front element has expired (its index is outside
  the window) and remove it.

```
k=3, array = [1, 3, -1, -3, 5, 3, 6, 7]

Deque stores indices, values shown for clarity:
Add 1: [1]
Add 3: [3]       (1 removed — smaller than 3)
Add -1: [3,-1]   max = 3
Add -3: [3,-1,-3] max = 3  (3 expires next step)
Add 5: [5]       max = 5
...
```

### Why a Deque?

You need to remove from both ends:
- **Back:** to maintain the monotonic invariant when adding.
- **Front:** to expire elements that have left the window.

### Connection to DP

Some problems (like Jump Game VI) use a monotonic deque not for a sliding window
but for DP optimisation — the deque tracks the best previous DP state within a
valid range, giving O(1) lookup instead of O(k) scanning.

### Edge Cases
- Always check front expiry **before** reading the max.
- For minimum window, reverse the invariant — remove from the back elements that
  are **larger** than the incoming element.

### Complexity
| | Time | Space |
|--|------|-------|
| Monotonic deque window | O(n) | O(k) |

Each element is added and removed from the deque at most once across the full traversal.

---

## Choosing the Right Variant

| Signal in problem | Pattern |
|---|---|
| "every subarray of size k" | Fixed window |
| "longest/shortest subarray where..." | Variable window |
| "anagram", "permutation in string" | Character frequency matching |
| "maximum/minimum in every window of size k" | Monotonic queue |
| "exactly k" of something | Variable window + at-most trick |
