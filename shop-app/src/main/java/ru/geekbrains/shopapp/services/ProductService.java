package ru.geekbrains.shopapp.services;


import ru.geekbrains.shopapp.dto.ProductDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface ProductService
		extends Serializable
{

  Optional<ProductDTO> findById(Long id);

  List<ProductDTO> findAll();

}