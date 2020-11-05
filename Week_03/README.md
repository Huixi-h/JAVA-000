学习笔记

week03

Netty原理与API网关

## **什么是高性能?**

从用户角度(业务指标)来看

​	海量的并发用户,比如说日活很高(日活两个亿),比如说同时在线人数200万

> wrk 压测工具里的 -c 代表并发
>
> 一般我们看一个系统的毛刺大不大,平不平稳,我们关注P90,P99(90%及99%的响应时间)

从系统内部角度(技术指标)来看

​	高吞吐量 QPS,TPS 	

​	低延迟

**高性能的副作用:**

- 系统复杂度 x 10以上
- 建设与维护成本++++
- 故障或BUG 导致的破坏性+++++

应对策略:

稳定性建设:

1. 容量
2. 爆炸半径
3. 工程方面积累与改进

## Netty

网络应用框架

1. 异步
2. 事件驱动
3. 基于NIO

适用于

1. 服务端
2. 客户端
3. TCP/UDP

MTU; 最大传输单元

MSS: 最大分段大小

TCP_NODELAY

netty优化

不要阻塞EventLoop

系统参数优化

Ulimit -a

/proc/sys/net/ipv4/tcp_fin_timeout,  TcpTimedWaitDelay

缓冲区优化

SO_RCVBUF 

SO_SNDBUF

SO_BACKLOG

REUSEXXX

心跳周期优化

内存与ByteBuf优化

其他优化