package com.kami.blog.service.Impl;
import java.util.List;
import com.kami.blog.dao.ReplyDao;
import com.kami.blog.model.Reply;
import com.kami.blog.common.Assist;
import com.kami.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    private ReplyDao replyDao;
    @Override
    public long getReplyRowCount(Assist assist){
        return replyDao.getReplyRowCount(assist);
    }
    @Override
    public List<Reply> selectReply(Assist assist){
        return replyDao.selectReply(assist);
    }
    @Override
    public Reply selectReplyByObj(Reply obj){
        return replyDao.selectReplyByObj(obj);
    }
    @Override
    public Reply selectReplyById(Integer id){
        return replyDao.selectReplyById(id);
    }
    @Override
    public int insertReply(Reply value){
        return replyDao.insertReply(value);
    }
    @Override
    public int insertNonEmptyReply(Reply value){
        return replyDao.insertNonEmptyReply(value);
    }
    @Override
    public int insertReplyByBatch(List<Reply> value){
        return replyDao.insertReplyByBatch(value);
    }
    @Override
    public int deleteReplyById(Integer id){
        return replyDao.deleteReplyById(id);
    }
    @Override
    public int deleteReply(Assist assist){
        return replyDao.deleteReply(assist);
    }
    @Override
    public int updateReplyById(Reply enti){
        return replyDao.updateReplyById(enti);
    }
    @Override
    public int updateReply(Reply value, Assist assist){
        return replyDao.updateReply(value,assist);
    }
    @Override
    public int updateNonEmptyReplyById(Reply enti){
        return replyDao.updateNonEmptyReplyById(enti);
    }
    @Override
    public int updateNonEmptyReply(Reply value, Assist assist){
        return replyDao.updateNonEmptyReply(value,assist);
    }

    public ReplyDao getReplyDao() {
        return this.replyDao;
    }

    public void setReplyDao(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }
	@Override
	public int deleteReplyByArticleId(Integer articleId) {
		return replyDao.deleteReplyByArticleId(articleId);
	}

}