plugins {
    `java-library`
}

dependencies {
    // 依賴公共模組
    implementation(project(":common"))
    implementation(project(":api"))
    api("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.14")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.1.2")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-cache:3.5.6")
    implementation("cn.hutool:hutool-all:5.8.40")
    implementation("com.baomidou:mybatis-plus-jsqlparser:3.5.14")





    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
}