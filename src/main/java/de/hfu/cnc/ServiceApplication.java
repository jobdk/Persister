package de.hfu.cnc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@RestController
public class ServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceApplication.class);
	Counter requestCounter;
	public ServiceApplication(MeterRegistry meterRegistry) {
		LOG.info("Constructor called");
		this.requestCounter = meterRegistry.counter("service_requests");
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "Cloud Native Computing reverted!";
	}

    @RequestMapping("/host.html")
	public String getHostName() {
		LOG.warn("Pre Counter Increment: " + requestCounter.count());
		requestCounter.increment();
		LOG.info("Post Counter Increment: " + requestCounter.count());

		try {
			String hostName = InetAddress.getLocalHost().getHostName();
			LOG.info("Get HostName called: " + hostName);
			return hostName;
		} catch (UnknownHostException e) {
			LOG.error("Could not get HostName", e);
			return "Could not find Hostname";
		}
	}
}