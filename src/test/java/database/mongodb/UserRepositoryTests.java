package database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import com.mongodb.WriteResult;

import database.mongodb.entity.UserAwardRecord;
import database.mongodb.entity.UserDTO;
import database.mongodb.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository repository;

    @Autowired
    MongoTemplate mongoTemplate;

    public void saveUser(String id, String name) {
        UserDTO userDTO = new UserDTO();
        UserAwardRecord userAwardRecord1 = new UserAwardRecord();
        userAwardRecord1.setExtra("test");
        userAwardRecord1.setAwardId(1);
        userAwardRecord1.setClearTag(123456l);
        userAwardRecord1.setProductId(1);
        UserAwardRecord userAwardRecord2 = new UserAwardRecord();
        userAwardRecord2.setExtra("test");
        userAwardRecord2.setAwardId(2);
        userAwardRecord2.setClearTag(123456l);
        userAwardRecord2.setProductId(2);
        List<UserAwardRecord> records = new ArrayList<>();
        records.add(userAwardRecord1);
        records.add(userAwardRecord2);
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setAwards(records);
        repository.save(userDTO);
        System.out.println(userDTO);
    }

    @Test
    public void updateUser() throws Exception {
        repository.deleteAll();
        saveUser("1", "Dava");
        WriteResult writeResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("Dava")),
                new Update().set("awards.$.extra", "modify"), UserDTO.class);

        System.out.println(writeResult);
        System.out.println(repository.findAll());

        //        BasicQuery query = new BasicQuery("{ awards.clearTag:123456}");
        //        BasicUpdate update = new BasicUpdate("{$set:{awards.$.extra: 'firstModify' }}");
        //        UserDTO userDTO = mongoTemplate.findAndModify(query, update, UserDTO.class);
        //        System.out.println(userDTO);

        //        userDTO = mongoTemplate.findAndModify(query1, new Update().set("awards.$.extra", "secondModify"), UserDTO.class);
        //        System.out.println(userDTO);

        //        WriteResult writeResult = mongoTemplate.updateMulti(query1, new Update().set("awards.$.extra", "fourthModify"),
        //                UserDTO.class);
        //        System.out.println(writeResult);

        //        writeResult = mongoTemplate.updateMulti(query1, new Update().set("awards.$.extra", "fifthModify"), UserDTO.class);
        //        System.out.println(writeResult);

    }

    @Test
    public void pressureTest() {
        int count = 1;
        repository.deleteAll();
        for (int i = 0; i < 1000000; i++) {
            saveUser("" + count++, "" + System.currentTimeMillis());
        }
    }
}
