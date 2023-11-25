package com.github.marschall.log4j.equinox;

import org.eclipse.equinox.log.ExtendedLogService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Bundle activator whose sole purpose is to give access to {@link BundleContext}.
 */
public final class EquinoxLog4jBundleActivator implements BundleActivator {

  private static volatile EquinoxLog4jBundleActivator defaultInstance;

  private volatile ServiceTracker<?, ExtendedLogService> serviceTracker;

  /**
   * Default constructor to be called by OSGi.
   */
  public EquinoxLog4jBundleActivator() {
    defaultInstance = this;
  }

  @Override
  public void start(BundleContext context) {
    this.serviceTracker = new ServiceTracker<>(context, ExtendedLogService.class, null);
    this.serviceTracker.open();
  }

  @Override
  public void stop(BundleContext context) {
    this.serviceTracker.close();
  }

  ExtendedLogService getExtendedLogService() {
    return this.serviceTracker.getService();
  }

  static EquinoxLog4jBundleActivator getDefaultInstance() {
    return defaultInstance;
  }

}
