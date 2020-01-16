# Spring IOC

### **原理**：

**控制反转，具体来说即为将对对象的创建交给第三方来控制，我们只需要将Bean的信息告诉第三方即可**

### **实现功能：**

**由XML进行Bean的配置。**

**支持property参数的**：

**value：支持Java基本属性及其的封装器类**

**ref**：**支持指定id的配置，解决循环依赖问题**

**支持根据名称和类型的自动配置**



### 具体流程：

**1.context启动**

**2.创建BeanFactory**

**3.通过路劲加载资源文件**

**4.读取XML文件加载BeanDefinition**

**5.注册BeanDefinition进入Factory**

**6.预加载单例Bean**















































