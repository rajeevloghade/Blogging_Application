package com.blog.swagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(SwaggerUI.class)
public class SwaggerConfig {

	/**
	 * API Docket bean to include API info, security contexts, and security schemes
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket api() {
		Set<String> protocols = new HashSet<String>();
		protocols.add("HTTP");
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.blog"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.securitySchemes(Arrays.asList(apiKey()))
				.protocols(protocols);
	}

	/**
	 * @return ApiInfo
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Blogging-Application")
				.description("Blogging Application reference for developers")
				.termsOfServiceUrl("")
				.contact(new Contact("Blogging-Application", "", "rajeevloghade010@gmail.com"))
				.license("Blogging Application License")
				.licenseUrl("rajeevloghade010@gmail.com")
				.version("1.0")
				.build();
	}

	/**
	 * ApiKey to include JWT as an Authorization header
	 * 
	 * @return ApiKey
	 */
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	@Bean
	UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder()
				.displayRequestDuration(true)
				.filter(true)
//	            .deepLinking(true)
//	            .displayOperationId(true)
//	            .defaultModelExpandDepth(1)
//	            .defaultModelRendering(ModelRendering.EXAMPLE)
//	            .docExpansion(DocExpansion.NONE)
//	            .maxDisplayedTags(null)
//	            .operationsSorter(OperationsSorter.ALPHA)
//	            .showExtensions(false)
//	            .tagsSorter(TagsSorter.ALPHA)
//	            .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
//	            .validatorUrl(null)
				.build();
	}

}
