package com.kami.blog.service.Impl;
import java.util.List;
import com.kami.blog.dao.BlackipDao;
import com.kami.blog.model.Blackip;
import com.kami.blog.service.BlackipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BlackipServiceImpl implements BlackipService{
    @Autowired
    private BlackipDao blackipDao;
    @Override
    public long getBlackipRowCount(){
        return blackipDao.getBlackipRowCount();
    }
    @Override
    public List<Blackip> selectBlackip(){
        return blackipDao.selectBlackip();
    }
    @Override
    public Blackip selectBlackipByObj(Blackip obj){
        return blackipDao.selectBlackipByObj(obj);
    }
    @Override
    public Blackip selectBlackipById(Integer id){
        return blackipDao.selectBlackipById(id);
    }
    @Override
    public int insertBlackip(Blackip value){
        return blackipDao.insertBlackip(value);
    }
    @Override
    public int insertNonEmptyBlackip(Blackip value){
        return blackipDao.insertNonEmptyBlackip(value);
    }
    @Override
    public int insertBlackipByBatch(List<Blackip> value){
        return blackipDao.insertBlackipByBatch(value);
    }
    @Override
    public int deleteBlackipById(Integer id){
        return blackipDao.deleteBlackipById(id);
    }
    @Override
    public int updateBlackipById(Blackip enti){
        return blackipDao.updateBlackipById(enti);
    }
    @Override
    public int updateNonEmptyBlackipById(Blackip enti){
        return blackipDao.updateNonEmptyBlackipById(enti);
    }

    public BlackipDao getBlackipDao() {
        return this.blackipDao;
    }

    public void setBlackipDao(BlackipDao blackipDao) {
        this.blackipDao = blackipDao;
    }

}