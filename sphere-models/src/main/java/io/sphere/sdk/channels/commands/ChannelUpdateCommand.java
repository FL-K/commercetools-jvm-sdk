package io.sphere.sdk.channels.commands;

import io.sphere.sdk.channels.Channel;
import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.commands.UpdateCommandDsl;
import io.sphere.sdk.models.Versioned;

import java.util.Collections;
import java.util.List;

/**
 {@doc.gen list actions}
 */
public interface ChannelUpdateCommand extends UpdateCommandDsl<Channel, ChannelUpdateCommand> {
    static ChannelUpdateCommand of(final Versioned<Channel> versioned, final List<? extends UpdateAction<Channel>> updateActions) {
        return new ChannelUpdateCommandImpl(versioned, updateActions);
    }

    static ChannelUpdateCommand of(final Versioned<Channel> versioned, final UpdateAction<Channel> updateAction) {
        return of(versioned, Collections.singletonList(updateAction));
    }
}
