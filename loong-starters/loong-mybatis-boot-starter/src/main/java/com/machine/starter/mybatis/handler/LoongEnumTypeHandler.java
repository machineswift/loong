package com.machine.starter.mybatis.handler;

import com.machine.common.envm.LoongBaseEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(value = {LoongBaseEnum.class})
public class LoongEnumTypeHandler<E extends LoongBaseEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public LoongEnumTypeHandler(Class<E> type) {
        if (null == type) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i, e.getCode());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String columnValue = resultSet.getString(columnName);
        return resultSet.wasNull() ? null : this.getEnum(columnValue);
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String columnValue = resultSet.getString(columnIndex);
        return resultSet.wasNull() ? null : this.getEnum(columnValue);
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String columnValue = callableStatement.getString(columnIndex);
        return callableStatement.wasNull() ? null : this.getEnum(columnValue);
    }

    private E getEnum(String code) {
        for (E e : this.type.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举编码：" + code);
    }

}