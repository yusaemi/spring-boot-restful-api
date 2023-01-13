package idv.module.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity(name = "idv.module.entity.Product")
@Table(name = "product")
public class Product {

    /**
     * 商品流水號
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;
    /**
     * 商品名稱(英文)
     */
    @Column(name = "en_name", nullable = true)
    private String enName;
    /**
     * 商品名稱(中文)
     */
    @Column(name = "zh_name", nullable = true)
    private String zhName;
    /**
     * 售價
     */
    @Column(name = "price", nullable = true)
    private BigDecimal price;
    /**
     * 發售日
     */
    @Column(name = "release_date", nullable = true)
    private LocalDate releaseDate;
    /**
     * 最後更新日期
     */
    @Column(name = "edit_date", nullable = true)
    private LocalDateTime editDate;
}