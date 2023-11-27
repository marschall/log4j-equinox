package com.github.marschall.log4j.equinox;

import org.apache.logging.log4j.spi.NoOpThreadContextMap;
import org.apache.logging.log4j.spi.Provider;

/**
 * Bind the Log4j API to Equinox.
 */
public final class EquinoxProvider extends Provider {

  /**
   * Default constructor called by service loader.
   */
  public EquinoxProvider() {
    // see org.apache.logging.log4j.util.ProviderUtil.COMPATIBLE_API_VERSIONS
    super(16, "2.6.0", EquinoxLoggerContextFactory.class, NoOpThreadContextMap.class);
  }

}
