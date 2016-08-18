package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.SocketUtils;

public class RandomPortPostProcessor  implements EnvironmentPostProcessor {
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		environment.getPropertySources().addFirst(new PropertySource<Object>("random") {

			@Override
			public Object getProperty(String name) {
				if("kafka.port".equals(name)) {
					return SocketUtils.findAvailableTcpPort();
				}
				return null;
			}});
	}
}
