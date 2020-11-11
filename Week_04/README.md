学习笔记

week04

ArrayList

基本特点:基于数组,便于index访问,超过数组需要扩容,扩容成本较高.

用途: 大部分情况下操作一组数据都可以用ArrayList

原理: 使用数组模拟列表,默认大小10,扩容*1.5, `newCapacity=oldCapacity+(oldCapacity>>1)`

安全问题:

1.写冲突 两个写,相互操作冲突;

2.读写冲突: 读,特别是iterator时,	数据个数变了,拿到了非预期数据or报错产生cocurrentModificationException

LinkedList

基本特点: 使用链表实现,无需扩容;

用途: 不知道容量,插入变动多的场景;

原理: 使用双向指针将所有的节点连起来

安全问题: 

1,写冲突: 两个写,相互操作冲突;

2.读写冲突: 读,特别是iterator时,	数据个数变了,拿到了非预期数据or报错产生cocurrentModificationException

List线程安全的简单方法:

既然线程安全是写冲突和读写冲突导致的,

读写都加锁: 

1.ArrayList的所有方法都加synchronized, -> Vector;

2.Collections.synchronizedList强制将List的操作加上同步;

3.Arrays.asList,不允许添加删除,但是可以set替换元素;

4.Collections.unmodifiableList,不允许修改内容,包括添加删除和set;

CopyOnWriteArrayList

核心改进原理: 

1写加锁; 保证不会写混乱;

2.写在一个copy副本上,而不是原始数据上,读写分离,最终一致;

HashMap:

基本特点: 空间换时间,哈希冲突不大的情况下查找数据性能较高

用途: 存放指定key的对象,缓存对象;

原理: 使用hash原理,存k-v数据,初始容量16,扩容*2,负载因子0.75,jdk8以后,在链表长度到8 & 数组长度到64时,使用红黑树

安全问题: 

1.写冲突;

2.读写问题,可能会死循环;

3.keys()无序问题;

ConcurrentHashMap-Java7分段锁

默认16个Segment,降低锁力度,concurrentLevel=16

ConcurrentHashMap-Java8

摒弃了分段锁方案,而是直接使用一个大的数组.	

