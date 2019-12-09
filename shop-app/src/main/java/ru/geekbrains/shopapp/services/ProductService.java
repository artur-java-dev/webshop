package ru.geekbrains.shopapp.services;


import ru.geekbrains.dbcommon.model.Category;
import ru.geekbrains.shopapp.dto.ProductDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface ProductService
		extends Serializable
{

  Optional<ProductDTO> findProductById(Long id);

  List<ProductDTO> findAllProducts();

  List<Category> findAllCategories();

  List<ProductDTO> findProductsByCategory(Long id);

}