# Graph Patterns

## Table of Contents

| # | Pattern | Key Problems |
|---|---------|--------------|
| 1 | [Connected Components / Island Counting](#connected-components--island-counting) | Number of Islands, Max Area of Island, Flood Fill |

---

## Connected Components / Island Counting

**When to use:** Any problem involving a grid or graph where you need to find, count,
measure, or label separate groups of connected cells/nodes. Look for words like
"islands", "regions", "clusters", "groups", or "connected".

---

### Core Idea

A connected component is a blob where every cell is reachable from every other cell
in the blob, and you can't reach any other blob without crossing a gap. The approach
is always the same — scan every cell, and whenever you find an unvisited cell that
belongs to a group, explore the entire group before moving on.

```
Grid:               After DFS from (0,0):
1 1 0 0 1           0 0 0 0 1   ← island 2
1 0 0 0 0    →      0 0 0 0 0
0 0 1 1 0           0 0 1 1 0   ← island 3
0 0 1 0 0           0 0 1 0 0

3 separate islands found ✓
```

---

### The Template

Every connected components problem follows this exact structure:

```
for each cell in the grid:
    if cell is unvisited and part of a group:
        count++              ← new component found
        explore(cell)        ← mark everything connected

explore(cell):
    if out of bounds or already visited or not part of group:
        return
    mark cell as visited
    explore(up, down, left, right)
```

The explore function is either recursive DFS or iterative BFS — both produce the same
result, choose based on preference or stack overflow constraints.

---

### DFS vs BFS for Grid Problems

**DFS (recursive):** Shorter code, dives deep into one path before backtracking.
Risk of stack overflow on very large grids due to deep recursion.

**BFS (queue):** Explores all neighbours at current distance before going deeper.
Safer for large grids, also gives shortest path if needed.

For pure island counting and area problems, DFS is usually simpler to write.

---

### Marking Visited

The most important implementation detail. You must mark cells as visited before
exploring their neighbours — otherwise you'll revisit cells and loop infinitely.

Two options:
- **Modify in place** — set the cell to 0 (or a sentinel value) to mark it visited.
  Destructive but O(1) space.
- **Separate visited array** — keep a boolean grid. Non-destructive, O(m×n) space.

Most grid problems allow in-place modification. If the original grid must be preserved,
use a visited array.

---

### 4-directional vs 8-directional

Most problems use 4-directional connectivity (up, down, left, right). Some use
8-directional (includes diagonals). Always check the problem statement — this changes
which neighbours you explore.

```java
// 4-directional
int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

// 8-directional (add diagonals)
int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
```

---

### Common Variations

**Count islands:** Count how many times you initiate a new DFS — each initiation is
a new component.

**Max area:** Track the size of each DFS (count cells visited) and return the maximum.

**Flood fill:** Instead of counting, recolour every cell in a component to a new value.
Same DFS structure, just write instead of count.

**Count sub-islands:** Two grids — an island in grid2 is a sub-island only if every
cell of it is also land in grid1. DFS both grids simultaneously.

---

### General Graphs (Non-Grid)

The same pattern applies to adjacency list graphs — instead of exploring 4 directions,
you iterate over the neighbour list. Instead of a 2D visited array, use a Set or
boolean array indexed by node number.

```
for each node in graph:
    if not visited:
        count++
        dfs(node)

dfs(node):
    mark node visited
    for each neighbour of node:
        if not visited: dfs(neighbour)
```

---

### Edge Cases to Watch
- Empty grid — return 0 immediately
- All water / all land — both handled correctly by the template
- Single cell grid — one island if it's land, zero if water
- Grid with only border cells — bounds checking prevents out-of-bounds access
- Already visited cells — always check before exploring to avoid infinite recursion

---

### Complexity
| | Time | Space |
|--|------|-------|
| Grid DFS/BFS | O(m × n) | O(m × n) recursion stack / queue |
| Graph DFS/BFS | O(V + E) | O(V) visited set + stack/queue |

Each cell or node is visited at most once across the entire algorithm — the nested
loops don't multiply the DFS cost.
