package io.sphere.sdk.cartdiscounts;

import io.sphere.sdk.models.Base;
import io.sphere.sdk.models.Builder;
import io.sphere.sdk.models.LocalizedString;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

/**
 * Builder for creating a {@link CartDiscountDraft}.
 *
 * @see CartDiscountDraft
 */
public final class CartDiscountDraftBuilder extends Base implements Builder<CartDiscountDraft> {
    private LocalizedString name;
    @Nullable
    private LocalizedString description;
    private CartDiscountValue value;
    private CartDiscountPredicate cartPredicate;
    private CartDiscountTarget target;
    private String sortOrder;
    private Boolean isActive = true;
    @Nullable
    private ZonedDateTime validFrom;
    @Nullable
    private ZonedDateTime validUntil;
    private Boolean requiresDiscountCode;

    private CartDiscountDraftBuilder(final LocalizedString name, final CartDiscountPredicate cartPredicate, final CartDiscountValue value, final CartDiscountTarget target, final String sortOrder, final Boolean requiresDiscountCode) {
        this.cartPredicate = cartPredicate;
        this.name = name;
        this.value = value;
        this.target = target;
        this.sortOrder = sortOrder;
        this.requiresDiscountCode = requiresDiscountCode;
    }

    public static CartDiscountDraftBuilder of(final CartDiscountDraft cartDiscountDraft) {
        return of(cartDiscountDraft.getName(), CartDiscountPredicate.of(cartDiscountDraft.getCartPredicate()), cartDiscountDraft.getValue(), cartDiscountDraft.getTarget(), cartDiscountDraft.getSortOrder(), cartDiscountDraft.isRequiresDiscountCode())
                .description(cartDiscountDraft.getDescription())
                .isActive(cartDiscountDraft.isActive())
                .validFrom(cartDiscountDraft.getValidFrom())
                .validUntil(cartDiscountDraft.getValidUntil());
    }

    public static CartDiscountDraftBuilder of(final LocalizedString name, final CartDiscountPredicate cartPredicate, final CartDiscountValue value, final CartDiscountTarget target, final String sortOrder, final boolean requiresDiscountCode) {
        return new CartDiscountDraftBuilder(name, cartPredicate, value, target, sortOrder, requiresDiscountCode);
    }

    public CartDiscountDraftBuilder description(@Nullable final LocalizedString description) {
        this.description = description;
        return this;
    }

    public CartDiscountDraftBuilder requiresDiscountCode(final boolean requiresDiscountCode) {
        this.requiresDiscountCode = requiresDiscountCode;
        return this;
    }

    public CartDiscountDraftBuilder sortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public CartDiscountDraftBuilder value(final CartDiscountValue value) {
        this.value = value;
        return this;
    }

    public CartDiscountDraftBuilder target(final CartDiscountTarget target) {
        this.target = target;
        return this;
    }

    public CartDiscountDraftBuilder name(final LocalizedString name) {
        this.name = name;
        return this;
    }

    public CartDiscountDraftBuilder active(final boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public CartDiscountDraftBuilder isActive(final boolean active) {
        return active(active);
    }

    public CartDiscountDraftBuilder cartPredicate(final CartDiscountPredicate cartPredicate) {
        this.cartPredicate = cartPredicate;
        return this;
    }

    public CartDiscountDraftBuilder validFrom(@Nullable final ZonedDateTime validFrom) {
        this.validFrom = validFrom;
        return this;
    }
    
    public CartDiscountDraftBuilder validUntil(@Nullable final ZonedDateTime validUntil) {
        this.validUntil = validUntil;
        return this;
    }

    @Override
    public CartDiscountDraft build() {
        return new CartDiscountDraftImpl(name, cartPredicate.toSphereCartPredicate(), description, value, target, sortOrder, isActive, validFrom, validUntil, requiresDiscountCode);
    }
}
