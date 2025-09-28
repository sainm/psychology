dependencies {
    // 依賴基礎設施模組
    implementation(project(":api"))
    implementation(project(":common"))
    implementation(project(":infra"))
    // Spring Boot Web Starter
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}