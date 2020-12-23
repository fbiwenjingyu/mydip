package com.bs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bs.platform.core.boot.DipPlatformApp;

@SpringBootApplication()
@EnableAutoConfiguration
public class DemoApplication {
  public static void main(String[] args) {
	  DipPlatformApp.run(DemoApplication.class, args);
  }
}
