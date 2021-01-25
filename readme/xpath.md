## xpath方法总结
#### XPath 是一门在 XML 文档中查找信息的语言， 可用来在 XML 文档中对元素和属性进行遍历.
#### 常用表达式

表达式|描述
--:|:--
节点名称(nodename)|选取此节点的所有子节点
/|从根节点选取
//|从匹配选择的当前节点选择文档中的节点,而不考虑它的位置
.|选取当前节点
..|选取当前节点的父节点
@|选取属性

#### 限定语
##### 用来查找某个特定的节点或包含某个指定值的节点

--:|:--
//book[price>35.00]|选择所有book元素，且其中price值大于35.00
/bookstore/book[1]|选取属于bookstore子元素的第一个book元素
/bookstore/book[last()]|选取属于bookstore子元素最后一个book元素
/bookstore/book[last()-1]|选取bookstore子元素倒数第二个book元素
/bookstore/book[position()<3]|选取前面两个属于bookstore元素的子元素的book元素
//title[@lang]|选取所有拥有lang的属性的title元素
//title[@lang='eng']|选取所有title元素,且这些元素拥有值为eng的lang属性
/bookstore/book[price>35.00]|选取所有bookstore元素的book元素,且price值大于35
/bookstore/book[price>35]/title|选取所有bookstore元素中book元素的title元素且price值大于35

#### 通配符
