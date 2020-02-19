package br.com.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.dekorate.kubernetes.annotation.Env;
import io.dekorate.openshift.annotation.OpenshiftApplication;
import io.dekorate.option.annotation.JvmOptions;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenshiftApplication(replicas = 2, expose = true, envVars = { @Env(name = "api-city", configmap = "api-city") })
@JvmOptions(xms = 128, xmx = 256, heapDumpOnOutOfMemoryError = true)
@EnableSwagger2
@SpringBootApplication
public class ExecuteApp {

	public static void main(String[] args) {
		SpringApplication.run(ExecuteApp.class, args);
	}
}
