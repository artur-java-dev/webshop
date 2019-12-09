package ru.geekbrains.admintool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.admintool.dto.ProductDTO;
import ru.geekbrains.dbcommon.model.*;
import ru.geekbrains.dbcommon.repo.CategoryRepository;
import ru.geekbrains.dbcommon.repo.ImageRepository;
import ru.geekbrains.dbcommon.repo.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
public class ProductServiceImpl
		implements ProductService
{

  private ProductRepository prodRepo;
  private CategoryRepository categoryRepo;
  private ImageRepository imageRepo;


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


  @Autowired
  @Qualifier("imageRepository")
  public void setImageRepository(ImageRepository ir)
  {
	imageRepo = ir;
  }


  @Override
  @Transactional
  public List<ProductDTO> findAllProducts()
  {
	return prodRepo.findAll().stream()
				   .map(ProductDTO::new)
				   .collect(toList());
  }


  @Override
  @Transactional
  public Optional<ProductDTO> findProductById(Long id)
  {
	return prodRepo.findById(id)
				   .map(ProductDTO::new);
  }


  @Override
  @Transactional
  public void save(ProductDTO dto)
  throws IOException
  {
	Product product = (dto.id != null) ?
					  prodRepo.findById(dto.id).get() :
					  new Product();

	product.setTitle(dto.title);
	Category cat = categoryRepo.findByName(dto.category).get();
	product.setCategory(cat);
	product.setPrice(dto.price);

	for (MultipartFile file : dto.newImages)
	{
	  ImageData data = new ImageData(file.getBytes());
	  Image img = new Image(file.getOriginalFilename(), file.getContentType(), data);
	  product.addImage(img);
	}

	prodRepo.save(product);
  }


  @Override
  @Transactional
  public boolean delete(Long id)
  {
	if (!prodRepo.findById(id).isPresent())
	  return false;

	prodRepo.deleteById(id);

	return true;
  }


  @Override
  @Transactional
  public List<Category> findAllCategories()
  {
	return categoryRepo.findAll();
  }


  @Override
  public Optional<Category> findCategoryById(Long id)
  {
	return categoryRepo.findById(id);
  }


  @Override
  public void saveCategory(Category category)
  {
	categoryRepo.save(category);
  }


  @Override
  public void deleteCategory(Long id)
  {
	categoryRepo.deleteById(id);
  }


  @Override
  public Optional<Image> findImageById(Long id)
  {
	return imageRepo.findById(id);
  }

}