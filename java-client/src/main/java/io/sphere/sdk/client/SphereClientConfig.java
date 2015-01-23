package io.sphere.sdk.client;

import io.sphere.sdk.models.Base;

import static io.sphere.sdk.client.ClientPackage.API_URL;
import static io.sphere.sdk.client.ClientPackage.AUTH_URL;

public class SphereClientConfig extends Base {
    private final String projectKey;
    private final String clientId;
    private final String clientSecret;
    private final String authUrl;
    private final String apiUrl;

    private SphereClientConfig(final String projectKey, final String clientId, final String clientSecret, final String authUrl, final String apiUrl) {
        this.apiUrl = apiUrl;
        this.projectKey = projectKey;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authUrl = authUrl;
    }

    public static SphereClientConfig of(final String projectKey, final String clientId, final String clientSecret) {
        return of(projectKey, clientId, clientSecret, AUTH_URL, API_URL);
    }

    public static SphereClientConfig of(final String projectKey, final String clientId, final String clientSecret, final String authUrl, final String apiUrl) {
        return new SphereClientConfig(projectKey, clientId, clientSecret, authUrl, apiUrl);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getProjectKey() {
        return projectKey;
    }
}
