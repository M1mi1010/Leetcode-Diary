# Binary Search Patterns

## Table of Contents

| # | Pattern | Key Problems |
|---|---------|--------------|
| 1 | [Sorted Array Search](#sorted-array-search-binary-search) | Binary Search, First Bad Version, Search 2D Matrix, Sqrt(x) |
| 2 | [First/Last Occurrence](#firstlast-occurrence) | Find First and Last Position, Find K Closest Elements |
| 3 | [Rotated Array / Peak Finding](#rotated-array--peak-finding) | Find Min Rotated, Search Rotated, Find Peak Element, Find in Mountain |

---

## Sorted Array Search (Binary Search)

**When to use:** Any time the search space is sorted or monotonic — finding a target,
finding a boundary, finding the first/last position satisfying a condition. If you can
answer "is the answer ≤ mid?" with a yes/no, binary search applies.

---

### Core Idea

Each comparison eliminates half the search space. Instead of scanning every element
O(n), you repeatedly halve the range until you've found the target or proven it doesn't
exist — O(log n).

```
array = [1, 3, 5, 7, 9, 11], target = 7

lo=0, hi=5, mid=2 → arr[2]=5 < 7 → search right → lo=3
lo=3, hi=5, mid=4 → arr[4]=9 > 7 → search left  → hi=3
lo=3, hi=3, mid=3 → arr[3]=7 = 7 → found ✓
```

---

### The Two Templates

**Exact match** — find a specific value:
```
lo = 0, hi = n-1
while lo <= hi:
    mid = lo + (hi-lo)/2
    if arr[mid] == target: return mid
    elif arr[mid] < target: lo = mid+1
    else: hi = mid-1
return -1
```

**Boundary search** — find first/last position satisfying a condition:
```
lo = 0, hi = n-1, ans = -1
while lo <= hi:
    mid = lo + (hi-lo)/2
    if condition(mid):
        ans = mid        ← record candidate, keep searching
        hi = mid-1       ← for leftmost; use lo=mid+1 for rightmost
    else:
        lo = mid+1
return ans
```

The boundary template is more powerful — exact match is a special case of it.

---

### Binary Search on the Answer

Many problems aren't searching an array but searching a value range. If you can define
a monotonic condition — "is X achievable with value mid?" — binary search finds the
minimum or maximum valid answer.

```
Sqrt(x): find largest m where m*m <= x
  lo=0, hi=x
  while lo <= hi:
      mid = lo + (hi-lo)/2
      if mid*mid <= x: ans=mid; lo=mid+1
      else: hi=mid-1
  return ans

First Bad Version: find first m where isBadVersion(m) = true
  → boundary search, condition = isBadVersion(mid)
  → keep hi=mid-1 when true, lo=mid+1 when false
```

---

### Common Variants

**Search insert position:** Find leftmost position where `arr[mid] >= target`.
If target exists, returns its index. If not, returns where it would be inserted.

**Single element in sorted array:** All elements appear twice except one. At any `mid`,
check whether `arr[mid] == arr[mid^1]` (XOR with 1 flips between even/odd pair).
If equal, the single element is to the right. If not, it's to the left or at mid.

**Search a 2D matrix:** Each row sorted, each row starts after previous row ends.
Treat as a flattened 1D array — map `mid` to `(mid/cols, mid%cols)`.

**Kth missing positive:** Count of missing positives up to value `mid` =
`mid - (count of array elements ≤ mid)`. Binary search for smallest mid where
this count ≥ k.

---

### Off-by-one and Overflow

- Always use `lo + (hi-lo)/2` not `(lo+hi)/2` — avoids integer overflow
- `while lo <= hi` for exact match, decide consistently for boundary search
- After the loop in boundary search, verify `ans` was set before returning

---

### Edge Cases
- Empty array — return -1 or 0 depending on the problem
- Target outside the array range — boundary template handles this if `ans` is
  initialised to -1 or a sentinel
- Single element array — loop runs once, exits cleanly
- All elements satisfy the condition — answer is index 0

---

### Complexity
| | Time | Space |
|--|------|-------|
| Binary search | O(log n) | O(1) |
| Binary search on value range [lo, hi] | O(log(hi-lo)) | O(1) |

---

## First/Last Occurrence

**When to use:** Finding the leftmost or rightmost index of a target in a sorted array,
finding the k elements closest to a target value, any problem requiring a precise
boundary position rather than just existence.

---

### Core Idea

A sorted array can contain duplicates, so a single binary search isn't enough — it
might land anywhere in a run of equal values. Two separate binary searches find the
left boundary and right boundary independently.

```
array = [1, 2, 2, 2, 3, 4], target = 2

Left boundary search (first occurrence):
  lo=0, hi=5
  mid=2 → arr[2]=2, condition true → ans=2, hi=1
  mid=0 → arr[0]=1, condition false → lo=1
  mid=1 → arr[1]=2, condition true → ans=1, hi=0
  loop ends → first = 1 ✓

Right boundary search (last occurrence):
  lo=0, hi=5
  mid=2 → arr[2]=2, condition true → ans=2, lo=3
  mid=4 → arr[4]=3, condition false → hi=3
  mid=3 → arr[3]=2, condition true → ans=3, lo=4
  loop ends → last = 3 ✓
```

---

### Left vs Right Boundary

The only difference between finding the first and last occurrence is which direction
you continue searching after finding a match:

```
First occurrence (leftmost):
  if arr[mid] == target: ans=mid; hi=mid-1  ← keep going left

Last occurrence (rightmost):
  if arr[mid] == target: ans=mid; lo=mid+1  ← keep going right
```

Everything else is identical.

---

### Find K Closest Elements

Given a sorted array, find k elements closest to target x. The answer is always a
contiguous subarray — binary search finds the optimal left boundary of that window.

**Key insight:** You're not searching for x itself, you're searching for the best
starting index `i` such that the window `[i, i+k-1]` is optimal. The condition
compares how far the left edge `arr[mid]` and right edge `arr[mid+k]` are from x:

```
if x - arr[mid] > arr[mid+k] - x:
    lo = mid+1   ← right edge is closer, shift window right
else:
    hi = mid     ← left edge is closer or equal, shift window left
```

This runs in O(log(n-k)) and avoids sorting by distance entirely.

---

### Why Not Just Use indexOf?

Linear scan is O(n). Binary search is O(log n). For large arrays with many duplicates
this matters significantly — and interviewers specifically test whether you know to
use two binary searches rather than scan inward from a found position.

---

### Edge Cases
- Target not in array — both boundaries return -1, handle before using the result
- All elements equal to target — first=0, last=n-1
- k = n in k closest — return the whole array
- x smaller than all elements — the window is always the leftmost k elements
- x larger than all elements — the window is always the rightmost k elements

---

### Complexity
| | Time | Space |
|--|------|-------|
| First/last occurrence | O(log n) | O(1) |
| K closest elements | O(log(n-k) + k) | O(1) |

## Rotated Array / Peak Finding

**When to use:** Sorted arrays that have been rotated, finding peaks in mountain-shaped
arrays, binary searching on arrays that aren't fully sorted but have local structure
you can exploit.

---

### Core Idea

A standard binary search requires the array to be fully sorted. Rotated and mountain
arrays break full sorting but preserve a key property — at any `mid`, you can always
determine which half is "more sorted" or which direction the peak/minimum lies. This
lets you still eliminate half the search space each step.

---

### Find Minimum in Rotated Sorted Array

A sorted array rotated at some pivot has two sorted halves. The minimum is at the
rotation point — where the array "resets" from high back to low.

```
[4, 5, 6, 7, 0, 1, 2]
              ↑ minimum at rotation point

At any mid:
- If arr[mid] > arr[hi]: minimum is in the RIGHT half (mid is in the left sorted half)
- If arr[mid] < arr[hi]: minimum is in the LEFT half including mid
```

```
lo=0, hi=6
mid=3 → arr[3]=7 > arr[6]=2 → min is right → lo=4
mid=5 → arr[5]=1 < arr[6]=2 → min is left  → hi=5
mid=4 → arr[4]=0 < arr[5]=1 → min is left  → hi=4
lo=hi=4 → minimum = arr[4] = 0 ✓
```

---

### Search in Rotated Sorted Array

Find a target in a rotated sorted array. At any `mid`, one half is always fully sorted.
Check if the target falls within the sorted half — if yes, search there. If no, search
the other half.

```
[4, 5, 6, 7, 0, 1, 2], target = 0

mid=3, arr[mid]=7
Left half [4,5,6,7] is sorted (arr[lo]=4 ≤ arr[mid]=7)
Is 0 in [4..7]? No → search right half → lo=4

mid=5, arr[mid]=1
Left half [0,1] is sorted (arr[lo]=0 ≤ arr[mid]=1)
Is 0 in [0..1]? Yes → search left half → hi=4

mid=4, arr[mid]=0 == target ✓
```

**The key question at every step:** which half is fully sorted? Then: does the target
fall in that sorted half?

---

### Rotated Array II (With Duplicates)

When duplicates are allowed, `arr[lo] == arr[mid] == arr[hi]` can occur — you can't
determine which half is sorted. The only safe move is to shrink both boundaries by 1:
`lo++; hi--`. This degrades worst case to O(n) but average stays O(log n).

---

### Find Peak Element

A peak is any element greater than its neighbours. The array has no particular global
structure, but the local slope tells you where to search.

**Key insight:** If `arr[mid] < arr[mid+1]`, the slope goes up to the right — a peak
must exist to the right (or at the right boundary). If `arr[mid] > arr[mid+1]`, slope
goes down — a peak exists to the left or at mid.

```
[1, 2, 3, 1]

mid=1, arr[1]=2 < arr[2]=3 → peak is right → lo=2
mid=2, arr[2]=3 > arr[3]=1 → peak is left/here → hi=2
lo=hi=2 → peak = arr[2] = 3 ✓
```

You never need to check both sides — the slope at mid always guarantees a peak exists
in one direction.

---

### Peak Index in a Mountain Array

A mountain array strictly increases then strictly decreases — exactly one peak.
Same logic as Find Peak Element but the structure is guaranteed, making it slightly
simpler. Follow the slope upward: if `arr[mid] < arr[mid+1]`, go right; else go left.

---

### Find in Mountain Array

Find a target in a mountain array (strictly increases then decreases). Three steps:

1. **Find the peak index** — binary search using slope comparison
2. **Binary search the ascending left half** `[0, peak]`
3. **Binary search the descending right half** `[peak, n-1]` (reversed comparisons)

Return the smaller index if found in both halves (left half first). This is three
separate binary searches chained together — O(log n) total.

```
Mountain: [1, 2, 3, 4, 5, 3, 1], target = 3

Step 1: peak at index 4 (value 5)
Step 2: search [1,2,3,4,5] → found at index 2
Step 3: search [5,3,1] descending → found at index 5
Return index 2 (smaller) ✓
```

---

### Choosing the Right Comparison

| Problem | Compare mid to | Direction rule |
|---|---|---|
| Find minimum | `arr[hi]` | `arr[mid] > arr[hi]` → go right |
| Search rotated | `arr[lo]` and target | check which half is sorted |
| Find peak | `arr[mid+1]` | `arr[mid] < arr[mid+1]` → go right |
| Mountain peak | `arr[mid+1]` | same as find peak |

---

### Edge Cases to Watch
- Single element — always the minimum, always the peak, always check if it's the target
- No rotation (pivot at index 0) — the array is fully sorted; standard binary search
  conditions handle this naturally
- Duplicates (Rotated II) — when boundaries equal mid, shrink both; never assume
  which half is sorted
- Find in Mountain: target equals peak value — found in step 1 implicitly; make sure
  your search boundaries include the peak in one of the halves
- Peak at index 0 or n-1 — problem constraints usually guarantee interior peak, but
  check the problem statement

---

### Complexity
| Problem | Time | Space |
|--|------|-------|
| Find minimum in rotated | O(log n) | O(1) |
| Search in rotated | O(log n) | O(1) |
| Search rotated with duplicates | O(log n) avg, O(n) worst | O(1) |
| Find peak element | O(log n) | O(1) |
| Find in mountain array | O(log n) | O(1) |
