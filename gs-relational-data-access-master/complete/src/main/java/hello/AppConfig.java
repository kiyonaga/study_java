package hello;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
//    @Autowired
//    DataSourceProperties properties;
//    DataSource dataSource;
//
//    @ConfigurationProperties("spring.datasource") // ここを追加
//    @Bean
//    DataSource realDataSource() {
//        DataSourceBuilder factory = DataSourceBuilder
//                .create(this.properties.getClassLoader())
//                .url(this.properties.getUrl())
//                .username(this.properties.getUsername())
//                .password(this.properties.getPassword());
//        this.dataSource = factory.build();
//        return this.dataSource;
//    }
//
//    @Primary
//    @Bean
//    DataSource dataSource() {
//        return new Log4jdbcProxyDataSource(this.dataSource);
//    }

//  @Bean
//  @ConfigurationProperties("spring.datasource")
//  public DataSource dataSource() {
//      return DataSourceBuilder.create().build();
//  }

}