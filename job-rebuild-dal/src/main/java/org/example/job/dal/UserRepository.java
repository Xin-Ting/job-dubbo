package org.example.job.dal;


import org.example.job.dal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


/**
 * @author 85217
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserEntityByUsername(String username);


}
