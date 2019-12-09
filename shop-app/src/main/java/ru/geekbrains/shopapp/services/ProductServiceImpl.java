package ru.geekbrains.shopapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.geekbrains.dbcommon.model.Category;
import ru.geekbrains.dbcommon.repo.CategoryRepository;
import ru.geekbrains.dbcommon.repo.ProductRepository;
import ru.geekbrains.shopapp.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service("productService")
public class ProductServiceImpl
		implements ProductService
{

  private ProductRepository prodRepo;
  private CategoryRepository categoryRepo;


  @Autowired
  @Qualifier("productRepository")
  public void setProductRepository(ProductRepository pr)
  {
	prodRepo = pr;
  }


  @Autowired
  @Qualifier("categoryRepository")
  public void setCategoryRepository(CategoryRepository cr)
  {
	categoryRepo = cr;
  }


  @Override
  public Optional<ProductDTO> findProductById(Long id)
  {
	return prodRepo.findById(id)
				   .map(ProductDTO::new);
  }


  @Override
  public List<ProductDTO> findAllProducts()
  {
	return prodRepo.findAll().stream()
				   .map(ProductDTO::new)
				   .collect(toList());
  }


  @Override
  public List<Category> findAllCategories()
  {
	return categoryRepo.findAll();
  }


  @Override
  public List<ProductDTO> findProductsByCategory(Long id)
  {
	return prodRepo.findProductsByCategory(id).stream()
				   .map(ProductDTO::new)
				   .collect(toList());
  }

}