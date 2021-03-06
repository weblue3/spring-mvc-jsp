package kr.co.softcampus.config;

import kr.co.softcampus.beans.UserBean;
import kr.co.softcampus.interceptor.CheckLoginInterceptor;
import kr.co.softcampus.interceptor.CheckWriterInterceptor;
import kr.co.softcampus.interceptor.TopMenuInterceptor;
import kr.co.softcampus.mapper.BoardMapper;
import kr.co.softcampus.mapper.TopMenuMapper;
import kr.co.softcampus.mapper.UserMapper;
import kr.co.softcampus.service.BoardService;
import kr.co.softcampus.service.TopMenuService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("kr.co.softcampus.controller")
@ComponentScan("kr.co.softcampus.service")
@ComponentScan("kr.co.softcampus.dao")
@PropertySource(value = "/WEB-INF/properties/db.properties", encoding = "UTF-8")
public class ServletAppContext implements WebMvcConfigurer {

    @Value("${db.classname}")
    private String db_classname;

    @Value("${db.url}")
    private String db_url;

    @Value("${db.username}")
    private String db_username;

    @Value("${db.password}")
    private String db_password;

    private TopMenuService topMenuService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    private BoardService boardService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setTopMenuService(TopMenuService topMenuService) {
        this.topMenuService = topMenuService;
    }

    //-- Controller의 메서드가 반환하는 jsp의 이름 앞뒤에 경로와 확장자를 붙혀주도록 설정한다.
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");

        CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
        InterceptorRegistration reg2 = registry.addInterceptor(checkLoginInterceptor);
        reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
        reg2.excludePathPatterns("/board/main");

        CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService);
        InterceptorRegistration reg3 = registry.addInterceptor(checkWriterInterceptor);
        reg3.addPathPatterns("/board/modify", "/board/delete");
    }

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(db_classname);
        dataSource.setUrl(db_url);
        dataSource.setUsername(db_username);
        dataSource.setPassword(db_password);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory sqlSessionFactory)throws Exception{
        MapperFactoryBean<BoardMapper> mapperFactoryBean = new MapperFactoryBean<>(BoardMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory sqlSessionFactory)throws Exception{
        MapperFactoryBean<TopMenuMapper> mapperFactoryBean = new MapperFactoryBean<>(TopMenuMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }

    @Bean
    public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory sqlSessionFactory)throws Exception{
        MapperFactoryBean<UserMapper> mapperFactoryBean = new MapperFactoryBean<>(UserMapper.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return mapperFactoryBean;
    }

    //-- PropertySource 에 등록된 프로퍼티들을 @Value("${property.name}") 형태로 사용하려면
    //-- PropertySourcesPlaceholderConfigurer 등록이 필요하다.
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("/WEB-INF/properties/error_message");
        return messageSource;
    }

    //-- 파일 업로드
    @Bean
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

}
