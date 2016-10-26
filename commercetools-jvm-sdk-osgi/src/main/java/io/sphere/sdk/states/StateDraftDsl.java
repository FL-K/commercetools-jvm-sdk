package io.sphere.sdk.states;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.sphere.sdk.models.Base;
import io.sphere.sdk.models.LocalizedString;
import io.sphere.sdk.models.Reference;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.Set;


public final class StateDraftDsl extends Base implements StateDraft {
    private final String key;
    private final StateType type;
    @Nullable
    private final LocalizedString name;
    @Nullable
    private final LocalizedString description;
    @Nullable
    private final Boolean initial;
    @Nullable
    private final Set<Reference<State>> transitions;
    @Nullable
    private final Set<StateRole> roles;

    @JsonCreator
    StateDraftDsl(final String key, final StateType type, @Nullable final LocalizedString name,
                  @Nullable final LocalizedString description,
                  @Nullable final Boolean initial, @Nullable final Set<Reference<State>> transitions,
                  @Nullable final Set<StateRole> roles) {
        this.key = key;
        this.type = type;
        this.name = name;
        this.description = description;
        this.initial = initial;
        this.transitions = transitions;
        this.roles = roles;
    }

    public static StateDraftDsl of(final String key, final StateType type) {
        return new StateDraftDsl(key, type, null, null, null, null, null);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public StateType getType() {
        return type;
    }

    @Override
    @Nullable
    public LocalizedString getName() {
        return name;
    }

    @Override
    @Nullable
    public LocalizedString getDescription() {
        return description;
    }

    @Override
    @Nullable
    public Boolean isInitial() {
        return initial;
    }

    @Override
    @Nullable
    public Set<Reference<State>> getTransitions() {
        return transitions;
    }

    @Override
    @Nullable
    public Set<StateRole> getRoles() {
        return roles;
    }

    public StateDraftDsl withName(final LocalizedString name) {
        return StateDraftBuilder.of(this).name(name).build();
    }

    public StateDraftDsl withDescription(final LocalizedString description) {
        return StateDraftBuilder.of(this).description(description).build();
    }

    public StateDraft withTransitions(@Nullable final Set<Reference<State>> transitions) {
        return StateDraftBuilder.of(this).transitions(transitions).build();
    }

    public StateDraftDsl withInitial(final boolean initial) {
        return StateDraftBuilder.of(this).initial(initial).build();
    }

    public StateDraft withRoles(final Set<StateRole> roles) {
        return StateDraftBuilder.of(this).roles(roles).build();
    }

    public StateDraft withRoles(final StateRole role) {
        return withRoles(Collections.singleton(role));
    }
}
