# Lab12
> 大家再挣扎一下，成功取决于最后一刻的坚持！

## Project Tips

其实project2只是换了一个输出和输入方式。对于输入，其实只要监听上下左右四个键，获取之后
将它们翻译成 w a s d，交给你原来的逻辑部分处理就好了。对于输出，图形化界面其实就是一个
展板，它接收你传输给它的显示信息，比方说命令行里面是一堆字符串，每次动，只要刷新这个展板
就好了。之前，我发了一个mvc的链接，或许对大家有帮助，我再发一次， 
http://www.ruanyifeng.com/blog/2015/02/mvcmvp_mvvm.html 。

## Demo设计思路
我考虑了一下，虽然动画比较好玩，但是先把Project搞定比较重要，所以这节Lab课直接放给大家
做Project，不需要上交作业。src中是一个MVC的Demo，大家参考一下。这个Demo的启动按钮在
Controller里面，启动它就可以开启项目。功能非常简单，监听用户上下左右的键盘输入，
对Player进行移动，可以对边界和撞墙的情况进行判断。对于这两种情况，只会在命令行输出出来，
如果同学们想要用更高级的方法，可以自行找找Toast怎么用，或者自己写一个Toast。

### 类图

![class_diagram](https://github.com/Java-A-2019/Lab12/raw/master/res/class_diagram.png)

这幅图简单展示了项目的大致结构，项目主体是由Controller, Board和GUI三个类组成的。
它们构成了所谓MVC模式，下面一个小节会详细讲。GUI负责界面的展示，Board负责状态的存储，
Controller负责逻辑的控制。Player和Place是棋盘的基本元素，两个Exception是自定义的，
这样使用起来很方便。

### MVC模式

![mvc](https://github.com/Java-A-2019/Lab12/raw/master/res/mvc.png)

这样做的好处是将逻辑、状态和显示三个部分分离，这样改动的时候，就会方便很多。

### 单例模式
这是23种设计模式中最简单的一种了。它的目的是为了使得全局只有一个变量存在，一方面
节省资源，另外一方面，全局只有一个不会出现混淆。原理如下，其实灰常简单。


```java
public class Board {
    private static Board board;
    private Board() {}
    
    public static Board getInstance() {
            if (board == null) board = new Board();
            return board; 
    }
}
```

上面这种叫做懒汉模式，只有在调用的时候才会创建单例。
* 将构造函数私有化，这样外部无法新建实例。
* 存储一个静态变量，也就是我们的单例。
* 通过类静态方法去访问这个变量，比方说Board.getInstance();。









