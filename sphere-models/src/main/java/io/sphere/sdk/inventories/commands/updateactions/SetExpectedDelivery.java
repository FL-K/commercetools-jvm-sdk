package io.sphere.sdk.inventories.commands.updateactions;

import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.inventories.InventoryEntry;

import javax.annotation.Nullable;
import java.time.ZonedDateTime;

/**
 *
 * {@include.example io.sphere.sdk.inventories.commands.InventoryEntryUpdateCommandTest#setExpectedDelivery()}
 */
public class SetExpectedDelivery extends UpdateAction<InventoryEntry> {
    @Nullable
    private final ZonedDateTime expectedDelivery;

    private SetExpectedDelivery(@Nullable final ZonedDateTime expectedDelivery) {
        super("setExpectedDelivery");
        this.expectedDelivery = expectedDelivery;
    }

    @Nullable
    public ZonedDateTime getExpectedDelivery() {
        return expectedDelivery;
    }

    public static SetExpectedDelivery of(@Nullable final ZonedDateTime expectedDelivery) {
        return new SetExpectedDelivery(expectedDelivery);
    }
}