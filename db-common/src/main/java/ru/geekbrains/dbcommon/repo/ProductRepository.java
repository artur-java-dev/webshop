package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.Product;

import java.util.List;


@Repository("productRepository")
public interface ProductRepository
		extends JpaRepository<Product, Long>
{

  @Query("select p from Product p where p.category = :id")
  List<Product> findProductsByCategory(@Param("id") Long id);

}