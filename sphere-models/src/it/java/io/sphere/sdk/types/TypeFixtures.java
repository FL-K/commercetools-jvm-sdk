package io.sphere.sdk.types;

import io.sphere.sdk.carts.Cart;
import io.sphere.sdk.carts.CustomLineItem;
import io.sphere.sdk.carts.LineItem;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.client.TestClient;
import io.sphere.sdk.customers.Customer;
import io.sphere.sdk.models.EnumValue;
import io.sphere.sdk.models.LocalizedEnumValue;
import io.sphere.sdk.models.TextInputHint;
import io.sphere.sdk.orders.Order;
import io.sphere.sdk.payments.Payment;
import io.sphere.sdk.payments.commands.updateactions.AddInterfaceInteraction;
import io.sphere.sdk.products.Price;
import io.sphere.sdk.types.commands.TypeCreateCommand;
import io.sphere.sdk.types.commands.TypeDeleteCommand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.UnaryOperator;

import static io.sphere.sdk.test.SphereTestUtils.*;
import static java.util.stream.Collectors.toList;

public class TypeFixtures {

    public static final String LOC_STRING_FIELD_NAME = "locstring-field-name";
    public static final String BOOLEAN_FIELD_NAME = "bool-field-name";
    public static final String DATE_FIELD_NAME = "date-field-name";
    public static final String TIME_FIELD_NAME = "time-field-name";
    public static final String DATETIME_FIELD_NAME = "datetime-field-name";
    public static final String MONEY_FIELD_NAME = "money-field-name";
    public static final String INT_FIELD_NAME = "int-field-name";
    public static final String DOUBLE_FIELD_NAME = "double-field-name";
    public static final String ENUM_FIELD_NAME = "enum-field-name";
    public static final String CAT_REFERENCE_FIELD_NAME = "catref";
    public static final String LOCALIZED_ENUM_FIELD_NAME = "localized-enum-field-name";
    public static final Set<String> TYPE_IDS = new HashSet<>(asList(Category.resourceTypeId(), Customer.resourceTypeId(), Cart.resourceTypeId(), Order.resourceTypeId(), LineItem.resourceTypeId(), CustomLineItem.resourceTypeId(), Payment.resourceTypeId(), AddInterfaceInteraction.resourceTypeId(), Price.resourceTypeId()));
    public static final String STRING_FIELD_NAME = "string-field-name";
    public static final String TYPE_NAME = "name of the custom type";

    public static void withUpdateableType(final TestClient client, final UnaryOperator<Type> operator) {
        final String typeKey = randomKey();
        final TypeDraft typeDraft = TypeDraftBuilder.of(typeKey, en(TYPE_NAME), TYPE_IDS)
                .description(en("description"))
                .fieldDefinitions(asList(stringfieldDefinition(), enumFieldDefinition(), localizedEnumFieldDefinition(), catRefDefinition(),
                        booleanDefinition(), LocalizedStringDefinition(), intDefinition(), doubleDefinition(), moneyDefinition(),
                        dateDefinition(), dateTimeDefinition(), timeDefinition()))
                .build();
        final Type type = client.execute(TypeCreateCommand.of(typeDraft));
        final Type updatedType = operator.apply(type);
        client.execute(TypeDeleteCommand.of(updatedType));
    }

    private static FieldDefinition dateDefinition() {
        return fieldDefinition(DateType.of(), DATE_FIELD_NAME);
    }

    private static FieldDefinition dateTimeDefinition() {
        return fieldDefinition(DateTimeType.of(), DATETIME_FIELD_NAME);
    }

    private static FieldDefinition timeDefinition() {
        return fieldDefinition(TimeType.of(), TIME_FIELD_NAME);
    }

    private static FieldDefinition booleanDefinition() {
        return fieldDefinition(BooleanType.of(), BOOLEAN_FIELD_NAME);
    }

    private static FieldDefinition moneyDefinition() {
        return fieldDefinition(MoneyType.of(), MONEY_FIELD_NAME);
    }

    private static FieldDefinition LocalizedStringDefinition() {
        return fieldDefinition(LocalizedStringType.of(), LOC_STRING_FIELD_NAME);
    }

    private static FieldDefinition catRefDefinition() {
        return fieldDefinition(ReferenceType.of(Category.referenceTypeId()), CAT_REFERENCE_FIELD_NAME);
    }

    private static FieldDefinition intDefinition() {
        return fieldDefinition(NumberType.of(), INT_FIELD_NAME);
    }

    private static FieldDefinition doubleDefinition() {
        return fieldDefinition(NumberType.of(), DOUBLE_FIELD_NAME);
    }

    private static FieldDefinition localizedEnumFieldDefinition() {
        final List<LocalizedEnumValue> localizedEnumValues = asList("1", "2").stream()
                .map(s -> LocalizedEnumValue.of("key" + s, en("label " + s)))
                .collect(toList());
        return fieldDefinition(LocalizedEnumType.of(localizedEnumValues), LOCALIZED_ENUM_FIELD_NAME);
    }

    private static FieldDefinition enumFieldDefinition() {
        final List<EnumValue> enumValues = asList(EnumValue.of("key1", "label1"), EnumValue.of("key2", "label2"));
        return fieldDefinition(EnumType.of(enumValues), ENUM_FIELD_NAME);
    }

    private static FieldDefinition stringfieldDefinition() {
        return fieldDefinition(StringType.of(), STRING_FIELD_NAME);
    }

    private static FieldDefinition fieldDefinition(final FieldType fieldType, final String fieldName) {
        return FieldDefinition
                .of(fieldType, fieldName, en(fieldName), false, TextInputHint.SINGLE_LINE);
    }
}