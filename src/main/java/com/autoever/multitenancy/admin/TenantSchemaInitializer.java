package com.autoever.multitenancy.admin;

import com.autoever.multitenancy.config.SchemaBuilder;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/***
 * 이 클래스는 데이터베이스 스키마를 생성한다.
 * 다만 SQL Injection이 발생할 수 있으므로 사용을 주의해서 해야한다.
 */
@Component
@RequiredArgsConstructor
public class TenantSchemaInitializer {

    private final DataSource dataSource;
    private final SchemaBuilder schemaBuilder;

    /***
     * 이 메서드는 SQL Injection을 발생 시킬 수 있는 메서드를 사용한다.
     * 즉 입력값에 문자열을 받을 경우 SQL Injection 이 발생할 위험이 있으므로 수정에 주의하라.
     * @param tenantId
     */
    public void init(Long tenantId) {
        String tenantSchema = schemaBuilder.getTenantSchema(tenantId.toString());
        Connection connection = getConnectionBy(tenantSchema);
        ResourceDatabasePopulator databasePoupulator = getDatabasePoupulator();
        databasePoupulator.populate(connection);
    }

    private Connection getConnectionBy(String tenantSchema) {
        try {
            Connection connection = dataSource.getConnection();
            createSchema(tenantSchema, connection);
            connection.setSchema(tenantSchema);
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("SQL Exception...", e);
        }
    }

    /***
     * 이 메서드는 SQL Injection이 발생할 수 있다.
     * tenantSchema 입력에 따라 위험할 수 있으므로 제한된 클라이언트에만 사용할 것을 추천한다.
     * @param tenantSchema
     * @param connection
     * @throws SQLException
     */
    private void createSchema(String tenantSchema, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("create schema " + tenantSchema);
        statement.execute();
    }

    private ResourceDatabasePopulator getDatabasePoupulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("schema_per_tenant.sql"));
        return databasePopulator;
    }
}
