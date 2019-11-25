package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.Role;


@Repository
public interface RoleRepository
		extends JpaRepository<Role, Long>
{

  Role findOneByName(String name);

}