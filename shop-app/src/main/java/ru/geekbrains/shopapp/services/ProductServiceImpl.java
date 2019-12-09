package ru.geekbrains.shopapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
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


  @Autowired
  @Qualifier("productRepository")
  public void setProductRepository(ProductRepository pr)
  {
	prodRepo = pr;
  }


  @Override
  public Optional<ProductDTO> findById(Long id)
  {
	return prodRepo.findById(id)
				   .map(ProductDTO::new);
  }


  @Override
  public List<ProductDTO> findAll()
  {
	return prodRepo.findAll().stream()
				   .map(ProductDTO::new)
				   .collect(toList());
  }

}