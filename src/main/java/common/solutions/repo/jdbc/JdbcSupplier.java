package common.solutions.repo.jdbc;

public interface JdbcSupplier<T> {
    T get() throws Exception;
}
