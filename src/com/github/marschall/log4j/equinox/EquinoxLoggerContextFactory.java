package com.github.marschall.log4j.equinox;

import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.eclipse.equinox.log.ExtendedLogService;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleException;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;


public final class EquinoxLoggerContextFactory implements LoggerContextFactory {

  private final ServiceTracker<?, ExtendedLogService> serviceTracker;
  private final Lock serviceLock;
  private LoggerContext loggerContext;

  public EquinoxLoggerContextFactory() {
    // this is a bit hairy
    // since we are a fragment and not a bundle we can't have an activator we have to work around this

    Bundle bundle = FrameworkUtil.getBundle(EquinoxLoggerContextFactory.class);
    // start the bundle so that we have a bundle context
    // maybe the bundle is not started because it has no Bundle-ActivationPolicy: lazy
    if (bundle.getState() == Bundle.RESOLVED) {
      try {
        bundle.start();
      } catch (BundleException e) {
        throw new RuntimeException("could not start bundle", e);
      }
    }
    // reimplement BundleActivator#start()
    BundleContext context = bundle.getBundleContext();
    this.serviceTracker =
        new ServiceTracker<>(context, ExtendedLogService.class, null);

    serviceTracker.open();
    // reimplement BundleActivator#stop()
    context.addBundleListener((BundleEvent event) -> {
      if (event.getBundle().getBundleId() == bundle.getBundleId()
          && event.getType() == BundleEvent.STOPPING) {
        serviceTracker.close();
      }
    });

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
        this.loggerContext = new EquinoxLoggerContext(this.serviceTracker.getService());
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
