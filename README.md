Log4j Equinox [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/com.github.marschall.log4j-equinox/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/com.github.marschall.log4j-equinox)  [![Javadocs](https://www.javadoc.io/badge/com.github.marschall/com.github.marschall.log4j-equinox.svg)](https://www.javadoc.io/doc/com.github.marschall/com.github.marschall.log4j-equinox)
=====================

This is an implementation of [Log4j 2 API](https://logging.apache.org/log4j/2.x/log4j-api/index.html) using the [Equinox](https://www.eclipse.org/equinox/) [ExtendedLogService](https://bugs.eclipse.org/bugs/show_bug.cgi?id=260672).

In plain words it makes all code that uses Log4j 2 API log to the Equinox `.metadata/.log` log file. It does this by redirecting all the log messages to the Equinox `ExtendedLogService`. This is mostly interesting for code that runs inside Eclipse RCP applications. This does _not_ make Equinox use Log4j 2. 

Simply deploy this along the Log4j 4 API bundle (`org.apache.logging.log4j.api`).
