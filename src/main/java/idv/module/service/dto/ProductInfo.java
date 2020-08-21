package idv.module.service.dto;

import idv.module.entity.pojo.ProductPojo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * ProductInfo. 2020/8/18 上午 11:39
 *
 * @author sero
 * @version 1.0.0
 **/
@Data
@ApiModel(description = "商品資訊")
public class ProductInfo extends ProductPojo {
}
