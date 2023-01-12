package idv.module.controller;

import idv.module.entity.pojo.ProductPojo;
import idv.module.service.ProductService;
import idv.module.service.dto.ProductInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController. 2020/8/18 上午 10:36
 *
 * @author sero
 * @version 1.0.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Tag(name = "商品處理Controller")
public class ProductController {

    private final ProductService productService;

    /**
     * 取得商品
     *
     * @param id 商品流水號
     * @return ResponseEntity<ProductInfo>
     */
    @Operation(summary = "取得商品")
    @ApiResponse(responseCode = "200", description = "商品已取得")
    @GetMapping("/{id}")
    public ResponseEntity<ProductInfo> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.get(id));
    }

    /**
     * 新增商品
     *
     * @param productPojo 商品物件
     * @return ResponseEntity
     */
    @Operation(summary = "新增商品")
    @ApiResponse(responseCode = "204", description = "商品已新增")
    @PostMapping("")
    public ResponseEntity<Void> create(@RequestBody ProductPojo productPojo) {
        productService.create(productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 更新完整商品
     *
     * @param id 商品流水號
     * @param productPojo 商品物件
     * @return ResponseEntity
     */
    @Operation(summary = "更新完整商品")
    @ApiResponse(responseCode = "204", description = "完整商品已更新")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody ProductPojo productPojo) {
        productService.update(id, productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 更新商品部分資料
     *
     * @param id 商品流水號
     * @param productPojo 商品物件
     * @return ResponseEntity
     */
    @Operation(summary = "更新商品部分資料")
    @ApiResponse(responseCode = "204", description = "商品部分資料已更新")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateMerge(@PathVariable("id") Integer id, @RequestBody ProductPojo productPojo) {
        productService.updateMerge(id, productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 刪除商品
     *
     * @param id 商品流水號
     * @return ResponseEntity
     */
    @Operation(summary = "刪除商品")
    @ApiResponse(responseCode = "204", description = "商品已刪除")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
