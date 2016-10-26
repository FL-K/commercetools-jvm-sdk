package io.sphere.sdk.shippingmethods.queries;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sphere.sdk.models.Referenceable;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.MetaModelQueryDsl;
import io.sphere.sdk.shippingmethods.ShippingMethod;
import io.sphere.sdk.shippingmethods.expansion.ShippingMethodExpansionModel;
import io.sphere.sdk.taxcategories.TaxCategory;

/**
 * {@doc.gen summary shipping methods}
 */
public interface ShippingMethodQuery extends MetaModelQueryDsl<ShippingMethod, ShippingMethodQuery, ShippingMethodQueryModel, ShippingMethodExpansionModel<ShippingMethod>> {
    /**
     * Creates a container which contains the full Java type information to deserialize the query result (NOT this class) from JSON.
     *
     * @see io.sphere.sdk.json.SphereJsonUtils#readObject(byte[], TypeReference)
     * @see io.sphere.sdk.json.SphereJsonUtils#readObject(String, TypeReference)
     * @see io.sphere.sdk.json.SphereJsonUtils#readObject(com.fasterxml.jackson.databind.JsonNode, TypeReference)
     * @see io.sphere.sdk.json.SphereJsonUtils#readObjectFromResource(String, TypeReference)
     *
     * @return type reference
     */
    static TypeReference<PagedQueryResult<ShippingMethod>> resultTypeReference() {
        return new TypeReference<PagedQueryResult<ShippingMethod>>(){
            @Override
            public String toString() {
                return "TypeReference<PagedQueryResult<ShippingMethod>>";
            }
        };
    }

    static ShippingMethodQuery of() {
        return new ShippingMethodQueryImpl();
    }

    default ShippingMethodQuery byName(final String name) {
        return withPredicates(ShippingMethodQueryModel.of().name().is(name));
    }

    default ShippingMethodQuery byTaxCategory(final Referenceable<TaxCategory> taxCategory) {
        return withPredicates(m -> m.taxCategory().is(taxCategory));
    }

    default ShippingMethodQuery byIsDefault() {
        return withPredicates(m -> m.isDefault().is(true));
    }
}
