package com.kami.blog.service;
import java.util.List;
import com.kami.blog.model.Follow;
import com.kami.blog.model.FollowUser;
import com.kami.blog.common.Assist;
public interface FollowService{
	/**
	 * 获得Follow数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getFollowRowCount(Assist assist);
	/**
	 * 获得Follow数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Follow> selectFollow(Assist assist);
	/**
	 * 获得一个Follow对象,以参数Follow对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Follow selectFollowByObj(Follow obj);
	/**
	 * 通过Follow的id获得Follow对象
	 * @param id
	 * @return
	 */
    Follow selectFollowById(Integer id);
	/**
	 * 插入Follow到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertFollow(Follow value);
	/**
	 * 插入Follow中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyFollow(Follow value);
	/**
	 * 批量插入Follow到数据库
	 * @param value
	 * @return
	 */
    int insertFollowByBatch(List<Follow> value);
	/**
	 * 通过Follow的id删除Follow
	 * @param id
	 * @return
	 */
    int deleteFollowById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Follow
	 * @param assist
	 * @return
	 */
    int deleteFollow(Assist assist);
	/**
	 * 通过Follow的id更新Follow中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateFollowById(Follow enti);
 	/**
	 * 通过辅助工具Assist的条件更新Follow中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateFollow(Follow value,  Assist assist);
	/**
	 * 通过Follow的id更新Follow中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyFollowById(Follow enti);
 	/**
	 * 通过辅助工具Assist的条件更新Follow中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyFollow(Follow value, Assist assist);
    /**
     * 查询关注
     */
    FollowUser selectFollowByUserId(String userId);
}