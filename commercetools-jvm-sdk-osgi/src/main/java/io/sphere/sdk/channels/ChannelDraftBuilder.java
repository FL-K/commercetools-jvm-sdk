package io.sphere.sdk.channels;

import io.sphere.sdk.models.Address;
import io.sphere.sdk.models.Base;
import io.sphere.sdk.models.Builder;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.types.CustomFieldsDraft;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;

/**
 * Builder for {@link ChannelDraft}.
 */
public final class ChannelDraftBuilder extends Base implements Builder<ChannelDraftDsl> {
    private final String key;
    private Set<ChannelRole> roles = Collections.emptySet();
    @Nullable
    private LocalizedString name;
    @Nullable
    private LocalizedString description;
    @Nullable
    private CustomFieldsDraft custom;
    @Nullable
    private Address address;

    private ChannelDraftBuilder(final String key) {
        this.key = key;
    }

    public static ChannelDraftBuilder of(final String key) {
        return new ChannelDraftBuilder(key);
    }

    public static ChannelDraftBuilder of(final ChannelDraft template) {
        return new ChannelDraftBuilder(template.getKey())
                .roles(template.getRoles())
                .name(template.getName())
                .description(template.getDescription())
                .custom(template.getCustom())
                .address(template.getAddress());
    }

    public ChannelDraftBuilder description(@Nullable final LocalizedString description) {
        this.description = description;
        return this;
    }
    
    public ChannelDraftBuilder name(@Nullable final LocalizedString name) {
        this.name = name;
        return this;
    }

    public ChannelDraftBuilder roles(final Set<ChannelRole> roles) {
        this.roles = roles;
        return this;
    }

    public ChannelDraftBuilder custom(@Nullable final CustomFieldsDraft custom) {
        this.custom = custom;
        return this;
    }

    public ChannelDraftBuilder address(@Nullable final Address address) {
        this.address = address;
        return this;
    }

    @Override
    public ChannelDraftDsl build() {
        return new ChannelDraftDsl(key, roles, name, description, custom, address);
    }
}
