package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.Category;


@Repository("categoryRepository")
public interface CategoryRepository
		extends JpaRepository<Category, Long>
{

}