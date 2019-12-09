package ru.geekbrains.admintool.services;


import ru.geekbrains.admintool.dto.ProductDTO;
import ru.geekbrains.dbcommon.model.Category;
import ru.geekbrains.dbcommon.model.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface ProductService
{

  List<ProductDTO> findAllProducts();

  Optional<ProductDTO> findProductById(Long id);

  void save(ProductDTO product)
  throws IOException;

  boolean delete(Long id);

  List<Category> findAllCategories();

  Optional<Category> findCategoryById(Long id);

  void saveCategory(Category category);

  void deleteCategory(Long id);

  Optional<Image> findImageById(Long id);

}