# Journal
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
		 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		 xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.12</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<groupId>com.devansh</groupId>
	<artifactId>Journal</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>Journal</name>
	<description>E2E Journal Application</description>

	<properties>
		<java.version>21</java.version>
	</properties>

	<dependencies>
		<!-- Spring Boot Web -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!-- MongoDB -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-mongodb</artifactId>
		</dependency>

		<!-- Spring Boot Test -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- Cucumber -->
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-java</artifactId>
			<version>7.14.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-spring</artifactId>
			<version>7.14.0</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>io.cucumber</groupId>
			<artifactId>cucumber-junit</artifactId>
			<version>7.14.0</version>
			<scope>test</scope>
		</dependency>

		<!-- For backward compatibility with JUnit 4 -->
		<dependency>
			<groupId>org.junit.vintage</groupId>
			<artifactId>junit-vintage-engine</artifactId>
			<version>5.10.0</version>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<!-- Spring Boot Plugin -->
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>

			<!-- Cucumber Report Plugin -->
			<plugin>
				<groupId>net.masterthought</groupId>
				<artifactId>maven-cucumber-reporting</artifactId>
				<version>5.7.5</version>
				<executions>
					<execution>
						<id>generate-cucumber-report</id>
						<phase>verify</phase>
						<goals>
							<goal>generate</goal>
						</goals>
						<configuration>
							<projectName>Journal API Test</projectName>
							<outputDirectory>target/cucumber-reports</outputDirectory>
							<jsonFiles>
								<param>target/cucumber.json</param>
							</jsonFiles>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>
</project>
