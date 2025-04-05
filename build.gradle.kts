plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.1221Systems"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.18.3")
	implementation("jakarta.validation:jakarta.validation-api:3.1.1")

	implementation("org.liquibase:liquibase-core:4.29.2")
	implementation("org.postgresql:postgresql:42.7.4")

	implementation("org.slf4j:slf4j-api:2.0.17")
	implementation("ch.qos.logback:logback-classic:1.5.18")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.4")

	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.mockito:mockito-core:5.16.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
