``
迭代器模式
``
> 一个完整的迭代器模式一般会涉及容器和容器迭代器两部分内容。
为了达到基于接口而非实现编程的目的，容器又包含容器接口（List）、容器实现类（ArrayList）;
迭代器又包含迭代器接口(Iterator)、迭代器实现类(ArrayIterator)。
>
定义迭代器的两种方法（我们使用方式一，因为它更灵活）
容器中需要定义 iterator() 方法，用来创建迭代器。
迭代器接口中需要定义 hasNext()、currentItem()、next() 三个最基本的方法

迭代器接口

````
// 接口定义方式一
 public interface Iterator<E> {
   boolean hasNext();
   void next();
   E currentItem();
 }
 
 // 接口定义方式二
 public interface Iterator<E> {
   boolean hasNext();
   E next();
 }
````
容器接口
````
public interface List<E> {

    /**
     * 组合迭代
     */
    Iterator iterator();

    /**
     * 容器大小
     */
    int size();

    /**
     * 新增元素
     */
    boolean add(E e);

    /**
     * 获取元素信息
     */
    E get(int cursor);

}

````
