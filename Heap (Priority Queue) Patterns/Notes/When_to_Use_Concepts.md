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
