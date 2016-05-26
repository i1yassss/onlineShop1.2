package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Category;


/**
 * Репозиторий для категорий товара
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
