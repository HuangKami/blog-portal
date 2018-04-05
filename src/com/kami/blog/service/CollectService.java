package com.kami.blog.service;
import java.util.List;
import com.kami.blog.model.Collect;
import com.kami.blog.common.Assist;
public interface CollectService{
	/**
	 * 获得Collect数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getCollectRowCount(Assist assist);
	/**
	 * 获得Collect数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<Collect> selectCollect(Assist assist);
	/**
	 * 获得一个Collect对象,以参数Collect对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    Collect selectCollectByObj(Collect obj);
	/**
	 * 通过Collect的id获得Collect对象
	 * @param id
	 * @return
	 */
    Collect selectCollectById(Integer id);
	/**
	 * 插入Collect到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertCollect(Collect value);
	/**
	 * 插入Collect中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyCollect(Collect value);
	/**
	 * 批量插入Collect到数据库
	 * @param value
	 * @return
	 */
    int insertCollectByBatch(List<Collect> value);
	/**
	 * 通过Collect的id删除Collect
	 * @param id
	 * @return
	 */
    int deleteCollectById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除Collect
	 * @param assist
	 * @return
	 */
    int deleteCollect(Assist assist);
	/**
	 * 通过Collect的id更新Collect中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateCollectById(Collect enti);
 	/**
	 * 通过辅助工具Assist的条件更新Collect中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateCollect(Collect value,  Assist assist);
	/**
	 * 通过Collect的id更新Collect中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyCollectById(Collect enti);
 	/**
	 * 通过辅助工具Assist的条件更新Collect中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyCollect(Collect value, Assist assist);
}