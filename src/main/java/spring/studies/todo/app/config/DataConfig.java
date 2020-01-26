package spring.studies.todo.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
//@EnableJpaRepositories(basePackages = "spring.studies.todo.app.repository")
//@PropertySource("application${spring.profiles.active}.properties")
@PropertySource("application.properties")
public class DataConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSOurce() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getProperty("todotoday.db.driver"));
        dataSourceBuilder.url(env.getProperty("todotoday.db.url"));
        dataSourceBuilder.username(env.getProperty("todotoday.db.username"));
        dataSourceBuilder.password(env.getProperty("todotoday.db.password"));
        return dataSourceBuilder.build();

//        import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName(env.getProperty("todotoday.db.driver"));
//        ds.setUrl(env.getProperty("todotoday.db.url"));
//        ds.setUsername(env.getProperty("todotoday.db.username"));
//        ds.setPassword(env.getProperty("todotoday.db.password"));

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); // TODO What is HibernateJpaVendorAdapter ?

        factoryBean.setDataSource(dataSOurce());
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setPackagesToScan(env.getProperty("todotoday.entity.package"));
        factoryBean.setJpaProperties(getHibernateProperties());

        return factoryBean;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", Objects.requireNonNull(env.getProperty("hibernate.dialect")));
        properties.put("hibernate.implicit_naming_strategy", Objects.requireNonNull(env.getProperty("hibernate.implicit_naming_strategy")));
        properties.put("hibernate.format_sql", Objects.requireNonNull(env.getProperty("hibernate.format_sql")));
        properties.put("hibernate.show_sql", Objects.requireNonNull(env.getProperty("hibernate.show_sql")));
        properties.put("hibernate.hbm2ddl.auto", Objects.requireNonNull(env.getProperty("hibernate.hbm2ddl.auto")));
        return properties;
    }
}
