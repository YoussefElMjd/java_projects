package repisotory;

import dto.Dto;
import myException.RepositoryException;

import java.io.IOException;
import java.util.List;

public interface Dao <K,T extends Dto<K>>{
    public void insert(T item) throws IOException, RepositoryException;
    public void delete(K key) throws IOException, RepositoryException;
    public void update(T item) throws IOException, RepositoryException;
    public T get(K key) throws IOException, RepositoryException;
    public boolean contains(K key) throws IOException, RepositoryException;
    public List<T> getAll() throws IOException, RepositoryException;
}
