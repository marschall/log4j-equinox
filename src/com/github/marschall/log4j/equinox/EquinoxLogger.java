package com.github.marschall.log4j.equinox;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;

final class EquinoxLogger extends AbstractLogger {

  private final org.eclipse.equinox.log.Logger logger;

  EquinoxLogger(String name, MessageFactory messageFactory, org.eclipse.equinox.log.Logger logger) {
    super(name, messageFactory);
    this.logger = logger;
  }
  
  EquinoxLogger(String name, org.eclipse.equinox.log.Logger logger) {
    super(name);
    this.logger = logger;
  }

  @Override
  public boolean isDebugEnabled() {
    return this.logger.isDebugEnabled();
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return this.logger.isDebugEnabled();
  }

  @Override
  public boolean isErrorEnabled() {
    return this.logger.isErrorEnabled();
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return this.logger.isErrorEnabled();
  }

  @Override
  public boolean isFatalEnabled() {
    return this.logger.isErrorEnabled();
  }

  @Override
  public boolean isFatalEnabled(Marker marker) {
    return this.logger.isErrorEnabled();
  }

  @Override
  public boolean isInfoEnabled() {
    return this.logger.isInfoEnabled();
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return this.logger.isInfoEnabled();
  }

  @Override
  public boolean isTraceEnabled() {
    return this.logger.isTraceEnabled();
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return this.logger.isTraceEnabled();
  }

  @Override
  public boolean isWarnEnabled() {
    return this.logger.isWarnEnabled();
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return this.logger.isWarnEnabled();
  }

  @Override
  public boolean isEnabled(Level level) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, Message message, Throwable t) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, CharSequence message, Throwable t) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, Object message, Throwable t) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Throwable t) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object... params) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4, Object p5) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4, Object p5, Object p6) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4, Object p5, Object p6, Object p7) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4, Object p5, Object p6, Object p7, Object p8) {
    return this.isEnabledFor(level);
  }

  @Override
  public boolean isEnabled(Level level, Marker marker, String message, Object p0, Object p1, Object p2, Object p3,
      Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
    return this.isEnabledFor(level);
  }

  private boolean isEnabledFor(Level level) {
    switch (level.getStandardLevel()) {
      case TRACE:
        return this.logger.isTraceEnabled();
      case DEBUG:
        return this.logger.isDebugEnabled();
      case INFO:
        return this.logger.isInfoEnabled();
      case WARN:
        return this.logger.isWarnEnabled();
      case ERROR:
      case FATAL:
        return this.logger.isErrorEnabled();
      default :
        return this.logger.isErrorEnabled();
    }
  }

  @Override
  public void logMessage(String fqcn, Level level, Marker marker, Message message, Throwable t) {
    String formattedMessage = message.getFormattedMessage();
    if (message instanceof LoggerNameAwareMessage) {
      ((LoggerNameAwareMessage) message).setLoggerName(getName());
    }
    switch (level.getStandardLevel()) {
      case DEBUG:
        logger.debug(formattedMessage, t);
        break;
      case TRACE:
        logger.trace(formattedMessage, t);
        break;
      case INFO:
        logger.info(formattedMessage, t);
        break;
      case WARN:
        logger.warn(formattedMessage, t);
        break;
      case ERROR:
      case FATAL:
        logger.error(formattedMessage, t);
        break;
      default :
        logger.error(formattedMessage, t);
        break;
    }
  }

  @Override
  public Level getLevel() {
    if (this.logger.isTraceEnabled()) {
      return Level.TRACE;
    }
    if (this.logger.isDebugEnabled()) {
      return Level.DEBUG;
    }
    if (this.logger.isInfoEnabled()) {
      return Level.INFO;
    }
    if (this.logger.isWarnEnabled()) {
      return Level.WARN;
    }
    if (this.logger.isErrorEnabled()) {
      return Level.ERROR;
    }
    // Option: throw new IllegalStateException("Unknown Equinox level");
    // Option: return Level.ALL;
    return Level.OFF;
  }

}
