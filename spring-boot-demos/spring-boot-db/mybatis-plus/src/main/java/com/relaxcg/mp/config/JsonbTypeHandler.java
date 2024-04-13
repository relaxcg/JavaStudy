package com.relaxcg.mp.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author relaxcg
 * @date 2024/4/12 9:31
 */
@MappedTypes({Object.class})
public class JsonbTypeHandler extends BaseTypeHandler<Object> {

    private final ObjectMapper objectMapper;

    private static final PGobject JSON_OBJECT = new PGobject();


    public JsonbTypeHandler() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("setNonNullParameter");
        if (ps != null) {
            JSON_OBJECT.setType("jsonb");
            try {
                JSON_OBJECT.setValue(objectMapper.writeValueAsString(parameter));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            ps.setObject(i, JSON_OBJECT);
        }
    }


    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return deserialize(json);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return deserialize(json);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return deserialize(json);
    }

    private Object deserialize(String json) throws SQLException {
        System.out.println("deserialize");
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(json, Object.class);
        } catch (IOException e) {
            throw new SQLException("Error deserializing JSONB value", e);
        }
    }
}
