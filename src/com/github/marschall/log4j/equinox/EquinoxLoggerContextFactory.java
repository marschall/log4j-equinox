package com.github.marschall.log4j.equinox;

import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;

/**
 * Equinox specific {@link LoggerContextFactory} implementation.
 */
public final class EquinoxLoggerContextFactory implements LoggerContextFactory {

  private final Lock serviceLock;
  private LoggerContext loggerContext;

  /**
   * Default constructor called by Log4j2
   */
  public EquinoxLoggerContextFactory() {
    this.serviceLock = new ReentrantLock();
  }

  @Override
  public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext) {
    return this.getContext();
  }

  @Override
  public LoggerContext getContext(String fqcn, ClassLoader loader, Object externalContext, boolean currentContext,
      URI configLocation, String name) {
    return this.getContext();
  }

  private LoggerContext getContext() {
    this.serviceLock.lock();
    try {
      if (this.loggerContext == null) {
        this.loggerContext = new EquinoxLoggerContext(EquinoxLog4jBundleActivator.getDefaultInstance().getExtendedLogService());
      }
      return this.loggerContext;
    } finally {
      this.serviceLock.unlock();
    }
  }

  @Override
  public boolean isClassLoaderDependent() {
    // context is always used
    return false;
  }

  @Override
  public void removeContext(LoggerContext context) {
  }

}
