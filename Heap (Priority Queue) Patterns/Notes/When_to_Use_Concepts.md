| 1 | [Top K Elements](#top-k-elements) | Kth Largest, K Closest Points, Top K Frequent, Last Stone Weight |

| 2 | [K-way Merge](#k-way-merge) | Merge K Sorted Lists, Kth Smallest in Matrix, Find K Pairs |

## Top K Elements

**When to use:** Finding the k largest, k smallest, k most frequent, or kth element
in a collection. Any problem where you need a ranked subset without fully sorting.

---

### Core Idea

Sorting the entire array to find k elements wastes work — O(n log n) when you only
need O(n log k). A heap lets you maintain exactly k candidates at a time, discarding
the rest as you go.

---

### The Heap Intuition

A **min-heap of size k** keeps the k largest elements seen so far. The top is always
the smallest of those k — your current "threshold". When a new element arrives:
- If it's larger than the top, it deserves a spot — pop the top, push the new one
- If it's smaller or equal, it can't make the top k — ignore it

At the end, the heap contains exactly the k largest elements. The top is the kth largest.

For k smallest, flip to a **max-heap of size k** — same logic, opposite comparison.

```
k=3, array = [3, 1, 4, 1, 5, 9, 2, 6]

After 3:        heap: [3]
After 1:        heap: [1, 3]
After 4:        heap: [1, 3, 4]    ← heap full
After 1: 1 ≤ 1 (top) → skip
After 5: 5 > 1 → pop 1, push 5  heap: [3, 4, 5]
After 9: 9 > 3 → pop 3, push 9  heap: [4, 5, 9]
After 2: 2 < 4 → skip
After 6: 6 > 4 → pop 4, push 6  heap: [5, 6, 9]

k largest: [5, 6, 9]. Kth largest (k=3): 5 ✓
```

---

### Kth Largest in a Stream

When elements arrive one at a time (stream), you can't sort. Maintain a min-heap of
size k permanently. Each new element is evaluated against the heap top. The heap top
is always the answer — the kth largest seen so far.

This is the heap approach above, but the heap is never discarded between queries.

---

### Top K Frequent Elements

Frequency first, then rank by frequency. Two steps:
1. Build a frequency map — count occurrences of each element O(n)
2. Use a min-heap of size k on `(frequency, element)` pairs — keep the k most frequent

Alternative: **bucket sort by frequency.** Create an array of size n+1 where index i
holds all elements that appear exactly i times. Scan from the right to collect the
top k. O(n) time, avoids the heap entirely.

---

### K Closest Points to Origin

"Closest" = smallest distance. Use a **max-heap of size k** on distance. When a point
is closer than the current furthest-of-k (the heap top), it replaces it.

You don't need the actual distance — comparing squared distances avoids the square
root and is equally correct.

---

### Quickselect — O(n) Average Alternative

For a one-off kth element query (not a stream), quickselect avoids the heap entirely.
It's the partition step from quicksort — after partitioning, the pivot is in its final
sorted position. If it lands at index k-1, you're done. Otherwise recurse into only
one half.

- Average: O(n)
- Worst case: O(n²) — use random pivot to avoid this
- Space: O(1)

The heap approach is O(n log k) but simpler to implement correctly. Quickselect is
faster on average but trickier. Know both — interviewers sometimes ask for the O(n)
solution explicitly.

---

### Choosing the Right Tool

| Situation | Tool |
|---|---|
| One-off top k from array | Min-heap size k, or quickselect |
| Streaming / online queries | Min-heap size k, kept permanently |
| Top k by frequency | Frequency map + heap, or bucket sort |
| K closest by distance | Max-heap size k on distance |
| Need exact kth, O(n) time | Quickselect |

---

### Java Heap Reminders

Java's `PriorityQueue` is a **min-heap** by default. For a max-heap:
```
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
```
For custom objects (e.g. `int[]` by frequency):
```
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
```

---

### Edge Cases to Watch
- k = 1 → just find the max/min, though the heap approach still works
- k = n → return everything, no filtering needed
- Duplicate values — a min-heap of size k handles duplicates naturally
- Frequency ties in top k frequent — the problem usually says any valid answer is
  acceptable, so tie-breaking doesn't matter
- Stream with fewer than k elements so far — the heap isn't full yet, so its size
  is the answer count, not k

---

### Complexity
| Approach | Time | Space |
|---|---|---|
| Heap size k | O(n log k) | O(k) |
| Full sort | O(n log n) | O(1) or O(n) |
| Quickselect | O(n) average | O(1) |
| Bucket sort (frequency) | O(n) | O(n) |

## K-way Merge

**When to use:** Merging multiple sorted lists into one, finding the kth smallest
element across k sorted structures, finding ranges that cover all k groups. Any
problem where you have k sorted sources and need to efficiently pull the next smallest
element across all of them.

---

### Core Idea

Merging two sorted lists is easy — compare the two heads and take the smaller. K-way
merge generalises this to k lists. The naive approach compares all k heads each time
(O(k) per step), but a min-heap reduces this to O(log k) per step by always giving
you the current global minimum instantly.

**The pattern:**
1. Push the first element from each list into a min-heap, along with metadata (which
   list it came from, its index within that list)
2. Pop the minimum — this is the next element in the merged order
3. Push the next element from the same list the popped element came from
4. Repeat

```
Lists: [1,4,7]  [2,5,8]  [3,6,9]

Initial heap: [(1,list0,idx0), (2,list1,idx0), (3,list2,idx0)]

Pop (1,list0) → output 1, push (4,list0,idx1)
heap: [(2,list1), (3,list2), (4,list0)]

Pop (2,list1) → output 2, push (5,list1,idx1)
heap: [(3,list2), (4,list0), (5,list1)]

... continues until all lists exhausted

Output: 1,2,3,4,5,6,7,8,9 ✓
```

---

### What to Store in the Heap

The heap entry always needs enough information to fetch the *next* element from the
same source. Typically: `(value, list_index, element_index)`. When you pop, use
`list_index` and `element_index + 1` to find and push the successor.

---

### Kth Smallest in a Sorted Matrix

A sorted matrix (each row and column sorted) is treated as n sorted lists (the rows).
Push the first element of each row, then run k-way merge logic, popping k times.
The kth pop is the answer.

Alternatively, binary search on the value range — for a given mid value, count how
many elements are ≤ mid using the sorted structure. The kth smallest is the smallest
value where that count ≥ k.

---

### Find K Pairs with Smallest Sums

Given two sorted arrays, find the k pairs `(a, b)` with smallest `a + b`. The key
insight: treat each element of the first array as the "head" of a sorted list (paired
with all elements of the second array, in order). Push the first pair from each, then
run k-way merge.

Optimisation: you only need to push pairs `(nums1[i], nums2[0])` for the first min(k,
n) elements of nums1 — pairs beyond that can never be in the top k.

---

### Smallest Range Covering Elements from K Lists

Find the smallest range `[l, r]` such that at least one element from each of the k
lists falls within it. The heap always holds exactly one element per list — the current
"window" across all lists. The range is `[heap_min, current_max]`. Advance the list
that contributed the minimum (pop and push next), updating the max. Track the smallest
range seen.

The window always contains exactly k elements (one per list), and shrinking it means
advancing the minimum — the only move available with a min-heap.

---

### Merge K Sorted Lists

The classic k-way merge. Push the head of each linked list into the heap. Pop the
minimum, append to result, push its `.next` if it exists. The heap size stays ≤ k
throughout.

---

### Edge Cases to Watch
- Empty lists — check before pushing the first element, skip empty sources
- Lists of unequal length — when a list is exhausted, just don't push a successor;
  the heap naturally shrinks
- k = 1 — degenerates to returning the single list as-is
- Duplicate values across lists — the heap handles these correctly, just include
  all of them
- For smallest range: if any list is exhausted, no larger range can cover all lists,
  so stop immediately

---

### Complexity
| Problem | Time | Space |
|---|---|---|
| Merge k sorted lists (n total elements) | O(n log k) | O(k) |
| Kth smallest in matrix | O(k log n) | O(n) |
| Find k pairs with smallest sums | O(k log k) | O(k) |
| Smallest range | O(n log k) | O(k) |
