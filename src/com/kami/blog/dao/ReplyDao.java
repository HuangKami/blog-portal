package com.kami.blog.dao;
import com.kami.blog.model.Reply;
import java.util.List;
import com.kami.blog.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface ReplyDao{
	/**
	 * 获得Reply数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getReplyRowCount(Assist assist);
	/**
	 * 获得Reply数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Reply> selectReply(Assist assist);
	/**
	 * 获得一个Reply对象,以参数Reply对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Reply selectReplyByObj(Reply obj);
	/**
	 * 通过Reply的id获得Reply对象
	 * @param id
	 * @return
	 */
    Reply selectReplyById(Integer id);
	/**
	 * 插入Reply到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertReply(Reply value);
	/**
	 * 插入Reply中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyReply(Reply value);
	/**
	 * 批量插入Reply到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertReplyByBatch(List<Reply> value);
	/**
	 * 通过Reply的id删除Reply
	 * @param id
	 * @return
	 */
    int deleteReplyById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Reply
	 * @param assist
	 * @return
	 */
    int deleteReply(Assist assist);
	/**
	 * 通过Reply的id更新Reply中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateReplyById(Reply enti);
 	/**
	 * 通过辅助工具Assist的条件更新Reply中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateReply(@Param("enti") Reply value, @Param("assist") Assist assist);
	/**
	 * 通过Reply的id更新Reply中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyReplyById(Reply enti);
 	/**
	 * 通过辅助工具Assist的条件更新Reply中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyReply(@Param("enti") Reply value, @Param("assist") Assist assist);
    /**
     * 通过文章ID删除回复
     * @param articleId
     * @return
     */
    int deleteReplyByArticleId(Integer articleId);
}