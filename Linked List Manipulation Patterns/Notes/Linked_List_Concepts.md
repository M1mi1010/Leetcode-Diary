| 1 | [Merging Two Sorted Lists](#merging-two-sorted-lists) | Merge Two Sorted Lists, Merge K Sorted Lists |

## Merging Two Sorted Lists

**When to use:** Combining two already-sorted linked lists into one sorted list, implementing
merge sort, synchronising sorted data streams. Look for "merge", "combine", or "splice"
with sorted sequences.

---

### Key Idea

Since both lists are already sorted, the smallest element is always at one of the two
heads. Compare the two heads, take the smaller one, and repeat — no re-sorting needed.

---

### The Dummy Node Trick

Start with a dummy node as a placeholder. This avoids special-casing the head of the
merged list — you always attach to `current.next` and return `dummy.next` at the end.

---

### Approach

**At each step:**
1. Compare the current heads of both lists.
2. Attach the smaller node to the merged list.
3. Advance the pointer in the list you took from.
4. When one list runs out, attach the remainder of the other — it's already sorted.

```
list1: 1 → 3 → 5
list2: 2 → 4 → 6

Step: pick 1, 2, 3, 4, 5, then attach 6
Result: 1 → 2 → 3 → 4 → 5 → 6
```

---

### Iterative vs Recursive

| | Iterative | Recursive |
|--|-----------|-----------|
| Space | O(1) | O(n+m) call stack |
| Readability | More verbose | More concise |
| Preferred | For large lists | For short lists |

---

### Edge Cases to Watch
- One or both lists empty — return the other list directly.
- Duplicate values — handled naturally, just pick either and continue.
- All nodes in one list smaller than the other — the loop exhausts one list immediately,
  then the remainder is attached in one step.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Iterative | O(n + m) | O(1) |
| Recursive | O(n + m) | O(n + m) |
