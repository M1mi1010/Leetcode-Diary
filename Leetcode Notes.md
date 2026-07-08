# Characters

## Convert char digit to int

```java
// Prevents charAt returning the ASCII value of a number at an index in a string
// E.g. returns 0 not 48
int digit = s.charAt(i) - '0';
```

---

# Arrays

## Copy an array

```java
//This is the best way to copy an array with a given offset
System.arraycopy(src, srcPos, dest, destPos, length);
```
