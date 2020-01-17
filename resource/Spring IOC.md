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

**流程如下**

![**image-20200117120739998**](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120739998.png)



**解析完成后将BeanDefinition交给Holder类承载**

![image-20200117120922308](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117120922308.png)



**最终注入BeanFactory中**



![image-20200117121030741](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121030741.png)





#### 3.预加载单例Bean**

 **在Context refresh的最后阶段预加载单例Bean** 



![image-20200117121347939](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121347939.png)





**查看是否已经创建出来，如果没有则从BeanDefinitionmap中取得BeanDefintion创建新的Bean**

![image-20200117121533754](C:\Users\yafeites\AppData\Roaming\Typora\typora-user-images\image-20200117121533754.png)





