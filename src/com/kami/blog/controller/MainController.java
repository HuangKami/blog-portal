package com.kami.blog.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kami.blog.common.Assist;
import com.kami.blog.model.Article;
import com.kami.blog.service.ArticleService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping
	public String index(Model model) {
		for(int i=1; i<=50; i++) {
			Article article = new Article();
			article.setContent("<div class=\"markdown_views\"><blockquote>\n" + 
					"  <p>转载请注明原创出处，谢谢！</p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<h3 id=\"说在前面\"><a name=\"t0\" target=\"_blank\"></a>说在前面</h3>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p>人生的大道上默默地走，就必须要有一盏灯亮着为你引导方向！而这盏灯抑或只是一句话，一句鼓励，一个赞美，一次承认，一次认可，一次相识一次交流……</p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<p>上篇文章：<a href=\"https://mp.weixin.qq.com/s?__biz=MzU2NjIzNDk5NQ==&amp;mid=2247483742&amp;idx=1&amp;sn=45385073d1b4375363f09f7fe6385917&amp;chksm=fcaedb42cbd95254328a7014eb9a1608e4efe7c3df9680431aba6b783e2652cafed2df8663e4#rd\" target=\"_blank\">阿里JAVA开发手册零度的思考理解(一) </a>得到作者<strong>孤尽</strong>的肯定支持，那是一个小激动啊，我会继续努力，继续阅读和思考阿里JAVA开发手册，毕竟每一条都是前人踩过的坑，通过血的教训总结出来的。</p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"上篇题目回顾\"><a name=\"t1\" target=\"_blank\"></a>上篇题目回顾</h3>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-6e6ffb470ba18275.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"阿里JAVA开发手册\" title=\"\"></p>\n" + 
					"\n" + 
					"<p>看完这条，个人觉得主要是<strong>集合相关操作</strong>，在JAVA基础中<strong>集合</strong>这块的重要性也的确非常重要（毕竟是用到最多的），本期只会结合上题进行一些简单扩展，并不会涵盖所有集合操作，也不涉及集合是否线程安全这块，后期我会在我的系列<strong>高并发、锁系列</strong>里扩展深入。</p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"集合的重要性\"><a name=\"t2\" target=\"_blank\"></a>集合的重要性</h3>\n" + 
					"\n" + 
					"<p><strong>已经有数组了为什么会出现集合呢？</strong>依然清晰的记得数据结构里面顺序结构、链式结构的特点。在这里数组就属于顺序结构（但是集合里面根据顺序结构或者链式结构实现的都有，所以在选择用那个的时候最起码需要有那么一点点思考而不是拿什么用什么）。</p>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p>数组一旦定义，长度将不能再变化。并且数组仅仅是一个一连串的变量而已，对于很多重复的操作（并没有进行统一的抽象）而且有些顺序结构并不太适合，需要链式结构实现适合或者是需要顺序结构与链式结构结合实现才比较合适。</p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<p><strong>备注：</strong>对于很多重复的操作，比如如果需要扩容，需要自己实现，根据编码水平不同实现的效率不一样（而且这个可能大量存在，每个人都需要实现，不符合工程学的思想），再比如需要排序，增删，遍历等等。</p>\n" + 
					"\n" + 
					"<p><strong>上面的一些问题就引入了集合并且解决了这些问题，所以集合非常重要，并且项目中集合到处可见，需要把db，nosql里面的数据接收下来。</strong></p>\n" + 
					"\n" + 
					"<p><strong>下面看看集合具备的几个特性 ：</strong> <br>\n" + 
					"- 这种框架是高性能的，对于基本集合（动态数组、链表、树和散列表）的实现是高效的，并且是经过高度测试（不管是性能，安全等都是很可靠的）。 <br>\n" + 
					"- 集合允许不同类型的集合以相同的方式继续操作。 <br>\n" + 
					"- 集合是容易扩展和修改的。</p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"集合的遍历思考\"><a name=\"t3\" target=\"_blank\"></a>集合的遍历思考</h3>\n" + 
					"\n" + 
					"<p>集合遍历，从工程学我们需要提供一种方法顺序访问一个集合对象中的各各元素，<strong>而又不需要暴露该对象的内部表示</strong>。</p>\n" + 
					"\n" + 
					"<p>如何才能做到呢？？？ 迭代器模式就可以做到，下面带大家一起去了解下。</p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"迭代器模式\"><a name=\"t4\" target=\"_blank\"></a>迭代器模式</h3>\n" + 
					"\n" + 
					"<p>迭代器模式的功能主要在于提供对聚合对象的迭代访问。主要就是这个<strong>访问</strong>进行做文章的。那么为什么使用迭代器模式呢？有什么好处呢？</p>\n" + 
					"\n" + 
					"<ul>\n" + 
					"<li>集合对象的类型很多，如果对集合对象的迭代访问跟集合对象本身融合在一起的话，会严重影响到集合对象的可扩展性和可维护性。 <br>\n" + 
					"\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p><strong>备注：</strong>迭代器模式的关键思想就是把对集合对象的遍历和访问从集合对象中分离出来，放到单独的迭代器中，这样集合对象会变得简单一些；而迭代器和集合对象可以独立的变化和发展，这样就大大增强类系统的灵活性。</p></blockquote></li>\n" + 
					"  </ul> <br>\n" + 
					"  一般情况下面，使用的都是外部迭代器（由客户端来控制迭代器的下一个元素的步骤，就是在代码里面我们需要手动调用next来迭代下一个元素，这样做就是要灵活点）\n" + 
					"\n" + 
					"\n" + 
					"<ul>\n" + 
					"<li><strong>JDK5之后引入的新特性foreach（ 增强版for）</strong></li>\n" + 
					"</ul>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p><strong>备注：</strong>通过使用javap查看反编译代码，在数组里面，是固有的foreach实现，直接循环数组，而在容器的迭代foreach是通过迭代器来实现。</p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-c09ddb3971cb0d40.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"数组foreach\" title=\"\"></p>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-7ce777ba9ffb1b53.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"容器的迭代foreach\" title=\"\"></p>\n" + 
					"\n" + 
					"<p><strong>在稍微多做点铺垫</strong> <br>\n" + 
					"- <strong>ArrayList对Iterator接口实现</strong></p>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p>**备注：**ArrayList里面对Iterator实现了2种，一种是普通的从前向后，而第二种是双向迭代输出，可以从往前也可以往后。</p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"解题\"><a name=\"t5\" target=\"_blank\"></a>解题</h3>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-6e6ffb470ba18275.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"阿里JAVA开发手册\" title=\"\"></p>\n" + 
					"\n" + 
					"<p>上面说了那么多，我觉得现在可以开始解题了，各位看官久等了。并发系列又是另外一个重要的话题，先不考虑并发进行分析，如果并发操作，需要对Iterator对象加锁，这个应该好理解。</p>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <ul>\n" + 
					"  <li><p>This field is used by the iterator and list iterator implementation returned by the iterator and listIterator methods. If the value of this field changes unexpectedly, the iterator (or list iterator) <strong>will throw a ConcurrentModificationException in response to the next, remove, previous, set or add operations.</strong> This provides fail-fast behavior, rather than non-deterministic behavior in the face of concurrent modification during iteration. </p></li>\n" + 
					"  <li><p>Use of this field by subclasses is optional. If a subclass wishes to provide fail-fast iterators (and list iterators), then it merely has to increment this field in its <strong>add(int, E) and remove(int)</strong> methods (and any other methods that it overrides that result in structural modifications to the list). A single call to add(int, E) or remove(int) must add no more than one to this field, or the iterators (and list iterators) will throw bogus ConcurrentModificationExceptions. If an implementation does not wish to provide fail-fast iterators, this field may be ignored.</p></li>\n" + 
					"  </ul>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<p><strong>所以应该注意，并不仅仅包括remove，add元素也请使用Iterator方式。</strong></p>\n" + 
					"\n" + 
					"<p><strong>这一条标准是加了强制的，说明了重要性，按照上面的优秀实践去做就对了。</strong></p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<pre class=\"prettyprint\" name=\"code\"><code class=\"language-java hljs  has-numbering\"><span class=\"hljs-keyword\">public</span> <span class=\"hljs-keyword\">static</span> <span class=\"hljs-keyword\">void</span> <span class=\"hljs-title\">main</span>(String[] args) {\n" + 
					"        List&lt;String&gt; list = <span class=\"hljs-keyword\">new</span> ArrayList&lt;String&gt;();\n" + 
					"\n" + 
					"        list.add(<span class=\"hljs-string\">\"1\"</span>);\n" + 
					"        list.add(<span class=\"hljs-string\">\"2\"</span>);\n" + 
					"\n" + 
					"        <span class=\"hljs-keyword\">for</span>(String item:list){\n" + 
					"            <span class=\"hljs-keyword\">if</span>(<span class=\"hljs-string\">\"1\"</span>.equals(item)){  <span class=\"hljs-comment\">//(1    换成  if(\"2\".equals(item)){ </span>\n" + 
					"                list.remove(item);\n" + 
					"            }\n" + 
					"        }\n" + 
					"    }</code><ul class=\"pre-numbering\"><li>1</li><li>2</li><li>3</li><li>4</li><li>5</li><li>6</li><li>7</li><li>8</li><li>9</li><li>10</li><li>11</li><li>12</li></ul></pre>\n" + 
					"\n" + 
					"<p>当（1  换成  if(“2”.equals(item)){  之后，运行结果报异常，结果如图：</p>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-9393c0c2d753fce1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"结果\" title=\"\"></p>\n" + 
					"\n" + 
					"<p>其实这种给出了错误，并且有代码行数的情况其实发现查找问题都挺方便的，<strong>其实该问题的重点就变成了都是基于Iterator的输出，但是在进行删除元素的时候应该用那种方式才正确。</strong></p>\n" + 
					"\n" + 
					"<p><strong>没有必要纠结为什么1不错，而2错，稍微看下源码就知道了，其实我们也可以让2不错，只是jdk里面就是这样实现的，它的解释和考虑如下原因。</strong></p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"原因\"><a name=\"t6\" target=\"_blank\"></a>原因</h3>\n" + 
					"\n" + 
					"<blockquote>\n" + 
					"  <p><strong>ArrayList此类的 iterator 和 listIterator 方法返回的迭代器是快速失败的：在创建迭代器之后，除非通过迭代器自身的 remove 或 add 方法从结构上对列表进行修改，否则在任何时间以任何方式对列表进行修改，迭代器都会抛出 ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险。</strong></p>\n" + 
					"</blockquote>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-8de8bad5d4836abf.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"快速失败\" title=\"\"></p>\n" + 
					"\n" + 
					"<p><strong>所以最佳实践就按照阿里java开发手册里面那样就好了，add元素也请使用Iterator方式。</strong></p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"实际工作中迭代器用法\"><a name=\"t7\" target=\"_blank\"></a>实际工作中迭代器用法</h3>\n" + 
					"\n" + 
					"<p>可能说完，大家感觉迭代器就仅仅在集合遍历里面用，而且都已经有了，其实实际中的确有一些用法，反正都是围绕控制访问的，比如分页，非常常见的情况，如果每次都基于数据库分页那么怕性能不好，如果完全在内存（内存太贵，数据太多，不现实），一般的做法就是比如一页20条数据，我们一般可以每次查询数据库的时候取5页到内存（具体每次取多少可以根据用户行为分析，得到一个比较合理的，而且越到后面访问的机会越少，取到内存的就越少了，可以先比如每次都是取n页数据，在多少页之后每次取m页 之后在每次取一页一页了。n&gt;m&gt;1）。那么比如取出来的100条数据在内存中，需要进行根据分页访问，而原来的jdk里面的好像不满足，那么自己实现一个类似的是不是特别灵活呢？后续有空，我会在我的微信公众号，系列文章的<strong>技术思考</strong>里面把类似这块分析下的。</p>\n" + 
					"\n" + 
					"\n" + 
					"\n" + 
					"<h3 id=\"思考\"><a name=\"t8\" target=\"_blank\"></a>思考</h3>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-97e6bf4bf7371a64.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"阿里JAVA开发手册\" title=\"\"></p>\n" + 
					"\n" + 
					"<p>这是阿里JAVA开发手册其中一条明细，<strong>期待你的留言和分析！！！</strong></p>\n" + 
					"\n" + 
					"<p><strong>如果读完觉得有收获的话，欢迎点赞加关注。</strong></p>\n" + 
					"\n" + 
					"<hr>\n" + 
					"\n" + 
					"<p>查阅更多历史，欢迎关注个人公众号！！！</p>\n" + 
					"\n" + 
					"<p><img src=\"http://upload-images.jianshu.io/upload_images/7849276-5f48f1430e958383.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240\" alt=\"匠心零度公众号.jpg\" title=\"\"></p></div>\n" + 
					"");
			article.setCreateTime(new Date());
			article.setUpdateTime(new Date());
			article.setTopic("Java编程语言");
			article.setReadCount(i + 100);
			article.setTitle("阿里JAVA开发手册零度的思考理解(" + i + ")");
			article.setUserId("9b957111106a11e8a792a81e84dcff54");
			articleService.insertArticle(article);
		}
		
//		Assist assist = new Assist();
//		assist.setStartRow(1).setRowSize(10).setOrder(Assist.order("Article.updateTime", false));
//		List<Article> latestArticles = articleService.selectLatestArticle(assist);
//		model.addAttribute("latestArticles", latestArticles);
		return "main/main";
	}
}
