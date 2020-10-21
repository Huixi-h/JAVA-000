学习笔记

week01

JVM常用命令行工具

jps -mlv 与 jinfo 进程号组合,可以查看单个进程的GC相关参数

Jstat -gcutil pid  [多长时间打印一次] [执行多少次]

Jstack -l pid 

Jcmd pid VM.version

Jcmd pid VM.command_line

Jcmd pid help



GC相关

引用计数法容易引起循环引用

标记清除算法

- 标记: 遍历所有可达对象,并在本地内存分门别类记下
- 清除: 保证不可达对象所占用的内存,在之后进行的内存分配时可以重用.

分代假设; 大部分新生对象很快无用;存活较长时间的对象,可能存活更长时间.

年轻代的GC频率会比较高,老年代的GC频率会比较低

对象分配在新生代的Eden区,

标记阶段Eden区存活的对象就会复制到存活区,

两个存活区from和to,互换角色,对象存活到一定周期会提升到老年代

-XX: +MaxTenuringThreshold=15(控制提升阈值)

老年代默认是存活对象,采用移动的方式:

1.标记所有通过GC Roots 可达的对象;

2.删除所有不可达对象;

3: 整理老年代空间中的内容,方法是将所有存活对象复制,从老年代空间开始的地方依次存放;

可以作为GC Roots的对象(确定是活的)

1.当前正在执行方法里的局部变量和输入参数;

2.活动线程;

3.所有类的静态字段;

4.JNI引用;



串行GC (Serial GC) / ParNewGC

-XX: +UseSerialGC 配置串行GC

串行GC对年轻代使用mark-copy(标记-复制)算法,对老年代使用mark-sweep-compact(标记-清除-整理)算法.

两者都是单线程的垃圾收集器,不能进行并行处理,所以都会触发全线暂停(STW),停止所有的应用线程.

因此这种GC算法不能充分利用多核CPU,不管CPU有几个核,JVM在垃圾收集时都只能使用单个cpu核心.

cpu利用率高,暂停时间长,简单粗暴,就像老式的电脑,动不动就卡死.

该选型只适合几百MB堆内存的JVM,而且是单核cpu比较管用,

-XX: +UseParNewGC 改进版本的Serial GC, 可以配合CMS使用.



并行GC(Parallel GC)

-XX: +UseParallelGC

-XX: +UseParallelOldGC

年轻代和老年代的垃圾回收都会触发STW事件.

在年轻代使用标记-复制算法,在老年代使用标记-清除-整理算法,

-XX: ParallelGCThreads=N 来指定GC线程数,其默认值是cpu核心数.

并行垃圾收集器适用于多核服务器,主要目标是增加吞吐量,因为对系统资源的有效利用,能达到更高的吞吐率.

1.在GC期间,所有cpu内核都在并行清理垃圾,所以总暂停时间更短;

2.在两次GC周期的间隔期,没有GC线程在运行,不会消耗任何系统资源.

Java8的默认GC策略是并行GC.