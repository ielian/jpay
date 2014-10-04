TDD on osgi
http://www.slideshare.net/ielian/tdd-on-osgi

The Sample project demonstrate a layered project that can be deployed
on an OSGI container like Karaf 2.3.3
without compromising its ability to run on an embedded container like jetty hosted inside an IDE
or as part of a build and test cycle performed by maven.

The project consists of these modules:

- jpay-core: model classes and service interfaces used by different modules
- jpay-service: Service implementation and logic to publish the event service using CXF REST and SPRING
- jpay-webui: Provides a simple servlet based webui to list and add events. Uses the OSGi service

Build and Test standalone
-------------------------
> mvn clean install
> mvn cobertura:cobertura (to run code coverage)
> mvn jetty:run (to run webui)

Install Karaf 2.3.3
----------------------------------
Download Apache Karaf here: http://karaf.apache.org/index/community/download.html
check ${KARAF_HOME}/etc/org.ops4j.pax.web.cfg for default jetty port
>org.osgi.service.http.port=8181
