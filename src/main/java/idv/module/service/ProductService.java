package idv.module.service;

import com.google.common.base.CaseFormat;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import idv.module.entity.Product;
import idv.module.entity.QProduct;
import idv.module.entity.pojo.ProductPojo;
import idv.module.repository.ProductDao;
import idv.module.service.dto.ProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

import static java.util.Objects.isNull;

/**
 * ProductService. 2020/8/18 上午 10:37
 *
 * @author sero
 * @version 1.0.0
 **/
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    /* 代理資源共享，改為IOC
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
     */


    /**
     * 取得商品
     *
     * @param id
     * @return
     */
    public ProductInfo get(Integer id) {

        QProduct qProduct = QProduct.product;

        /* 使用JPA查詢 */
        Product product = productDao.findById(id).orElse(new Product());

        /**
         * 可使用QueryDSL方式查詢
         */
        /* 使用jpaQueryFactory建立查詢
        JPAQuery<Product> jpaQuery = jpaQueryFactory.selectFrom(qProduct).where(qProduct.id.eq(id));
        Product product = jpaQuery.fetchOne();
        if (isNull(product)) {
            product = new Product();
        }
         */

        /* 使用JPA提供介面繼承QueryDSL
        Product product = productDao.findOne(qProduct.id.eq(id)).orElse(new Product());
         */

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(product, productInfo);

        return productInfo;

    }

    /**
     * 新增商品
     *
     * @param productPojo
     * @return
     */
    public void create(ProductPojo productPojo) {
        Product product = new Product();
        BeanUtils.copyProperties(productPojo, product);
        product.setId(null);
        productDao.save(product);
    }

    /**
     * 更新完整商品
     *
     * @param id
     * @param productPojo
     * @return
     */
    public void update(Integer id, ProductPojo productPojo) {
        Product product = new Product();
        BeanUtils.copyProperties(productPojo, product);
        product.setId(id);
        productDao.save(product);
    }

    /**
     * 更新商品部分資料
     *
     * @param id
     * @param productPojo
     * @return
     */
    public void updateMerge(Integer id, ProductPojo productPojo) {

        Product product = productDao.findById(id).orElseThrow(() -> new RuntimeException("商品不存在。"));

        Field[] fields = productPojo.getClass().getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            try {
                String name = field.getName();
                String methodName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, name);
                Method getMethod = productPojo.getClass().getMethod("get" + methodName);
                Object invoke = getMethod.invoke(productPojo);
                if (!ObjectUtils.isEmpty(invoke)) {
                    Method setMethod = product.getClass().getMethod("set" + methodName, field.getType());
                    setMethod.invoke(product, String.valueOf(invoke));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        product.setId(id);
        productDao.save(product);
    }

    /**
     * 刪除商品
     *
     * @param id
     */
    public void delete(Integer id) {
        productDao.deleteById(id);
    }

}
