package io.sphere.sdk.customers.commands.updateactions;

import io.sphere.sdk.commands.UpdateAction;
import io.sphere.sdk.customers.Customer;

import javax.annotation.Nullable;
import java.time.LocalDate;

/**
 * Sets a date of birth for the customer.
 *
 * {@include.example io.sphere.sdk.customers.commands.CustomerUpdateCommandTest#setDateOfBirth()}
 */
public class SetDateOfBirth extends UpdateAction<Customer> {
    @Nullable
    private final LocalDate dateOfBirth;

    private SetDateOfBirth(@Nullable final LocalDate dateOfBirth) {
        super("setDateOfBirth");
        this.dateOfBirth = dateOfBirth;
    }

    public static SetDateOfBirth of(@Nullable final LocalDate dateOfBirth) {
        return new SetDateOfBirth(dateOfBirth);
    }

    @Nullable
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}