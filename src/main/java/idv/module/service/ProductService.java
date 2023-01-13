package idv.module.service;

import com.google.common.base.CaseFormat;
import idv.module.entity.Product;
import idv.module.entity.pojo.ProductPojo;
import idv.module.repository.ProductDao;
import idv.module.service.dto.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ProductService. 2020/8/18 上午 10:37
 *
 * @author sero
 * @version 1.0.0
 **/
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    /**
     * 取得商品
     *
     * @param id 商品流水號
     * @return ProductInfo
     */
    public ProductInfo get(Integer id) {
        /* 使用JPA查詢 */
        Product product = productDao.findById(id).orElse(new Product());

        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(product, productInfo);

        return productInfo;
    }

    /**
     * 新增商品
     *
     * @param productPojo 商品物件
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
     * @param id 商品流水號
     * @param productPojo 商品物件
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
     * @param id 商品流水號
     * @param productPojo 商品物件
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
     * @param id 商品流水號
     */
    public void delete(Integer id) {
        productDao.deleteById(id);
    }

}
