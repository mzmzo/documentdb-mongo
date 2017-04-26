package database.mongodb.service;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.mongodb.entity.UserAwardRecord;
import database.mongodb.entity.UserDTO;
import database.mongodb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserDTO saveUser() {
        repository.deleteAll();
        UserDTO userDTO = new UserDTO();
        UserAwardRecord userAwardRecord = new UserAwardRecord();
        userAwardRecord.setAwardId(1);
        userAwardRecord.setClearTag(123456l);
        userAwardRecord.setProductId(1);
        List<UserAwardRecord> records = new ArrayList<>();
        records.add(userAwardRecord);
        userDTO.setName("Dave");
        userDTO.setAwards(records);
        userDTO = repository.save(userDTO);
        return userDTO;
    }
}
