## Stack Patterns

| 1 | [Valid Parentheses](#valid-parentheses) | Valid Parentheses, Longest Valid Parentheses, Min Remove |

| 2 | [Min Stack Design](#min-stack-design) | Min Stack, Online Stock Span, Max Frequency Stack |

| 3 | [Simulation and Backtracking (Stack)](#simulation-and-backtracking-stack) | Asteroid Collision, Decode String, Simplify Path |

| 4 | [Expression Evaluation](#expression-evaluation) | Basic Calculator I/II/III, Evaluate RPN |

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

## Simulation and Backtracking (Stack)

**When to use:** Problems where you need to process a sequence of operations and
occasionally undo or reverse them — navigating file paths, handling collisions,
decoding nested structures. The stack models "where you've been" so you can step back.

---

### Core Idea

A stack naturally models state that needs to be unwound. Each element represents a
decision or position that may need to be revisited. When a "go back" or "undo"
operation occurs, you pop rather than recompute from scratch.

---

### File Path / Navigation Simulation

Operations move you forward (push a directory) or backward (pop to parent). A `..`
means go up one level — pop the stack. A `.` or empty means stay — ignore. The final
stack represents the canonical path from root to current position.

The stack here isn't about undoing mistakes — it's about maintaining a clean
representation of nested state as you process instructions sequentially.

---

### Collision Simulation

When elements in a sequence interact with their neighbours (like asteroids), a stack
lets you compare the incoming element with the most recent unresolved one. If they
interact, resolve the collision (pop, discard, or both) and repeat. If they don't,
push the new element and move on.

The key insight: you only ever need to compare with the *top* of the stack, because
everything below it was already resolved with previous elements.

---

### Nested / Encoded Structures

When a structure is nested (brackets inside brackets, repeated substrings inside
repeated substrings), a stack lets you save the outer context while you process the
inner one. When the inner context closes, pop and merge it back into the outer.

Each stack frame holds: what was built so far at that level, and any metadata needed
to combine it with the level above (like a repeat count).

**The pattern:**
- Opening symbol → push current state, start fresh
- Closing symbol → pop previous state, combine with what was just built

---

### Edge Cases to Watch
- Multiple consecutive `..` operations that would go above root — clamp at root (empty stack).
- Simultaneous collisions — resolve one at a time with the stack top, not all at once.
- Nested structures with multipliers — the multiplier applies to everything built
  inside that level, not just the last character.
- Empty input or all cancelling operations — the stack may end up empty, which is
  a valid result.

---

### Complexity
| | Time | Space |
|--|------|-------|
| All variants | O(n) | O(n) |

---

## Expression Evaluation

**When to use:** Parsing and computing arithmetic expressions — with or without
parentheses, with operator precedence, in standard or postfix notation.

---

### Core Idea

Expressions are hard to evaluate left-to-right because operators have different
precedence and parentheses change the order. A stack resolves this by deferring
operations until the right moment — when a lower-priority operator is seen, or when
a closing bracket is reached.

---

### Reverse Polish Notation (Postfix)

The simplest variant — no precedence to handle, no parentheses. Operands are pushed
onto a stack. When an operator is seen, pop two operands, apply the operator, push
the result. The final stack contains the answer.

This is the foundation. Understand this before the others.

---

### Infix with Precedence (No Parentheses)

When operators have precedence (`*` and `/` before `+` and `-`), you can't just
apply them left to right. The insight: when you encounter a `+` or `-`, everything
before it at the same precedence level can be resolved. When you see `*` or `/`,
apply it immediately to the top of the stack.

A common approach: treat all numbers as positive/negative by absorbing the sign,
push them onto a stack, and only multiply/divide immediately. Sum the stack at the end.

---

### Infix with Parentheses

Parentheses create nested sub-expressions. When you hit `(`, save the current running
total and sign onto a stack and start fresh. When you hit `)`, pop the saved state
and combine it with the result of the sub-expression just computed.

This is the same nested-context pattern as decode string — the stack holds outer
context while the inner expression is resolved.

---

### Full Expression Evaluation (Precedence + Parentheses)

Combines both challenges. The standard approach uses two stacks — one for operands,
one for operators — and applies operators when a lower-precedence operator is seen or
a `)` is encountered (flushing everything back to the matching `(`).

Alternatively, convert to postfix first, then evaluate postfix.

---

### The Sign and Number Parsing

A consistent source of bugs in these problems:
- Multi-digit numbers must be accumulated character by character (`num = num*10 + digit`)
- Signs apply to the number that follows them, not the one before
- The last number in an expression is never followed by an operator, so you must
  process it after the loop ends

---

### Edge Cases to Watch
- Spaces between tokens — skip them before processing.
- Unary minus (negative numbers at the start or after `(`) — treat the initial sign
  as applying to 0, or track a sign variable.
- Division truncates toward zero in most problems — `int` division in Java/C++ handles
  this, but watch for negative results.
- Nested parentheses — each `(` pushes a new frame, each `)` pops one.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Reverse Polish Notation | O(n) | O(n) |
| Basic Calculator II | O(n) | O(n) |
| Basic Calculator (with parens) | O(n) | O(n) |
