package com.maksimfomenko.dao.implement;

import com.maksimfomenko.dao.AbstractCrudDao;
import com.maksimfomenko.dao.GroupDao;
import com.maksimfomenko.dao.mapper.GroupMapper;
import com.maksimfomenko.model.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDaoImpl extends AbstractCrudDao<Group, Long> implements GroupDao {

    private static final String SELECT_ONE = "SELECT * FROM groups WHERE group_id = ?";
    public static final String FIND_BY_NAME = "SELECT * FROM groups WHERE group_name = ?";
    public static final String SELECT_ALL = "SELECT * FROM groups";
    public static final String INSERT_ONE = "INSERT INTO groups (group_name) VALUES (?)";
    public static final String UPDATE = "UPDATE groups SET group_name = ? WHERE group_id = ?";
    public static final String DELETE_ONE = "DELETE FROM groups WHERE group_id = ?";

    GroupMapper mapper;

    public GroupDaoImpl() {
        this.mapper = new GroupMapper();
    }

    @Override
    public Optional<Group> findById(Connection connection, Long id) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(SELECT_ONE)) {
            st.setLong(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.apply(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    public List<Group> findAll(Connection connection) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = st.executeQuery()) {
                List<Group> groups = new ArrayList<>();
                while (rs.next()) {
                    groups.add(mapper.apply(rs));
                }
                return groups;
            }
        }
    }

    @Override
    public void deleteById(Connection connection, Long id) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(DELETE_ONE)) {
            st.setLong(1, id);
            if (st.executeUpdate() != 1) {
                throw new SQLException("Unable to delete group (id = " + id + ")");
            }
        }
    }

    @Override
    public Optional<Group> findByName(Connection connection, String name) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(FIND_BY_NAME)) {
            st.setString(1, name);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapper.apply(rs));
                } else {
                    return Optional.empty();
                }
            }
        }
    }

    @Override
    protected Group create(Connection connection, Group entity) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(INSERT_ONE, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, entity.getName());
            if (st.executeUpdate() != 1) {
                throw new SQLException("Unable to create group " + entity);
            }
            try (ResultSet rs = st.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new SQLException("Unable to retrieve id");
                }
                Long id = rs.getLong(1);
                return new Group(id, entity.getName());
            }
        }
    }

    @Override
    protected Group update(Connection connection, Group entity) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(UPDATE)) {
            st.setString(1, entity.getName());
            st.setLong(2, entity.getId());

            if (st.executeUpdate() != 1) {
                throw new SQLException("Unable to update group " + entity);
            }
            return new Group(entity.getId(), entity.getName());
        }
    }
}
