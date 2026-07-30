## Tree Traversal — DFS Patterns

| 1 | [Recursive Preorder](#recursive-preorder-root--left--right) | Binary Tree Paths, Invert Binary Tree |
| 2 | [Recursive Inorder](#recursive-inorder-left--root--right) | Kth Smallest in BST, Minimum Absolute Difference |
| 3 | [Recursive Postorder](#recursive-postorder-left--right--root) | Max Depth, Diameter, Balanced Tree, Max Path Sum |

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
