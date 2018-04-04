package auto_matching_search_app;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication(scanBasePackages={"auto_matching_search_app.bookmarks", "auto_matching_search_app.home",
		"auto_matching_search_app.keywords", "auto_matching_search_app.ranking", "auto_matching_search_app.user"})
@MapperScan(basePackages={"auto_matching_search_app.bookmarks.persistence", "auto_matching_search_app.home.persistence",
		"auto_matching_search_app.keywords.persistence", "auto_matching_search_app.ranking.persistence", "auto_matching_search_app.user.persistence"})
public class SimulatedProjectNomotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulatedProjectNomotoApplication.class, args);
	}
	
	/**
	 * MyBatisの設定
	 * @param dataSource
	 * @return sessionFactory.getObject()
	 * @throws Exception
	 */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // コンフィグファイルの読み込み
        sessionFactory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));

        return sessionFactory.getObject();
    }
}
