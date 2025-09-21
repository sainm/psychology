rootProject.name = "psychology"
include("common") //公共工具类 & DTO
include("infra") //基础设施模块（配置、数据库、权限）
include("server") //启动模块（Spring Boot Application）
include("api") //服务接口（Service Interfaces）数据传输对象（DTOs）枚举和常量
include("core")//实现业务逻辑
include("web") //Web 层（页面访问、前后端接口）