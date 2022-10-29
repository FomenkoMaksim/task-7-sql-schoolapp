package com.maksimfomenko.dao.mapper;

import com.maksimfomenko.model.Group;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.maksimfomenko.model.Group.GROUP_ID;
import static com.maksimfomenko.model.Group.GROUP_NAME;

public class GroupMapper implements Mapper<Group> {
    @Override
    public Group apply(ResultSet rs) throws SQLException {
        return new Group(rs.getLong(GROUP_ID), rs.getString(GROUP_NAME));
    }
}
