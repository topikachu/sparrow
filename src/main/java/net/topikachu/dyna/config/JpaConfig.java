package net.topikachu.dyna.config;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 禕 on 2017/1/21.
 */
@Configuration
public class JpaConfig extends JpaBaseConfiguration {
    protected JpaConfig(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider) {
        super(dataSource, properties, jtaTransactionManagerProvider);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put( PersistenceUnitProperties.WEAVING,"false");
        map.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.CREATE_OR_EXTEND);
        map.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_BOTH_GENERATION);
        //map.put(PersistenceUnitProperties.CREATE_JDBC_DDL_FILE, "create.sql");

        return map;
    }
}
