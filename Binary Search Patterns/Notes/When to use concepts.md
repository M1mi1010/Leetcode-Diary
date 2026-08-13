## Sorted Array Search (Binary Search)

**When to use:** Any time the search space is sorted or monotonic — finding a target,
finding a boundary, finding the first/last position satisfying a condition. If you can
answer "is the answer ≤ mid?" with a yes/no, binary search applies.

---

### Core Idea

Each comparison eliminates half the search space. Instead of scanning every element
O(n), you repeatedly halve the range until you've either found the target or proven
it doesn't exist — O(log n).

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

**Boundary search** — find the first/last position satisfying a condition:
```
lo = 0, hi = n-1, ans = -1
while lo <= hi:
    mid = lo + (hi-lo)/2
    if condition(mid):
        ans = mid        ← record candidate, keep searching
        hi = mid-1       ← for first true; use lo=mid+1 for last true
    else:
        lo = mid+1
return ans
```

The boundary template is more powerful — the exact match template is a special case.

---

### Binary Search on the Answer

Many problems aren't searching an array but searching a *value range*. If you can
define a monotonic condition — "is X achievable with mid?" — binary search finds the
minimum or maximum valid X.

Examples:
- Sqrt(x): find largest m where m² ≤ x
- First Bad Version: find first m where isBadVersion(m) = true
- Kth Missing Positive: find smallest m where count of missing numbers ≤ m is ≥ k

The array doesn't need to exist — the "search space" is an abstract range of values.

---

### Recognising the Condition

The hardest part is framing the condition. Ask:
- Is there a clear yes/no test for any candidate value?
- Does the answer change from "no" to "yes" at exactly one point (monotonic)?

If yes — binary search applies regardless of whether there's a physical array.

---

### Common Variants

**Search insert position:** Where would target go if inserted? Binary search for the
leftmost
