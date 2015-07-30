package io.sphere.sdk.products;

import io.sphere.sdk.attributes.Attribute;
import io.sphere.sdk.models.Base;
import io.sphere.sdk.models.Builder;
import io.sphere.sdk.models.Image;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ProductVariantBuilder extends Base implements Builder<ProductVariant> {
    private final int id;
    @Nullable
    private String productId;
    @Nullable
    private String sku;
    private List<Price> prices = Collections.emptyList();
    private List<Attribute> attributes = Collections.emptyList();
    private List<Image> images = Collections.emptyList();
    @Nullable
    private ProductVariantAvailability availability;

    private ProductVariantBuilder(final int id) {
        this.id = id;
    }

    public static ProductVariantBuilder of(final int id) {
        return new ProductVariantBuilder(id);
    }

    public static ProductVariantBuilder ofMasterVariant() {
        return of(1);
    }

    public ProductVariantBuilder sku(@Nullable final String sku) {
        this.sku = sku;
        return this;
    }

    public ProductVariantBuilder prices(final List<Price> prices) {
        this.prices = prices;
        return this;
    }

    public ProductVariantBuilder prices(final Price ... prices) {
        return prices(Arrays.asList(prices));
    }

    public ProductVariantBuilder price(final Price price) {
        return prices(Collections.singletonList(price));
    }

    public ProductVariantBuilder productId(@Nullable final String productId) {
        this.productId = productId;
        return this;
    }

    public ProductVariantBuilder attributes(final List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public ProductVariantBuilder attributes(final Attribute ... attributes) {
        return attributes(Arrays.asList(attributes));
    }

    public ProductVariantBuilder images(final List<Image> images) {
        this.images = images;
        return this;
    }

    public ProductVariantBuilder availability(@Nullable final ProductVariantAvailability availability) {
        this.availability = availability;
        return this;
    }

    @Override
    public ProductVariant build() {
        return new ProductVariantImpl(id, sku, prices, attributes, images, availability, productId);
    }
}
