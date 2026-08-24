# Binary Search Patterns

## Table of Contents

| # | Pattern | Key Problems |
|---|---------|--------------|
| 1 | [Sorted Array Search](#sorted-array-search-binary-search) | Binary Search, First Bad Version, Search 2D Matrix, Sqrt(x) |
| 2 | [First/Last Occurrence](#firstlast-occurrence) | Find First and Last Position, Find K Closest Elements |

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
