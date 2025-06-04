package com.example;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import waffle.servlet.spi.NegotiateSecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;
import waffle.windows.auth.impl.WindowsAuthProviderImpl;

@Component
public class BeanProvider {

  @Bean
  public WindowsAuthProviderImpl waffleWindowsAuthProvider() {
    return new WindowsAuthProviderImpl();
  }

  @Bean
  public NegotiateSecurityFilterProvider negotiateSecurityFilterProvider(
      WindowsAuthProviderImpl windowsAuthProvider) {
    return new NegotiateSecurityFilterProvider(windowsAuthProvider);
  }

  @Bean
  public SecurityFilterProviderCollection waffleSecurityFilterProviderCollection(
      NegotiateSecurityFilterProvider negotiateSecurityFilterProvider) {
    List<SecurityFilterProvider> securityFilterProviders = new ArrayList<>();
    securityFilterProviders.add(negotiateSecurityFilterProvider);
    return new SecurityFilterProviderCollection(
        securityFilterProviders.toArray(new SecurityFilterProvider[0]));
  }

  @Bean
  public NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint(
      SecurityFilterProviderCollection securityFilterProviderCollection) {
    NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint =
        new NegotiateSecurityFilterEntryPoint();
    negotiateSecurityFilterEntryPoint.setProvider(securityFilterProviderCollection);
    return negotiateSecurityFilterEntryPoint;
  }

  @Bean
  public NegotiateSecurityFilter waffleNegotiateSecurityFilter(
      SecurityFilterProviderCollection securityFilterProviderCollection) {
    NegotiateSecurityFilter negotiateSecurityFilter = new NegotiateSecurityFilter();
    negotiateSecurityFilter.setProvider(securityFilterProviderCollection);
    return negotiateSecurityFilter;
  }
}
