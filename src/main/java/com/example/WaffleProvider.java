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

  private final SecurityFilterProviderCollection securityFilterProviderCollection =
      getSecurityFilterProviderCollection();

  public NegotiateSecurityFilter getNegotiateSecurityFilter() {
    NegotiateSecurityFilter negotiateSecurityFilter = new NegotiateSecurityFilter();
    negotiateSecurityFilter.setProvider(securityFilterProviderCollection);
    return negotiateSecurityFilter;
  }

  public NegotiateSecurityFilterEntryPoint getNegotiateSecurityFilterEntryPoint() {
    NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint =
        new NegotiateSecurityFilterEntryPoint();
    negotiateSecurityFilterEntryPoint.setProvider(securityFilterProviderCollection);
    return negotiateSecurityFilterEntryPoint;
  }

  public SecurityFilterProviderCollection getSecurityFilterProviderCollection() {
    NegotiateSecurityFilterProvider negotiateSecurityFilterProvider =
        new NegotiateSecurityFilterProvider(new WindowsAuthProviderImpl());

    List<SecurityFilterProvider> securityFilterProviders = new ArrayList<>();
    securityFilterProviders.add(negotiateSecurityFilterProvider);

    return new SecurityFilterProviderCollection(
        securityFilterProviders.toArray(new SecurityFilterProvider[0]));
  }
}
