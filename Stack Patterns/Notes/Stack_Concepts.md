## Stack Patterns

| 1 | [Valid Parentheses](#valid-parentheses) | Valid Parentheses, Longest Valid Parentheses, Min Remove |

| 2 | [Min Stack Design](#min-stack-design) | Min Stack, Online Stock Span, Max Frequency Stack |

---

## Valid Parentheses

**When to use:** Matching opening and closing brackets, validating nested structures,
any problem where something opened must be closed in the correct order.

---

### Core Idea

A stack is the natural fit for bracket matching because of its LIFO property —
the most recently opened bracket must be the next one closed. When you see an opening
bracket, push it. When you see a closing bracket, check if it matches the top.

---

### Beyond Simple Validation

The basic valid parentheses problem extends into harder variants. Understanding the
core tool for each type:

**Counting unmatched brackets:** Instead of a stack of characters, track counts of
unmatched opens and unmatched closes as you scan. This is enough when you only need
a number, not positions.

**Finding positions to remove:** When you need to know *which* characters to remove
to make a string valid, store indices on the stack rather than characters. After
processing, any index still on the stack is an unmatched open bracket.

**Longest valid substring:** The stack stores indices of unmatched brackets. The
length of the longest valid window is the gap between consecutive unmatched indices.

**Minimum swaps/additions:** Think about what makes a string invalid — unmatched
opens and unmatched closes. The minimum changes needed is a mathematical relationship
between these two counts.

---

### The Index Stack Trick

Storing indices instead of characters unlocks positional information:
- You can mark which characters are invalid
- You can compute substring lengths between unmatched positions
- You can reconstruct the valid string by keeping only unmatched positions

---

### Edge Cases to Watch
- Empty string is valid.
- Odd-length strings can never be valid.
- A string with only closing brackets — the stack will be empty when you try to match,
  which is a mismatch.
- Initialising the index stack with a sentinel value (-1) simplifies length calculations
  for the longest valid substring variant.

---

### Complexity
| | Time | Space |
|--|------|-------|
| All variants | O(n) | O(n) worst case |

---

## Min Stack Design 

**When to use:** When a standard stack isn't enough — you need O(1) access to extra
information (minimum, maximum, frequency) on top of normal push/pop behaviour.

---

### Core Idea

The challenge with augmented stacks is that extra information (like the current minimum)
can change as elements are pushed and popped. You can't just store a single global value
because when the element holding that value is popped, you need to know what it was
*before* that element existed.

The solution is always some form of **auxiliary state that mirrors the main stack** —
maintained in sync so that popping restores not just the element but the associated
metadata.

---

### Maintaining Historical State

Think of it this way: every time you push an element, you're not just adding a value —
you're adding a snapshot of the relevant state *at that moment in time*. When you pop,
you restore the previous snapshot automatically.

This is the key mental model for all stack design problems. Ask: "what information do
I need to restore when this element is popped?"

---

### Monotonic Stacks

Some stack design problems use a **monotonic stack** — a stack that maintains elements
in sorted order (either always increasing or always decreasing) by popping elements that
violate the order before pushing the new one.

This is powerful because:
- Popped elements are ones that can never be the answer going forward
- The stack always represents the set of "candidates" that could still matter
- Each element is pushed and popped at most once → O(n) total

Monotonic stacks are the right tool when you need, for each element, the nearest
element that is greater/smaller than it — either to the left or right.

---

### Frequency-Based Stacks

When the priority for popping is frequency rather than recency, the structure becomes
more complex. The insight is to bucket elements by their frequency level, where each
bucket is itself a stack (preserving recency within a frequency tier).

The current maximum frequency is tracked separately and decremented when its bucket
empties after a pop.

---

### Common Thread Across All Variants

| What you need O(1) access to | Technique |
|---|---|
| Current minimum/maximum | Parallel stack storing running min/max |
| Nearest greater/smaller element | Monotonic stack |
| Most frequent, then most recent | Per-frequency stacks + freq map |

---

### Edge Cases to Watch
- Duplicate values in min stack — duplicates of the current minimum still need to be
  tracked on the auxiliary stack, or the minimum won't restore correctly after pop.
- Monotonic stack span accumulation — when popping, spans must carry forward so
  historical information isn't lost.
- Frequency stack on pop — when the top frequency bucket empties, max frequency must
  decrement immediately.

---

### Complexity
| | Push | Pop | Special query |
|--|------|-----|--------------|
| Min/Max Stack | O(1) | O(1) | O(1) |
| Monotonic Stack | O(1) amortised | O(1) amortised | O(1) |
| Frequency Stack | O(1) | O(1) | O(1) |
