package com.maksimfomenko.model;

public interface Entity<K> {
    K getId();

    void setId(K id);
}
