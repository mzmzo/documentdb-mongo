package database.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import database.mongodb.entity.UserDTO;

public interface UserRepository extends MongoRepository<UserDTO, String> {

    public UserDTO findById(String id);

    public UserDTO findByName(String name);

}
