package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.BoardDAOImpl;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.CommentDAOImpl;
import com.myweb.www.service.BoardService;
import com.myweb.www.service.BoardServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;



@Configuration
@ComponentScan(basePackages = {"com.myweb.www.service"})
@MapperScan(basePackages = {"com.myweb.www.repository"})
public class RootConfig {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb?serverTimezone=Asia/Seoul");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("jimmywin12");
		hikariConfig.setMinimumIdle(5);
		
		hikariConfig.setConnectionTestQuery("SELECT now()");
		hikariConfig.setPoolName("springHikariCP");
		
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "200");
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception {
		return sqlSessionFactory().openSession();
	}
	
	@Bean
	public BoardDAO bdao() {
		return new BoardDAOImpl();
	}
	
	@Bean
	public BoardVO bvo() {
		return new BoardVO();
	}
	
	@Bean
	public BoardService bsv() {
		return new BoardServiceImpl();
	}
	
	@Bean
	public CommentDAO cdao() {
		return new CommentDAOImpl();
	}
	
	
}
