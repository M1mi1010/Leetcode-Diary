| 1 | [Merging Two Sorted Lists](#merging-two-sorted-lists) | Merge Two Sorted Lists, Merge K Sorted Lists |

| 2 | [In-place Reversal of Linked Lists](#in-place-reversal-of-linked-lists) | Reverse Linked List, Reverse Linked List II |

| 3 | [Intersection Detection](#intersection-detection) | Intersection of Two Linked Lists, Minimum Index Sum |

| 4 | [Addition of Numbers](#addition-of-numbers--linked-lists) | Add Two Numbers, Plus One Linked List |

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

## In-place Reversal of Linked Lists

**When to use:** Reversing all or part of a linked list in O(1) space, palindrome checks,
problems that say "in-place" or "no extra memory". Look for "reverse", "flip", or
"rearrange" in the problem statement.

---

### Key Idea

Instead of creating a new list, redirect the `next` pointers as you walk through.
Each node that pointed forward now points backward — no extra memory, just pointer
manipulation.

---

### The Three Pointers

- `prev` — the node behind current (starts as null)
- `curr` — the node being processed
- `next` — saved before overwriting `curr.next` so you don't lose the rest of the list

At each step: save next, flip the link, advance both pointers.

```
Before: null ← 1 → 2 → 3 → 4 → 5
After:  null ← 1 ← 2 ← 3 ← 4 ← 5
                                  ↑ new head (prev)
```

---

### Partial Reversal

For reversing between positions `left` and `right`:

1. Walk to the node just before `left` — this is your anchor.
2. Reverse the sublist from `left` to `right` using the same three-pointer approach.
3. Reconnect the reversed section back to the rest of the list.

A dummy node before the head simplifies reconnection when `left = 1`.

---

### Palindrome Check

1. Find the middle using slow/fast pointers.
2. Reverse the second half in-place.
3. Compare both halves node by node.
4. Optionally restore the list by reversing again.

---

### Edge Cases to Watch
- Empty list or single node — return as-is, nothing to reverse.
- Reversing at the head — use a dummy node to avoid special-casing the new head.
- Partial reversal reconnection — the node before `left` must point to the new front,
  and the old front of the sublist must point to the node after `right`.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Full reversal | O(n) | O(1) |
| Partial reversal | O(n) | O(1) |
| Recursive reversal | O(n) | O(n) call stack |

---

## Intersection Detection

**When to use:** Finding where two linked lists meet at a common node, finding shared
elements between two lists. Look for "common node", "intersection", or "shared".

---

### Key Idea

Two linked lists that intersect share a common tail — once they meet at a node, all
subsequent nodes are identical. The challenge is that the lists may have different lengths,
so the intersection node isn't at the same index in both.

---

### The Length Equalisation Trick

Find the length of both lists. Advance the pointer of the longer list by the difference
in lengths. Now both pointers are the same distance from the end — step both forward
together until they meet. That meeting point is the intersection.

---

### The Two-Pointer Shortcut

No need to compute lengths explicitly. Use two pointers starting at each head. When one
reaches the end, redirect it to the head of the **other** list. When the other reaches its
end, redirect it similarly. They will meet at the intersection after at most `m + n` steps
— because both pointers travel the same total distance.

```
Pointer A travels: list1 + list2
Pointer B travels: list2 + list1
Both travel the same distance → meet at intersection
```

If there's no intersection, both pointers reach null simultaneously.

---

### Minimum Index Sum Variant

When the "lists" are arrays of strings rather than linked list nodes, use a hash map.
Store each element of the first list with its index, then check each element of the second
list against the map. Track the pair with the minimum combined index sum.

---

### Edge Cases to Watch
- No intersection — both pointers reach null at the same time, return null.
- One list is empty — no intersection possible.
- Lists of equal length — no offset needed, pointers meet immediately if they intersect.
- Intersection at the head — works naturally with the two-pointer approach.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Length equalisation | O(m + n) | O(1) |
| Two-pointer shortcut | O(m + n) | O(1) |
| Hash map (index sum variant) | O(m + n) | O(m) |

## Addition of Numbers — Linked Lists

**When to use:** Adding numbers represented as linked lists, simulating arithmetic on
data structures where digits are stored as nodes. Look for "add", "sum", or "plus" where
the input is a linked list of digits.

---

### Key Idea

Digits are stored in nodes, so you can't do arithmetic directly. Simulate grade-school
addition — process digit by digit, tracking a carry that propagates forward through
the list.

---

### Digit Order Matters

Problems vary in how digits are ordered in the list:

**Reverse order (most common — Add Two Numbers):** The head is the least significant
digit. This is convenient because addition naturally starts from the least significant
digit, so you can just walk both lists forward together.

**Forward order (Plus One Linked List):** The head is the most significant digit.
Addition starts at the tail, so you need to either reverse the list first, use a stack
to process from the end, or use recursion to carry back up from the tail.

---

### The Carry

At each position, compute `sum = digit1 + digit2 + carry`. The new digit is `sum % 10`
and the new carry is `sum / 10`. After processing all nodes, if carry is still 1,
append a new node with value 1.

```
list1: 2 → 4 → 3   (represents 342)
list2: 5 → 6 → 4   (represents 465)

Step 1: 2+5=7, carry=0 → node 7
Step 2: 4+6=10, carry=1 → node 0
Step 3: 3+4+1=8, carry=0 → node 8

Result: 7 → 0 → 8   (represents 807) ✓
```

---

### Plus One Variant

Only one list, add 1 to the number it represents. The carry can only be 1 or 0, and
only propagates when a digit is 9. The worst case is all nines — every digit becomes 0
and a new head node with value 1 is prepended.

**Stack approach:** Push all nodes onto a stack, pop and add 1 with carry propagation,
update node values in place. If carry remains after the stack is empty, prepend a new
node.

---

### Edge Cases to Watch
- Lists of different lengths — when one runs out, treat missing digits as 0.
- Carry after the last digit — always check if carry remains and append a node if so.
- All nines — every node becomes 0, a new head is needed.
- Single node lists — the formula still works naturally.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Add Two Numbers | O(max(m, n)) | O(max(m, n)) for result list |
| Plus One (stack) | O(n) | O(n) for stack |
| Plus One (reverse) | O(n) | O(1) extra |
