plugins {
    id("org.springframework.boot")
}

dependencies {
    // 依賴 Web 層
    implementation(project(":web"))
    // 開發工具，用於熱部署
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}