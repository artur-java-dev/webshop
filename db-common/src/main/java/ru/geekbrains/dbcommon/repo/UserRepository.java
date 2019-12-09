package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.User;

import java.util.Optional;


@Repository("userRepository")
public interface UserRepository
		extends JpaRepository<User, Long>
{

  Optional<User> findOneByUserName(String username);

}