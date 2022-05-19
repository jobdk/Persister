package de.hfu.cnc;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@SpringBootApplication
@RestController
public class ServiceApplication {

	private final UserRepository repository;
	private static final Logger LOG = LoggerFactory.getLogger(ServiceApplication.class);
	Counter requestCounter;

	@Autowired
	public ServiceApplication(MeterRegistry meterRegistry, UserRepository repository) {
		LOG.info("Constructor called");
		this.requestCounter = meterRegistry.counter("service_requests");
		this.repository = repository;
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

	@GetMapping("/users")
	public List<User> user() {
		return repository.findAll();
	}
	@PostMapping("/users")
	public void addUser(@RequestBody User user) {
		repository.insert(user);
	}
	@DeleteMapping("/users")
	public void deleteUser(@RequestParam("id") String id) {
		repository.deleteById(id);
	}
}