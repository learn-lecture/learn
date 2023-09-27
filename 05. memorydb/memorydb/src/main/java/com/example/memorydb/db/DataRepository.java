package com.example.memorydb.db;

import java.util.List;
import java.util.Optional;

public interface DataRepository<T, ID> extends Repository<T, ID> {
    // Create, Update
    T save(T data);

    // Read
    Optional<T> findById(ID id);
    List<T> findAll();

    // Delete
    void delete(ID id);
}
