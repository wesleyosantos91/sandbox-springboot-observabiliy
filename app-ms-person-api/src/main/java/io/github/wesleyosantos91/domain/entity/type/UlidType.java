package io.github.wesleyosantos91.domain.entity.type;

import de.huxhorn.sulky.ulid.ULID;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.EnhancedUserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class UlidType implements EnhancedUserType<ULID.Value> {


    @Override
    public String toSqlLiteral(ULID.Value value) {
        return value.toString();
    }

    @Override
    public String toString(ULID.Value value) throws HibernateException {
        return value.toString();
    }

    @Override
    public ULID.Value fromStringValue(CharSequence charSequence) throws HibernateException {
        return ULID.parseULID(charSequence.toString());
    }

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<ULID.Value> returnedClass() {
        return ULID.Value.class;
    }

    @Override
    public boolean equals(ULID.Value value, ULID.Value j1) {
        return false;
    }

    @Override
    public int hashCode(ULID.Value value) {
        return value.hashCode();
    }

    @Override
    public ULID.Value nullSafeGet(ResultSet resultSet, int position, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        String ulidString = resultSet != null ? resultSet.getString(position) : null;
        return ulidString != null ? ULID.parseULID(ulidString) : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, ULID.Value value, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (value == null) {
            preparedStatement.setNull(i, Types.VARCHAR); // Using VARCHAR for string representation of ULID
        } else {
            String ulidString = value.toString();
            if (ulidString.length() > 26) { // Check for potential truncation issues
                throw new IllegalArgumentException("ULID string exceeds maximum length for database column");
            }
            preparedStatement.setString(i, ulidString);
        }
    }

    @Override
    public ULID.Value deepCopy(ULID.Value value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(ULID.Value value) {
        return value;
    }

    @Override
    public ULID.Value assemble(Serializable serializable, Object o) {
        if (serializable instanceof ULID.Value) {
            return (ULID.Value) serializable;
        } else {
            return null;
        }
    }
}