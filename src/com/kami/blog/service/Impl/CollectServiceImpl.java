package com.kami.blog.service.Impl;
import java.util.List;
import com.kami.blog.dao.CollectDao;
import com.kami.blog.model.Collect;
import com.kami.blog.common.Assist;
import com.kami.blog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CollectServiceImpl implements CollectService{
    @Autowired
    private CollectDao collectDao;
    @Override
    public long getCollectRowCount(Assist assist){
        return collectDao.getCollectRowCount(assist);
    }
    @Override
    public List<Collect> selectCollect(Assist assist){
        return collectDao.selectCollect(assist);
    }
    @Override
    public Collect selectCollectByObj(Collect obj){
        return collectDao.selectCollectByObj(obj);
    }
    @Override
    public Collect selectCollectById(Integer id){
        return collectDao.selectCollectById(id);
    }
    @Override
    public int insertCollect(Collect value){
        return collectDao.insertCollect(value);
    }
    @Override
    public int insertNonEmptyCollect(Collect value){
        return collectDao.insertNonEmptyCollect(value);
    }
    @Override
    public int insertCollectByBatch(List<Collect> value){
        return collectDao.insertCollectByBatch(value);
    }
    @Override
    public int deleteCollectById(Integer id){
        return collectDao.deleteCollectById(id);
    }
    @Override
    public int deleteCollect(Assist assist){
        return collectDao.deleteCollect(assist);
    }
    @Override
    public int updateCollectById(Collect enti){
        return collectDao.updateCollectById(enti);
    }
    @Override
    public int updateCollect(Collect value, Assist assist){
        return collectDao.updateCollect(value,assist);
    }
    @Override
    public int updateNonEmptyCollectById(Collect enti){
        return collectDao.updateNonEmptyCollectById(enti);
    }
    @Override
    public int updateNonEmptyCollect(Collect value, Assist assist){
        return collectDao.updateNonEmptyCollect(value,assist);
    }

    public CollectDao getCollectDao() {
        return this.collectDao;
    }

    public void setCollectDao(CollectDao collectDao) {
        this.collectDao = collectDao;
    }

}