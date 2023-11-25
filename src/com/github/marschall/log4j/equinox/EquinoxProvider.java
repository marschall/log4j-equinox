package com.github.marschall.log4j.equinox;

import org.apache.logging.log4j.spi.Provider;

/**
 * Bind the Log4j API to Equinox.
 */
public final class EquinoxProvider extends Provider {

  /**
   * Default constructor called by service loader.
   */
  public EquinoxProvider() {
    super(16, "2.7.2", EquinoxLoggerContextFactory.class);
  }

}
