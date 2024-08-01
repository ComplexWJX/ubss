package com.jaxon.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.itany.utils.SetProperties;

public class LifeCycleBean implements InitializingBean, //BeanPostProcessor,
    ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>
{
    private static int i = 0;
    
    private String name;
    
    @Autowired
    private SetProperties properties;
    
    public LifeCycleBean()
    {
        System.out.println(properties);
        System.out.println(i++ + ":construct-method");
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        System.out.println(i++ + "setName");
        this.name = name;
    }
    
    public void initMethod()
    {
        System.out.println(properties);
        System.out.println(i++ + ":LifeCycleBean.initMethod()");
    }
    
    @Override
    public void setApplicationContext(ApplicationContext arg0)
        throws BeansException
    {
        System.out.println(properties);
        System.out.println(i++ + ":ApplicationContextAware");
        
    }
    
    /*@Override
    public Object postProcessAfterInitialization(Object bean, String beanname)
        throws BeansException
    {
        System.out.println(i++ + beanname + ","
            + ":LifeCycleBean.postProcessAfterInitialization()");
        return bean;
    }
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanname)
        throws BeansException
    {
        System.out.println(i++ + beanname + ","
            + ":LifeCycleBean.postProcessBeforeInitialization()");
        return bean;
    }*/
    
    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        System.out.println(properties);
        System.out.println(i++ + ":LifeCycleBean.afterPropertiesSet()");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        System.out.println(properties);
        System.out.println(i++ +":onApplicationEvent");
    }
    
}
