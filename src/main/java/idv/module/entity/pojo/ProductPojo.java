package idv.module.entity.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ProductPojo. 2020/8/18 上午 11:37
 *
 * @author sero
 * @version 1.0.0
 **/
@Data
@ApiModel(description = "商品物件")
public class ProductPojo {

    @ApiModelProperty(value = "商品流水號")
    private Integer id;

    @ApiModelProperty(value = "商品名稱(英文)")
    private String enName;

    @ApiModelProperty(value = "商品名稱(中文)")
    private String zhName;

    @ApiModelProperty(value = "售價")
    private BigDecimal price;

    @ApiModelProperty(value = "發售日")
    private LocalDate releaseDate;

    @ApiModelProperty(value = "最後更新日期")
    private LocalDateTime editDate;

}
