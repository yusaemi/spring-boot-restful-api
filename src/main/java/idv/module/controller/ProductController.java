package idv.module.controller;

import idv.module.entity.pojo.ProductPojo;
import idv.module.service.ProductService;
import idv.module.service.dto.ProductInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ProductController. 2020/8/18 上午 10:36
 *
 * @author sero
 * @version 1.0.0
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Api(tags = "商品處理Controller")
public class ProductController {

    private final ProductService productService;

    /**
     * 取得商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "取得商品")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "商品已取得") })
    @GetMapping("/{id}")
    public ResponseEntity<ProductInfo> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.get(id));
    }

    /**
     * 新增商品
     *
     * @param productPojo
     * @return
     */
    @ApiOperation(value = "新增商品")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "商品已新增") })
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProductPojo productPojo) {
        productService.create(productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 更新完整商品
     *
     * @param id
     * @param productPojo
     * @return
     */
    @ApiOperation(value = "更新完整商品")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "完整商品已更新") })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProductPojo productPojo) {
        productService.update(id, productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 更新商品部分資料
     *
     * @param id
     * @param productPojo
     * @return
     */
    @ApiOperation(value = "更新商品部分資料")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "商品部分資料已更新") })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateMerge(@PathVariable("id") Integer id, @RequestBody ProductPojo productPojo) {
        productService.updateMerge(id, productPojo);
        return ResponseEntity.noContent().build();
    }

    /**
     * 刪除商品
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "刪除商品")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "商品已刪除") })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
