package io.sphere.sdk.test;

import com.neovisionaries.i18n.CountryCode;
import io.sphere.sdk.models.*;
import io.sphere.sdk.queries.PagedQueryResult;

import javax.money.CurrencyUnit;
import javax.money.MonetaryCurrencies;
import java.util.*;

import static io.sphere.sdk.utils.IterableUtils.toStream;
import static java.util.stream.Collectors.toList;

public final class SphereTestUtils {
    private static final Random random = new Random();

    private SphereTestUtils() {
        //pure utility class
    }

    public static final Locale GERMAN = Locale.GERMAN;
    public static final Locale ENGLISH = Locale.ENGLISH;

    public static final CountryCode DE = CountryCode.DE;
    public static final CountryCode GB = CountryCode.GB;
    public static final CountryCode US = CountryCode.US;

    public static final CurrencyUnit EUR = DefaultCurrencyUnits.EUR;

    /**
     * Creates a LocalizedString for the {@code Locale.ENGLISH}.
     * @param value the value of the english translation
     * @return localized string with value
     */
    public static LocalizedString en(final String value) {
        return LocalizedString.of(ENGLISH, value);
    }

    public static String en(final Optional<LocalizedString> localizedStringOption) {
        return localizedStringOption.get().get(ENGLISH).get();
    }

    public static String englishSlugOf(final WithLocalizedSlug model) {
        return model.getSlug().get(ENGLISH).get();
    }

    public static <T> T firstOf(final PagedQueryResult<T> result) {
        return result.head().get();
    }

    public static LocalizedString randomSlug() {
        return LocalizedString.of(ENGLISH, "random-slug-" + random.nextInt());
    }

    public static String randomString() {
        return "random string " + random.nextInt();
    }

    public static MetaAttributes randomMetaAttributes() {
        final String metaTitle = "meta title" + randomString();
        final String metaDescription = "meta description" + randomString();
        final String metaKeywords = "meta keywords," + randomString();
        return MetaAttributes.metaAttributesOf(ENGLISH, metaTitle, metaDescription, metaKeywords);
    }

    public static <T> List<String> toIds(final Iterable<? extends Identifiable<T>> elements) {
        return toStream(elements).map(element -> element.getId()).collect(toList());
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> asList(T... a) {
        return Arrays.asList(a);
    }
}
