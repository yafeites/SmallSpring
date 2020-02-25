# SmallSpring

基本功能完成，学习使用

## 简介

SmallSpring是通过对Spring源码学习后，对IOC和AOP功能模块的构建，其中部分类和函数实现和Spring相同，方便学习和后续功能增强（减少重构），下面是具体介绍

## **XML配置**

### IOC

```
<beans>

        <!--     IOC   -->
        
    <bean id="1" class="smallSpring.test.Person">
        <property name="age" value="24">
        </property>
    </bean>
    
    <bean id="2" class="smallSpring.test.Student">
        <property name="teacher" ref="3"></property>
    </bean>
    
    <bean id="3" class="smallSpring.test.Teacher">
        <property name="student" ref="2"></property>
    </bean>
    
    <bean id="4" class="smallSpring.test.father" autowire="byType">
    </bean>
    
    <bean id="5" class="smallSpring.test.father " scope="prototype">
    </bean>

    
</beans>
```

- bean：所有配置均在Bean标签中实现
- id：Bean的唯一标识符 ，必须配置
- class：每个Bean对应的类的全名
- property：Bean中包含的参数在这里配置
- name：Bean属性在类中的名称
- value：Bean属性具体数值（基础类型）
- ref：如果属性是引用类型，则可以通过id来引用其他的Bean
- autowire：包含（byType：基于bean类型的自动配置，byName 基于Bean 名称的自动配置）
- scope：包含（protoytpe：原型模式，每次创建一个不同的Bean，Singleton：单例模式，在Spring上下文中只保留一个类的实现，默认情况是单例模式）



### AOP

    <beans>
               <!--    AOP  -->
    <bean id="boss" class="smallSpring.test.Boss"></bean>
    
    <bean id="advice" class="smallSpring.aop.advice.Impl.MethodBeforeAdviceImpl"></bean>
    
    <bean id="advisor" class="smallSpring.aop.advisor.DefaultPointCutAdvisor">
        <property name="advice" ref="advice"></property>
    </bean>
    
    <bean id="proxyCreator" class="smallSpring.aop.autoproxycreator.AdvisorAutoProxyCreator">
        <property name="interceptorName" value="advisor"></property>
        <property name="proxyCls" value="smallSpring.test.Boss"></property>
    </bean>
    
    </beans>
实现自动代理需要实现以下操作

- 被代理目标的配置，如id =boss

- 增强通知的Bean配置，如id=advice

- 增强通知的载体Bean配置 ，如 id=advisor

- 自动配置类的Bean配置,如id=proxyCreator（参数interceptorName配置增强通知载体的Bean id名，参数proxyCls配置被代理目标的全限定名）



  ## 学习说明

  IOC的具体流程请看[这里](https://github.com/yafeites/SmallSpring/blob/master/doc/Spring_IOC.md)

  AOP的具体流程请看[这里](https://github.com/yafeites/SmallSpring/blob/master/doc/Spring_AOP.md)

  涉及到的一些问题汇总请看[这里](https://github.com/yafeites/SmallSpring/blob/master/doc/相关问题解答.md)
