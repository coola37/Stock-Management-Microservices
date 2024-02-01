package com.yigitkula.stockmanagement.productservice.conroller;

import com.yigitkula.stockmanagement.productservice.enums.Language;
import com.yigitkula.stockmanagement.productservice.exception.enums.FriendlyMessageCodes;
import com.yigitkula.stockmanagement.productservice.exception.utils.FriendlyMessageUtils;
import com.yigitkula.stockmanagement.productservice.repository.entity.Product;
import com.yigitkula.stockmanagement.productservice.request.ProductCreateRequest;
import com.yigitkula.stockmanagement.productservice.request.ProductUpdateRequest;
import com.yigitkula.stockmanagement.productservice.response.FriendlyMessage;
import com.yigitkula.stockmanagement.productservice.response.InternalApiResponse;
import com.yigitkula.stockmanagement.productservice.response.ProductResponse;
import com.yigitkula.stockmanagement.productservice.service.IProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/api/1.0/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductRepositoryService productRepositoryService;

    @ResponseStatus(CREATED)
    @PostMapping("/{language}/create")
    public InternalApiResponse<ProductResponse> createProduct(@PathVariable Language language,
                                                              @RequestBody ProductCreateRequest productCreateRequest){
        log.debug("[{}] [createProduct] -> request: {}", this.getClass().getSimpleName(), productCreateRequest);
        Product product = productRepositoryService.createProduct(language, productCreateRequest);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}] [createProduct] -> response: {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCES))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESFULLY_CREATED))
                        .build())
                .httpStatus(CREATED)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(OK)
    @GetMapping("/{language}/get/{productId}")
    public InternalApiResponse<ProductResponse> getProduct(@PathVariable Language language,
                                                           @PathVariable Long productId){
        log.debug("[{}] [getProduct] -> request productId: {}", this.getClass().getSimpleName(), productId);

        Product product = productRepositoryService.getProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}] [getProduct] -> response {}", this.getClass().getSimpleName(), productId);
        return InternalApiResponse.<ProductResponse>builder()
                .httpStatus(OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }


    @ResponseStatus(OK)
    @GetMapping("/{language}/products")
    public InternalApiResponse<List<ProductResponse>> getAllProducts(@PathVariable Language language){
        log.debug("[{}] [getProducts]", this.getClass().getSimpleName());
        List<Product> products = productRepositoryService.getProducts(language);
        List<ProductResponse> productResponses = convertProductResponseList(products);
        log.debug("[{}][getProducts] -> response: {}", this.getClass().getSimpleName(), productResponses);
        return InternalApiResponse.<List<ProductResponse>>builder()
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .payload(productResponses)
                .build();
    }

    private List<ProductResponse> convertProductResponseList(List<Product> productList) {
        return productList.stream()
                .map(arg -> ProductResponse.builder()
                        .productId(arg.getProductId())
                        .productName(arg.getProductName())
                        .quantity(arg.getQuantity())
                        .price(arg.getPrice())
                        .productCreatedDate(arg.getProductCreatedDate().getTime())
                        .productUptadedDate(arg.getProductUptadedDate().getTime())
                        .build())
                .collect(Collectors.toList());
    }

    @ResponseStatus(OK)
    @PutMapping("/{language}/update/{productId}")
    public InternalApiResponse<ProductResponse> updateProduct(@PathVariable Language language,
                                                              @PathVariable Long productId,
                                                              @RequestBody ProductUpdateRequest productUpdateRequest){
        log.debug("[{}] [updateProduct] -> request {}", this.getClass().getSimpleName(), productUpdateRequest);

        Product product = productRepositoryService.updateProduct(language, productId, productUpdateRequest);
        ProductResponse productResponse = convertProductResponse(product);

        log.debug("[{}] [updateProduct] -> request {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCES))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESFULLY_UPDATED))
                        .build())
                .httpStatus(OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{language}/delete/{productId}")
    public InternalApiResponse<ProductResponse> deleteProduct(@PathVariable Language language,
                                                              @PathVariable Long productId){
        log.debug("[{}] [deleteProduct] -> request {}", this.getClass().getSimpleName(), productId);
        Product product = productRepositoryService.deleteProduct(language, productId);
        ProductResponse productResponse = convertProductResponse(product);
        log.debug("[{}] [deleteProduct] -> response {}", this.getClass().getSimpleName(), productResponse);
        return InternalApiResponse.<ProductResponse>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.SUCCES))
                        .description(FriendlyMessageUtils.getFriendlyMessage(language, FriendlyMessageCodes.PRODUCT_SUCCESFULLY_DELETED))
                        .build())
                .httpStatus(OK)
                .hasError(false)
                .payload(productResponse)
                .build();
    }

    private static ProductResponse convertProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .productCreatedDate(product.getProductCreatedDate().getTime())
                .productUptadedDate(product.getProductUptadedDate().getTime())
                .build();
    }
}
