package com.deloitte.nextgen.framework.rt;


import com.deloitte.nextgen.framework.rt.controller.ReferenceTableController;
import com.deloitte.nextgen.framework.rt.dao.ReferenceTableDAOImpl;
import com.deloitte.nextgen.framework.rt.service.ReferenceTableServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * The Class ReferenceTableServiceApplication.
 */
@SpringBootApplication
@Slf4j
//@EnableCaching
@ComponentScan(basePackageClasses =
        {
                //CachingConfig.class,
                ReferenceTableController.class,
                ReferenceTableServiceImpl.class,
                ReferenceTableDAOImpl.class
        }
        )
public class ReferenceTableServiceApplication {

	public static void main(String[] args) {

        //clearRedisCache
        log.info("Starting with {}", "ReferenceTableServiceApplication");
        SpringApplication app = new SpringApplication(ReferenceTableServiceApplication.class);
        Environment env = app.run(args).getEnvironment();
        logApplicationStartup(env);
        log.info("Started ReferenceTableServiceApplication");
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        if (serverPort == null) {
            serverPort = "8080";
        }
        String contextPath = env.getProperty("server.servlet.context-path");

        if (contextPath == null) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n\tAccess URLs:\n\t" +
                        "\tLocal: \t\t{}://localhost:{}{}\n\t" +
                        "\tExternal: \t{}://{}:{}{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                env.getActiveProfiles());
    }

}
