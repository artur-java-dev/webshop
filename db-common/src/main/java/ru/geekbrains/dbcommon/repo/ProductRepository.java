package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.Product;


@Repository("productRepository")
public interface ProductRepository
		extends JpaRepository<Product, Long>
{

}