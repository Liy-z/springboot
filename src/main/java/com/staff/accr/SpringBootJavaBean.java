package com.staff.accr;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrClient;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.druid.pool.DruidDataSource;
import com.staff.MyLocaleResolver.MyLocaleResolver;



@EnableAutoConfiguration
@Configuration
@MapperScan("com.staff.mapper")
@SpringBootConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(SolrProperties.class)
public class SpringBootJavaBean  {

	@Autowired
	private Environment env;
	
    //创建数据源
    @Bean
    public DataSource getDataSource() {
    	DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    } 
    
    
    
    @Bean
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
		return new SolrTemplate(client) ;
    	
    }
    
    //创建SqlSessionFactory  bean
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    //添加事物Bean
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
	
  //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
    	WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
    		@Override
    		public void addViewControllers(ViewControllerRegistry registry){
    			//默认是登录页面
    			registry.addViewController("/").setViewName("login");
    			//成功页面
    			registry.addViewController("/index.html").setViewName("login");
    		}
		};
    	return adapter;
    }
    //添加自己的国际化管理
    @Bean
    public LocaleResolver localeresolver(){
    	return new MyLocaleResolver();
    }
	
}
