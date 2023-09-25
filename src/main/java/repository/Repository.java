package repository;

import exceptions.ServiceJdbcException;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    List<T> list() throws ServiceJdbcException;

    T byId(Long id);

    void save(T t);

    void delete(Long id);
}
