package com.github.marschall.log4j.equinox;

import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.eclipse.equinox.log.ExtendedLogService;


final class EquinoxLoggerContext implements LoggerContext {

  private final ExtendedLogService logService;
  private final LoggerRegistry<ExtendedLogger> loggerRegistry;


  EquinoxLoggerContext(ExtendedLogService logService) {
    this.logService = logService;
    this.loggerRegistry = new LoggerRegistry<>();
  }

  @Override
  public Object getExternalContext() {
    return null;
  }

  @Override
  public ExtendedLogger getLogger(String name) {
    if (!loggerRegistry.hasLogger(name)) {
      loggerRegistry.putIfAbsent(name, null, new EquinoxLogger(name, this.logService.getLogger(name)));
    }
    return loggerRegistry.getLogger(name);
  }

  @Override
  public ExtendedLogger getLogger(String name, MessageFactory messageFactory) {
    if (!loggerRegistry.hasLogger(name, messageFactory)) {
      loggerRegistry.putIfAbsent(name, messageFactory,
          new EquinoxLogger(name, messageFactory, this.logService.getLogger(name)));
    }
    return loggerRegistry.getLogger(name, messageFactory);
  }

  @Override
  public boolean hasLogger(String name) {
    return loggerRegistry.hasLogger(name);
  }

  @Override
  public boolean hasLogger(String name, MessageFactory messageFactory) {
    return loggerRegistry.hasLogger(name, messageFactory);
  }

  @Override
  public boolean hasLogger(String name, Class<? extends MessageFactory> messageFactoryClass) {
    return loggerRegistry.hasLogger(name, messageFactoryClass);
  }

}
