package com.example;

import java.util.ArrayList;
import java.util.List;
import waffle.servlet.spi.NegotiateSecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;
import waffle.windows.auth.impl.WindowsAuthProviderImpl;

public class WaffleProvider {

  public NegotiateSecurityFilter getNegotiateSecurityFilter() {
    NegotiateSecurityFilterProvider negotiateSecurityFilterProvider =
        new NegotiateSecurityFilterProvider(new WindowsAuthProviderImpl());

    List<SecurityFilterProvider> securityFilterProviders = new ArrayList<>();
    securityFilterProviders.add(negotiateSecurityFilterProvider);

    SecurityFilterProviderCollection securityFilterProviderCollection =
        new SecurityFilterProviderCollection(
            securityFilterProviders.toArray(new SecurityFilterProvider[0]));

    NegotiateSecurityFilter negotiateSecurityFilter = new NegotiateSecurityFilter();
    negotiateSecurityFilter.setProvider(securityFilterProviderCollection);

    return negotiateSecurityFilter;
  }

  public NegotiateSecurityFilterEntryPoint getNegotiateSecurityFilterEntryPoint() {
    NegotiateSecurityFilterProvider negotiateSecurityFilterProvider =
        new NegotiateSecurityFilterProvider(new WindowsAuthProviderImpl());

    List<SecurityFilterProvider> securityFilterProviders = new ArrayList<>();
    securityFilterProviders.add(negotiateSecurityFilterProvider);

    SecurityFilterProviderCollection securityFilterProviderCollection =
        new SecurityFilterProviderCollection(
            securityFilterProviders.toArray(new SecurityFilterProvider[0]));

    NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint =
        new NegotiateSecurityFilterEntryPoint();
    negotiateSecurityFilterEntryPoint.setProvider(securityFilterProviderCollection);
    return negotiateSecurityFilterEntryPoint;
  }
}
