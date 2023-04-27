package com.sashafilth.tree;

public interface Tree<T extends Comparable<T>> {

    Tree<T> insert(T data);

    void delete(T data);

    T find(T data);

    void traverse();

}
