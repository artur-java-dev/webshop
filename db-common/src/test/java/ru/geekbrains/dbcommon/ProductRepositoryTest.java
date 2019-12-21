package ru.geekbrains.dbcommon;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import ru.geekbrains.dbcommon.model.Category;
import ru.geekbrains.dbcommon.model.Product;
import ru.geekbrains.dbcommon.repo.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestPropertySource(locations = "classpath:application-test.properties")
@DataJpaTest
public class ProductRepositoryTest
{

  @Autowired
  private TestEntityManager entityMng;

  @Autowired
  @Qualifier("productRepository")
  private ProductRepository repo;


  @Test
  public void findProducts()
  {
	Category cat = new Category();
	cat.setName("Category");

	Product prod1 = new Product();
	prod1.setTitle("Product 1");
	prod1.setCategory(cat);

	Product prod2 = new Product();
	prod2.setTitle("Product 2");
	prod2.setCategory(cat);

	entityMng.persist(cat);
	entityMng.persist(prod1);
	entityMng.persist(prod2);

	List<Product> products = repo.findProductsByCategory(cat.getId());
	assertNotNull(products);
	assertEquals(2, products.size());
	assertEquals("Product 1", products.get(0).getTitle());
	assertEquals("Product 2", products.get(1).getTitle());

	products = repo.findProductsByCategory(Long.MAX_VALUE);
	assertNotNull(products);
	assertEquals(0, products.size());
  }

}