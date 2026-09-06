// ============================================================
// SEARCHABLE.JAVA
// ============================================================
// TOPICS COVERED:
//
// 1. Interface
// 2. Generic Interface
// 3. Abstraction
// ============================================================

// CONCEPT: Generic Interface
//
// T means the interface can work with different types.
//
// In Inventory:
// Searchable<Product>
//
// Therefore T becomes Product.

public interface Searchable<T> {

    T search(int id);
}