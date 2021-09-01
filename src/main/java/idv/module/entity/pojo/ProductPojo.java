package idv.module.entity.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "商品物件")
public class ProductPojo {

    @Schema(name = "商品流水號")
    private Integer id;

    @Schema(name = "商品名稱(英文)")
    private String enName;

    @Schema(name = "商品名稱(中文)")
    private String zhName;

    @Schema(name = "售價")
    private BigDecimal price;

    @Schema(name = "發售日")
    private LocalDate releaseDate;

    @Schema(name = "最後更新日期")
    private LocalDateTime editDate;

}
