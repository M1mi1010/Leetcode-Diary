## Tree Traversal — DFS Patterns

| 1 | [Recursive Preorder](#recursive-preorder-root--left--right) | Binary Tree Paths, Invert Binary Tree |

| 2 | [Recursive Inorder](#recursive-inorder-left--root--right) | Kth Smallest in BST, Minimum Absolute Difference |

| 3 | [Recursive Postorder](#recursive-postorder-left--right--root) | Max Depth, Diameter, Balanced Tree, Max Path Sum |

| 4 | [Lowest Common Ancestor](#lowest-common-ancestor) | LCA of Binary Tree, LCA of BST |

| 5 | [Serialization and Deserialization](#serialization-and-deserialization) | Subtree of Another Tree, Find Duplicate Subtrees, Serialize and Deserialize |

---

### Recursive Preorder (Root → Left → Right)

**When to use:** Processing a node before its children, building paths from root to leaf,
cloning or inverting trees, comparing two trees. The parent's value is needed before
you can process its children.

**Key Idea:** Visit and act on the current node first, then recurse left, then right.
Information flows **downward** — you pass context from parent to children.

```
      1
     / \
    2   3
   / \
  4   5

Preorder: 1 → 2 → 4 → 5 → 3
```

**Common uses:**
- Building root-to-leaf path strings — append current node before recursing.
- Inverting a tree — swap children before recursing into them.
- Serialising a tree — record node before its children so structure is preserved.

**Edge cases:** Check for null before processing. Leaf nodes (both children null) often
need special handling — e.g. recording a completed path.

---

### Recursive Inorder (Left → Root → Right)

**When to use:** Problems on Binary Search Trees where sorted order matters, finding
kth smallest/largest, minimum difference between nodes. Inorder traversal of a BST
visits nodes in ascending order.

**Key Idea:** Recurse left fully, then act on current node, then recurse right.
Information is collected **in sorted order** for BSTs.

```
      4
     / \
    2   6
   / \
  1   3

Inorder: 1 → 2 → 3 → 4 → 6
```

**Common uses:**
- Collecting BST values in sorted order.
- Kth smallest — count nodes as you visit them, stop at k.
- Minimum absolute difference — compare each node to the previously visited node.

**Edge cases:** Empty tree returns empty list. For early-exit problems (kth smallest),
use a counter and stop recursing once the answer is found.

---

### Recursive Postorder (Left → Right → Root)

**When to use:** When a node's answer depends on results from both its children first.
Information flows upward — children compute and return values, the parent combines them.

---

### Core Idea

You cannot process a node until both subtrees have reported back. This makes postorder
the natural fit for anything involving height, depth, size, or any property that is
defined recursively in terms of subtree properties.

```
      1
     / \
    2   3
   / \
  4   5

Postorder: 4 → 5 → 2 → 3 → 1
```

---

### The Return Value vs The Answer

This is the most important concept in postorder problems. What a function **returns**
upward and what the **answer** to the problem is are often two different things.

- **Return value:** Something the parent needs to make its own calculation. Usually
  height, size, or a state.
- **Answer:** Often tracked separately as a global variable updated at each node.

Example: for diameter, you return height upward but record path length as the answer.
For max path sum, you return the best single-branch extension but record the best
peak as the answer.

Get comfortable with recursive functions that both return a value AND update something
on the side.

---

### Height is the Foundation

Almost every postorder problem either computes height directly or uses it as a
building block. Make sure you can write height from memory:

```
height(node):
    if node is null: return 0
    return 1 + max(height(left), height(right))
```

Problems like diameter, balance checking, and path sums all reduce to variations of
this pattern where you do extra work at each node using the left and right heights.

---

### Returning Multiple Values

Some postorder problems require a node to report back more than one thing. Rather than
a single int, your recursive function might return a pair or object. For example, a
node might need to tell its parent both its height AND whether its subtree is valid.

This avoids redundant passes — you compute everything in one traversal.

---

### Tree DP

Some postorder problems have the structure of dynamic programming. Each node has
multiple possible states, and the optimal choice at a node depends on what its
children chose. The recursive function returns the best outcome for each possible
state, and the parent picks the combination that gives the best overall result.

The key insight: you return ALL possible outcomes upward, not just one. The parent
then selects.

---

### Deletion and Structural Changes

When a problem involves deleting nodes or restructuring the tree, postorder ensures
you handle children before parents. This matters because:

- Deleting a parent before its children loses references to those children.
- A node can only be correctly removed once you know what its subtree looks like
  after any child deletions.
- After deletion, a child may become the root of a new independent tree.

---

### When the Answer Doesn't Pass Through the Root

Some problems ask for a maximum or longest path that can start and end anywhere. The
answer might involve a node deep in the tree acting as the "peak" of the path. At
every node, compute what the best result would be if that node were the peak, update
a global maximum, then return only what's useful to the parent (usually one branch,
not both).

---

### Edge Cases to Watch
- Null nodes must return a base value that makes sense mathematically — 0 for height,
  a very small number for max sum, etc.
- Negative values — sometimes a subtree's contribution makes things worse. Decide
  whether to clamp to 0 or allow negatives based on what the problem is asking.
- The answer is often not the return value of the root call — check whether you need
  a separate variable updated throughout the recursion.
- Single node trees — your base case and main logic should both handle this correctly.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Balanced tree | O(n) | O(log n) call stack |
| Skewed tree | O(n) | O(n) call stack |

Every node is visited exactly once regardless of what computation happens at each node.

## Lowest Common Ancestor

**When to use:** Finding where two nodes first share a common ancestor, any problem
asking "where do these two paths converge from the root downward".

---

### Key Idea

The LCA of two nodes p and q is the deepest node in the tree that has both p and q
as descendants (a node is a descendant of itself). You're looking for the point where
the paths from the root to p and root to q diverge.

---

### The Core Insight

At any node, there are three possible situations:
- Both p and q are in the left subtree → LCA is somewhere in the left subtree
- Both p and q are in the right subtree → LCA is somewhere in the right subtree
- p and q are on opposite sides (or the current node is p or q) → this node IS the LCA

This is naturally postorder — you need to know what each subtree contains before
deciding whether the current node is the answer.

---

### Binary Tree vs BST

**General Binary Tree:** You have no information about node placement, so you must
search both subtrees. Each recursive call reports whether it found p, q, or neither.
When a node gets "found p" from one side and "found q" from the other, it is the LCA.

**Binary Search Tree:** The ordered structure tells you exactly which subtree to search
without looking at both. If both p and q are less than the current node, go left. If
both are greater, go right. The first node where they diverge (one goes left, one goes
right) is the LCA. This makes BST LCA much simpler — no need to search both sides.

---

### What to Return Upward

For the general tree, your recursive function needs to communicate which targets it
found. Think carefully about what return value lets a parent node know:
- Neither target was found below
- One of the targets was found below
- The LCA itself was found below (so stop searching)

---

### Edge Cases to Watch
- A node can be its own ancestor — if p is an ancestor of q, then p is the LCA.
- Both nodes are guaranteed to exist in the tree (for most problem variants) — if not,
  you need extra checks.
- Root is always a valid LCA (worst case both nodes are in different halves).

---

### Complexity
| | Time | Space |
|--|------|-------|
| Binary Tree | O(n) | O(h) call stack |
| BST | O(h) | O(h) call stack |

Where h = height. For balanced trees O(log n), skewed trees O(n).

---

## Serialization and Deserialization

**When to use:** Converting a tree to a string/array representation and reconstructing
it exactly, detecting structural patterns within trees, comparing subtrees.

---

### Key Idea

Serialization encodes a tree into a linear format (string or array). Deserialization
reconstructs the exact original tree from that format. The challenge is preserving
enough structural information that reconstruction is unambiguous.

---

### Why Traversal Order Alone Isn't Enough

A sequence of node values without null markers is ambiguous — multiple different trees
can produce the same inorder or preorder sequence. You must encode null children
explicitly so the structure is fully captured.

---

### Preorder is Natural for Serialization

Preorder (root first) is the most common choice because during deserialization, you
read the root first and can immediately reconstruct the tree top-down. Null markers
tell you when a subtree ends.

```
Tree:        1
            / \
           2   3
              / \
             4   5

Serialized: "1,2,null,null,3,4,null,null,5,null,null"
```

Reading left to right, you always know exactly what to build next.

---

### Deserialization Mindset

Think of the serialized string as a stream of values. A pointer or index tracks your
position. Each recursive call consumes one value — if it's null, return null. If it's
a number, create a node, then recursively build its left child (consuming more values),
then its right child.

---

### Subtree Detection

To check if one tree is a subtree of another, you can serialize both trees and check
if one string contains the other as a substring. The key — add delimiters around
values so "12" doesn't match "1" and "2" as separate nodes.

---

### Duplicate Subtrees

To find duplicate subtrees, serialize every subtree (postorder works naturally here —
children are serialized before the root) and use a frequency map. A subtree that has
been seen before is a duplicate. The serialization acts as a structural fingerprint.

---

### Edge Cases to Watch
- Empty trees must be encoded — a missing null marker breaks deserialization.
- Delimiter choice matters — without separators, multi-digit numbers become ambiguous.
- For subtree matching via string contains, wrap values in delimiters to prevent
  partial matches.
- Duplicate subtrees: a single null node appears many times — decide whether to count
  it as a duplicate or ignore it.

---

### Complexity
| | Time | Space |
|--|------|-------|
| Serialize | O(n) | O(n) |
| Deserialize | O(n) | O(n) |
| Subtree check (string) | O(m·n) naive | O(m+n) |
| Duplicate subtrees | O(n²) worst case for string keys | O(n) |
