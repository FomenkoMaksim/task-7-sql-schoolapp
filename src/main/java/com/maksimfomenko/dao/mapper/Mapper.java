package com.maksimfomenko.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
    T apply(ResultSet rs) throws SQLException;
}
