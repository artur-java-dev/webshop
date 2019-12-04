package ru.geekbrains.dbcommon.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.dbcommon.model.Image;


@Repository("imageRepository")
public interface ImageRepository
		extends JpaRepository<Image, Long>
{

}