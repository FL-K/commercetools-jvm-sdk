package io.sphere.sdk.customers.queries;

import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.customers.expansion.CustomerExpansionModel;
import io.sphere.sdk.queries.MetaModelQueryDslBuilder;
import io.sphere.sdk.queries.MetaModelQueryDslImpl;

class CustomerInStoreQueryImpl extends MetaModelQueryDslImpl<Customer, CustomerInStoreQuery, CustomerQueryModel, CustomerExpansionModel<Customer>> implements CustomerInStoreQuery {

    CustomerInStoreQueryImpl(final String storeKey) {
        super("/in-store/key=" + storeKey + "/customers", CustomerQuery.resultTypeReference(), CustomerQueryModel.of(), CustomerExpansionModel.of(), CustomerInStoreQueryImpl::new);
    }   

    private CustomerInStoreQueryImpl(final MetaModelQueryDslBuilder<Customer, CustomerInStoreQuery, CustomerQueryModel, CustomerExpansionModel<Customer>> builder) {
        super(builder);
    }
    
}
