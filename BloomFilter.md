# 🧠 What is a Bloom Filter?

A **Bloom Filter** is a **probabilistic data structure** used to test whether an element is a member of a set.

- ✅ **Fast and memory-efficient**.
- ❌ **False positives are possible** (might say something is present when it's not).
- ✅ **No false negatives** (if it says "not present", it’s definitely not present).

---

## ⚙️ How it Works

1. You have a **bit array** of fixed size (e.g., 10,000 bits).
2. You use **k different hash functions** (or same function with different seeds).
3. When adding an element:
   - Compute `k` hash values.
   - Set the corresponding bits to `true`.

4. When checking an element:
   - Re-compute the same `k` hashes.
   - If **any one of the bits is `false`** → definitely **not present**.
   - If **all bits are `true`** → **might be present**.

---

## 🔍 Code Breakdown

### ✅ 1. Constructor

```java
public CustomBloomFilter(int size, int[] seeds)
```

### ✅ 2. Hash Function

```java
private int getHash(String value, int seed)
```
Example:
For username "adhish" and seed 31:
result = 31 * (31 * (31 * (0 + 'a') + 'd') + 'h') + ...
Then:
(size - 1) & result
This ensures result fits within the bounds of the bit array.

🔒 Why Bitwise AND (&)?
Faster than modulo (%) for powers of 2:
& (1024 - 1) ≈ % 1024

### ✅ 3.add() Method

```java
public void add(String value)
```

### ✅ 4.mightContain() Method

```java
public boolean mightContain(String value)
```

- ##🧪 Example Walkthrough
Let's assume:
seeds = {7, 11, 13}

bitSet size = 10000

Adding "adhish":
```
hash("adhish", 7)  → index = 103
hash("adhish", 11) → index = 87
hash("adhish", 13) → index = 541
```
Set bits at 103, 87, and 541 to true.
