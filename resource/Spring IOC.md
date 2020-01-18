# Spring IOC

## **原理**：

**控制反转，具体来说即为将对对象的创建交给第三方来控制，我们只需要将Bean的信息告诉第三方即可**

## **实现功能：**

**由XML进行Bean的配置**

**支持由普通方法或者FactoryBean来创建Bean**

**支持property参数的以下配置**：

**value：支持Java基本属性及其的封装器类**

**ref**：**支持指定id的配置，解决循环依赖问题**

**支持根据名称和类型的自动配置**

**支持单例模式和原型模式的配置（默认情况下为单例模式）**



## 具体类的说明：

**类的名称和继承关系大致根据Spring源码仿照而来，不会有太大的冲突，只是根据主要流程取消了一些类和类方法**

#### 1.Context类

**上下文是Spring的主类，IOC的流程从这里开始**

![image-20200117103033085](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117103033085.png)



**FileSystemApplicationContext：我们通过此类产生的对象进入实际的流程中，是最上层的实现类**

**AbstarctXmlApplicationConext: 实现factory的加载和对XML文件的读取中的对接方法**



![image-20200117110857925](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110857925.png)

**AbstarctRefreshableApplicationContext:实现在refresh()中创建factory和加载factory的对接方法**



![image-20200117110922837](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110922837.png)

**AbstractApplicationContext:实现refresh的具体流程**





![image-20200117110957198](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110957198.png)

#### 2.BeanFactory类

**关于Bean的创建流程从这里开始**

![image-20200117105611104](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117105611104.png)



**DefaultListableBeanFactory:  ** **Factory最上层的实现类，被Context所创建**



![image-20200117110518870](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110518870.png)



**AbstractAutowireCapableBeanFactory: 实现从Bean的创建到参数注入到初始化的一系列工作**



![image-20200117110637248](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110637248.png)

**AbstractBeanFactory: ** **实现取得BeanDefintion到Bean的创建之间的工作**



**![image-20200117110345796](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117110345796.png)**  







#### 3.BeanDefinition类

**XML文件配置的Bean信息都保存在这里**

![image-20200117112601098](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117112601098.png)







**RootBeanDefintion：BeanFactory和BeanDefinitionReader使用的最上层实现类，未实现任何方法，主要用来与Spring中的类保持一致**

**AbstractBeanDefinition：实现关于Bean信息的set，get方法**



![image-20200117113426083](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117113426083.png)



## 具体流程：

#### **1.context启动**和创建BeanFactory

**首先创建FileSystemApplicationContext**

![image-20200117114632544](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117114632544.png)

**触发refresh（）方法**



![image-20200117114723304](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117114723304.png)



#### 2.读取XML文件加载BeanDefinition并注册



**增添BeanFactory开始XML的加载**

![image-20200117114734800](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117114734800.png)





**从loadBeanDefintion进入加载信息并传入BeanFactory中**

![image-20200117115016659](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117115016659.png)



**根据路径location 进入资源加载中**

![image-20200117115154674](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117115154674.png)

**真正的XML信息解析**

![image-20200117120400791](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120400791.png)

**流程为先提取Bean的Id，继续解析Bean的属性和子元素**

![image-20200117120611875](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120611875.png)

![**image-20200117120739998**](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120739998.png)



**解析完成后将BeanDefinition交给Holder类承载**

![image-20200117120922308](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120922308.png)



**最终注入BeanFactory中**



![image-20200117121030741](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121030741.png)





#### 3.预加载单例Bean

 **在Context refresh的最后阶段预加载单例Bean** 



![image-20200117121347939](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121347939.png)





**查看是否已经创建出来，如果没有则从BeanDefinitionmap中取得BeanDefintion创建新的Bean**

![image-20200117121533754](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121533754.png)



##### **创建Bean的步骤**

**1.创建Bean的包装类BeanWrapper，并且根据包装类创建实例Bean**

**2.增加单例Bean构造工厂,用于三层缓存**

**3.给bean添加参数**

**4.bean最终初始化**

![image-20200118102105813](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118102105813.png)





## 具体问题的实现原理

#### **1.ByName和ByType的自动配置**

![image-20200118103324630](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118103324630.png)

****

**ByName：实际直接根据PropertyName创建Bean即可**

**ByType：维持一个map，名为classBeanDefinitionMap，Key为Class类型，Value为RootBeanDefinition类型，在注册BeanDefinition时，根据Bean类型注册进入map**



![image-20200118103909041](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118103909041.png)

#### **2.循环引用问题**

**循环引用是由于A引用B，B引用A而导致的单线程创建死循环问题，函数将在参数注入时一直调用getBean方法**

**解决方法：采用三层缓存**

![image-20200118104420774](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118104420774.png)

**一层缓存：singletonObjects**
**二层缓存：erarlySingletonObjects**
**三层缓存：singletonFactories**



![image-20200118104905204](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118104905204.png)



**在三层缓存创建完后将未完成的Bean移到二层缓存，而当Bean创建完成后会将二层，三层的Bean删除，留在一层缓存中**

![image-20200118105148685](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118105148685.png)

**所以我们只要将三层缓存的singleFactory工厂放在Bean的创建过程中即可，但必须保证在Bean的参数注入前，这样才能起作用，实际也是如此**

![image-20200118105337208](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200118105337208.png)



## 总结

