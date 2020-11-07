package dao;

public interface Dao<T> {
    void save(T t);
    void update(T t);
    boolean delete(int id);
}
