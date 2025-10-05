package app.springboot.JournalApp.repository;


import app.springboot.JournalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<Users, ObjectId> {
    Users findByUserName(String userName);

    void deleteByUserName(String userName);
}

