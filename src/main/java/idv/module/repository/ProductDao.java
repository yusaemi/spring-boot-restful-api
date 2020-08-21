package idv.module.repository;


import idv.module.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * ProductDao. 2020/8/18 上午 10:34
 *
 * @author sero
 * @version 1.0.0
 **/
public interface ProductDao extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product> {

}
