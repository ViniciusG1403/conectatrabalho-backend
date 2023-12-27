package core.shared;


import jakarta.ws.rs.NotFoundException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.ValueExtractor;
import org.hibernate.type.descriptor.converter.internal.NamedEnumValueConverter;
import org.hibernate.type.descriptor.java.EnumJavaType;
import org.hibernate.type.descriptor.java.JavaType;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.hibernate.type.descriptor.jdbc.JdbcType;
import org.hibernate.type.descriptor.jdbc.ObjectJdbcType;
import org.hibernate.type.spi.TypeConfiguration;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Properties;

public class PostgreSQLEnumType implements DynamicParameterizedType, UserType<Enum> {
    private static final String H2_DIALECT = "io.quarkus.hibernate.orm.runtime.dialect.QuarkusH2Dialect";

    private Class enumClass;

    private NamedEnumValueConverter enumValueConverter;

    private ValueExtractor jdbcValueExtractor;

    @Override
    public void setParameterValues(final Properties properties) {
        final JdbcType jdbcType = new ObjectJdbcType(getSqlType());
        final JavaType javaType = new ObjectJavaType();
        final TypeConfiguration typeConfiguration = new TypeConfiguration();
        final String className = properties.getProperty(DynamicParameterizedType.RETURNED_CLASS);
        jdbcValueExtractor = jdbcType.getExtractor(javaType);
        enumClass = getClass(className);

        final EnumJavaType enumJavaType = (EnumJavaType) typeConfiguration.getJavaTypeRegistry()
                .getDescriptor(enumClass);
        enumValueConverter = new NamedEnumValueConverter(enumJavaType, jdbcType,
                typeConfiguration.getJavaTypeRegistry().getDescriptor(String.class));
    }

    @Override
    public int getSqlType() {
        return SqlTypes.VARCHAR;
    }

    @Override
    public Class<Enum> returnedClass() {
        return enumClass;
    }

    @Override
    public boolean equals(final Enum enumOne, final Enum enumTwo) {
        return Objects.equals(enumOne, enumTwo);
    }

    @Override
    public int hashCode(final Enum anEnum) {
        return Objects.nonNull(anEnum) ? anEnum.hashCode() : 0;
    }

    @Override
    public Enum nullSafeGet(final ResultSet resultSet, final int index,
                            final SharedSessionContractImplementor session, final Object owner) throws SQLException {
        final String enumValue = (String) jdbcValueExtractor.extract(resultSet, index, session);
        return enumValueConverter.toDomainValue(enumValue);
    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement, final Enum value,
                            final int index, final SharedSessionContractImplementor session) throws SQLException {
        final String enumName = enumValueConverter.toRelationalValue(value);

        if (Objects.isNull(enumName)) {
            preparedStatement.setNull(index, Types.OTHER);
            return;
        }

        if (H2_DIALECT.equals(session.getFactory().getJdbcServices().getDialect().toString())) {
            preparedStatement.setObject(index, enumName, Types.VARCHAR);
            return;
        }

        preparedStatement.setObject(index, enumName, Types.OTHER);
    }

    @Override
    public Enum deepCopy(final Enum anEnum) {
        return anEnum;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(final Enum value) {
        return value;
    }

    @Override
    public Enum assemble(final Serializable cached, final Object owner) {
        return (Enum) cached;
    }

    @SuppressWarnings("unchecked")
    private <T> Class<T> getClass(String className) {
        try {
            return (Class<T>) Class.forName(className, false,
                    Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            throw new NotFoundException("Não foi possível localizar a classe do enum: " + className);
        }
    }

}
