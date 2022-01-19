# 什么是NIO

```
non-blocking io 非阻塞IO
```

# 三大组件

## Channel 

```
Channel 有一点类似于 Stream，它就是读写的双向通道，可以从 Channel 将数据读入 Buffer，也可以将 Buffer 的数据写入 Channel，而之前的 Stream 要么是输入，要么是输出，Channel 比 Stream 更为底层
```

常见的 Channel 有：

- FileChannel	文件传输的通道
- DatagramChannel UDP数据的传输通道
- SocketChannel TCP数据的传输通道
- ServerSocketChannel 专用服务器的TCP数据传输通道

## Buffer

```
Buffer 用来暂存数据的内存缓冲区，用来暂时存储从channel中读取到的数据，或从缓冲区中拿数据写到磁盘中
```

常见的 Buffer 有：

- ByteBuffer 以字节来缓冲数据
  - MappedByteBuffer
  - DirectByteBuffer
  - HeapByteBuffer
- ShortBuffer
- IntBuffer
- LongBuffer
- FloatBuffer
- DoubleBuffer

## Selector

```
但从字面上不好理解，需要结合服务器的设计演化来理解它的用途
```

### 多线程版设计

<img src="C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20220119184542467.png" alt="image-20220119184542467" style="zoom:50%;" />

### 线程池版设计

<img src="C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20220119185331449.png" alt="image-20220119185331449" style="zoom:50%;" />

### Selector 版设计

<img src="C:\Users\admin\AppData\Roaming\Typora\typora-user-images\image-20220119185659321.png" alt="image-20220119185659321" style="zoom:50%;" />