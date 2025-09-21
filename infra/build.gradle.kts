plugins {
    `java-library`
}

dependencies {
    // 依賴公共模組
    implementation(project(":common"))
    implementation(project(":api"))
    // Spring Security（对上层暴露以便使用注解/类型）
    api("org.springframework.boot:spring-boot-starter-security")
    // Spring Security OAuth2 Client for Third-Party Login
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    // MyBatis for Database Operations
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.5")
    // MariaDB JDBC Driver
    implementation("org.mariadb.jdbc:mariadb-java-client:3.4.0")

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.1.2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // JWT (JJWT)

}