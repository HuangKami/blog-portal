package com.kami.blog.service.Impl;
import java.util.List;
import com.kami.blog.dao.FollowDao;
import com.kami.blog.model.Follow;
import com.kami.blog.model.FollowUser;
import com.kami.blog.common.Assist;
import com.kami.blog.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowDao followDao;
    @Override
    public long getFollowRowCount(Assist assist){
        return followDao.getFollowRowCount(assist);
    }
    @Override
    public List<Follow> selectFollow(Assist assist){
        return followDao.selectFollow(assist);
    }
    @Override
    public Follow selectFollowByObj(Follow obj){
        return followDao.selectFollowByObj(obj);
    }
    @Override
    public Follow selectFollowById(Integer id){
        return followDao.selectFollowById(id);
    }
    @Override
    public int insertFollow(Follow value){
        return followDao.insertFollow(value);
    }
    @Override
    public int insertNonEmptyFollow(Follow value){
        return followDao.insertNonEmptyFollow(value);
    }
    @Override
    public int insertFollowByBatch(List<Follow> value){
        return followDao.insertFollowByBatch(value);
    }
    @Override
    public int deleteFollowById(Integer id){
        return followDao.deleteFollowById(id);
    }
    @Override
    public int deleteFollow(Assist assist){
        return followDao.deleteFollow(assist);
    }
    @Override
    public int updateFollowById(Follow enti){
        return followDao.updateFollowById(enti);
    }
    @Override
    public int updateFollow(Follow value, Assist assist){
        return followDao.updateFollow(value,assist);
    }
    @Override
    public int updateNonEmptyFollowById(Follow enti){
        return followDao.updateNonEmptyFollowById(enti);
    }
    @Override
    public int updateNonEmptyFollow(Follow value, Assist assist){
        return followDao.updateNonEmptyFollow(value,assist);
    }

    public FollowDao getFollowDao() {
        return this.followDao;
    }

    public void setFollowDao(FollowDao followDao) {
        this.followDao = followDao;
    }
	@Override
	public FollowUser selectFollowByUserId(String userId) {
		return followDao.selectFollowByUserId(userId);
	}

}