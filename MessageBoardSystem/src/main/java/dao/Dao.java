package dao;

public interface Dao<T> {
    void save(T t);
    void update(int id, T t);
    void delete(T t);
    T get(int id);
}
