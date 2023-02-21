package fm.douban.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import fm.douban.app.control.MainControl;
import fm.douban.model.Singer;
import fm.douban.service.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class SingerServiceImpl implements SingerService {

    @Autowired
    private static final Logger LOG = LoggerFactory.getLogger(MainControl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Singer addSinger(Singer singer) {
        if (singer == null) {
            LOG.error("singer can not be null");
            return null;
        }
        return mongoTemplate.insert(singer);
    }

    @Override
    public Singer get(String singerId) {
        if (singerId == null) {
            LOG.error("singerId can not be null");
            return null;
        }
        return mongoTemplate.findById(singerId, Singer.class);
    }

    @Override
    public List<Singer> getAll() {
        return mongoTemplate.findAll(Singer.class);
    }

    @Override
    public boolean modify(Singer singer) {
        if (singer == null) {
            LOG.error("singer can not be null");
            return false;
        }
        Query query = new Query(Criteria.where("singerId").is(singer.getId()));
        Update update = new Update();
        update.set("name", singer.getName());
        update.set("avatar", singer.getName());
        update.set("homePage", singer.getName());
        update.set("similarSingerIds", singer.getName());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Singer.class);

        return result != null && result.getModifiedCount() > 0;
    }

    @Override
    public boolean delete(String singerId) {
        if (singerId == null) {
            LOG.error("singerId can not be null");
            return false;
        }
        Singer singer = new Singer();
        singer.setId(singerId);

        DeleteResult result = mongoTemplate.remove(singer);

        return result != null && result.getDeletedCount() > 0;
    }
}
