package net.waryss.mtools.bluepurse.ws.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.paths.SwaggerPathProvider;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;

@Configuration
@EnableSwagger
public class SwaggerConfig extends SwaggerPathProvider{
	private SpringSwaggerConfig springSwaggerConfig;
	private @Value("${api.title}") String title;
	private @Value("${api.version}") String version;
	private @Value("${api.contact}") String contact;
	private @Value("${api.license}") String license;
	private @Value("${api.appPath}") String appPath;
	private @Value("${api.basePath}") String basePath;
	private @Value("${api.licenseUrl}") String licenseUrl;
	private @Value("${api.description}") String description;
	private @Value("${api.includePatterns}") String includePatterns;
	private @Value("${api.termsOfServiceUrl}") String termsOfServiceUrl;

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(title, description, termsOfServiceUrl, contact, license, licenseUrl);
		return apiInfo;
	}

	@Override
	protected String applicationPath() {
		return appPath;
	}

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(springSwaggerConfig).apiInfo(apiInfo()).includePatterns(includePatterns)
				.apiVersion(version).pathProvider(this);
	}

	@Override
	protected String getDocumentationPath() {
		return basePath;
	}

	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}


}