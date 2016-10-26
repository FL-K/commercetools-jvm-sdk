package io.sphere.sdk.categories;

import io.sphere.sdk.models.*;
import io.sphere.sdk.types.CustomFieldsDraft;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * <p>Creates templates for new categories.</p>
 *
 *  {@include.example io.sphere.sdk.categories.commands.CategoryCreateCommandIntegrationTest#execution()}
 */
public final class CategoryDraftBuilder extends Base implements Builder<CategoryDraft> {
    private final LocalizedString name;
    private final LocalizedString slug;
    @Nullable
    private LocalizedString description;
    @Nullable
    private Reference<Category> parent;
    @Nullable
    private String orderHint;
    @Nullable
    private String externalId;
    @Nullable
    private CustomFieldsDraft custom;
    @Nullable
    private LocalizedString metaTitle;
    @Nullable
    private LocalizedString metaDescription;
    @Nullable
    private LocalizedString metaKeywords;    

    private CategoryDraftBuilder(final CategoryDraft d) {
        name = d.getName();
        slug = d.getSlug();
        description = d.getDescription();
        parent = d.getParent();
        orderHint = d.getOrderHint();
        externalId = d.getExternalId();
        custom = d.getCustom();
        metaTitle = d.getMetaTitle();
        metaDescription = d.getMetaDescription();
        metaKeywords = d.getMetaKeywords();
    }

    private CategoryDraftBuilder(final LocalizedString name, final LocalizedString slug) {
        this.name = name;
        this.slug = slug;
    }

    public static CategoryDraftBuilder of(final CategoryDraft categoryDraft) {
        return new CategoryDraftBuilder(categoryDraft);
    }

    public static CategoryDraftBuilder of(final LocalizedString name, final LocalizedString slug) {
        return new CategoryDraftBuilder(name, slug);
    }

    public CategoryDraftBuilder description(@Nullable final LocalizedString description) {
        this.description = description;
        return this;
    }

    public CategoryDraftBuilder parent(@Nullable final Referenceable<Category> parent) {
        this.parent = Optional.ofNullable(parent).map(Referenceable::toReference).orElse(null);
        return this;
    }

    public CategoryDraftBuilder orderHint(@Nullable final String orderHint) {
        this.orderHint = orderHint;
        return this;
    }
    
    public CategoryDraftBuilder externalId(@Nullable final String externalId) {
        this.externalId = externalId;
        return this;
    }

    public CategoryDraftBuilder custom(@Nullable final CustomFieldsDraft custom) {
        this.custom = custom;
        return this;
    }

    public CategoryDraftBuilder metaTitle(@Nullable final LocalizedString metaTitle) {
        this.metaTitle = metaTitle;
        return this;
    }

    public CategoryDraftBuilder metaDescription(@Nullable final LocalizedString metaDescription) {
        this.metaDescription = metaDescription;
        return this;
    }

    public CategoryDraftBuilder metaKeywords(@Nullable final LocalizedString metaKeywords) {
        this.metaKeywords = metaKeywords;
        return this;
    }

    public CategoryDraft build() {
        return new CategoryDraftImpl(name, slug, description, parent, orderHint, externalId, custom, metaTitle, metaDescription, metaKeywords);
    }
}
