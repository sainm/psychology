SET FOREIGN_KEY_CHECKS = 0;
--
-- 用户表：存储用户基本信息
--
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`            BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username`      VARCHAR(64)  NOT NULL UNIQUE COMMENT '用户名',
    `password`      VARCHAR(255) NOT NULL COMMENT '加密后的密码',
    `email`         VARCHAR(128) UNIQUE COMMENT '邮箱',
    `status`        TINYINT      NOT NULL DEFAULT 1 COMMENT '用户状态：0-禁用, 1-启用',
    `provider_id`   VARCHAR(255) COMMENT '第三方登录ID',
    `provider_type` VARCHAR(32) COMMENT '第三方登录类型（google, github等）',
    `create_by`     BIGINT COMMENT '创建人ID',
    `update_by`     BIGINT COMMENT '更新人ID',
    `create_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE sys_organization
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(100) NOT NULL UNIQUE,
    parent_id   BIGINT    DEFAULT NULL COMMENT '上级组织ID，顶级组织为NULL',
    status      TINYINT   DEFAULT 1 COMMENT '1=启用 0=禁用',
    `create_by` BIGINT COMMENT '创建人ID',
    `update_by` BIGINT COMMENT '更新人ID',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES sys_organization (id) ON DELETE SET NULL
);

--
-- 角色表：存储角色信息
--
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `role_name`   VARCHAR(64) NOT NULL UNIQUE COMMENT '角色名称',
    `description` VARCHAR(255) COMMENT '描述',
    `create_by`   BIGINT COMMENT '创建人ID',
    `update_by`   BIGINT COMMENT '更新人ID',
    `create_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';


--
-- 权限表：存储权限信息
--
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `permission_code` VARCHAR(128) NOT NULL UNIQUE COMMENT '权限标识符（例如：user:list）',
    `permission_name` VARCHAR(128) NOT NULL COMMENT '权限名称',
    `description`     VARCHAR(255) COMMENT '描述',
    `create_by`       BIGINT COMMENT '创建人ID',
    `update_by`       BIGINT COMMENT '更新人ID',
    `create_time`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

--
-- 用户-角色关联表：多对多关系
--
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id`     BIGINT    NOT NULL COMMENT '用户ID',
    `role_id`     BIGINT    NOT NULL COMMENT '角色ID',
    `create_by`   BIGINT COMMENT '创建人ID',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户-角色关联表';


--
-- 角色-权限关联表：多对多关系
--
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id`       BIGINT    NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT    NOT NULL COMMENT '权限ID',
    `create_by`     BIGINT COMMENT '创建人ID',
    `create_time`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`role_id`, `permission_id`),
    FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色-权限关联表';

DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`
(
    `id`           BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '公司ID',
    `name`         VARCHAR(100) NOT NULL COMMENT '公司名称',
    `description`  VARCHAR(100) DEFAULT '' COMMENT '公司类型/行业，例如互联网科技公司',
    `status`       TINYINT      DEFAULT 1 COMMENT '状态: 1=活跃, 0=禁用',
    `create_by`    BIGINT COMMENT '创建人ID',
    `update_by`    BIGINT COMMENT '更新人ID',
    `created_time` DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT '公司信息表';

DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`
(
    `id`             BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '部门ID',
    `company_id`     BIGINT       NOT NULL COMMENT '所属公司ID',
    `name`           VARCHAR(100) NOT NULL COMMENT '部门名称',
    `description`    VARCHAR(255) DEFAULT '' COMMENT '部门描述，例如“负责产品研发和维护”',
    `status`         TINYINT      DEFAULT 1 COMMENT '状态: 1=活跃, 0=禁用',
    `create_by`    BIGINT COMMENT '创建人ID',
    `update_by`    BIGINT COMMENT '更新人ID',
    `created_time`     DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time`     DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT fk_dept_company FOREIGN KEY (company_id) REFERENCES sys_company (id)
) COMMENT '部门信息表';

#
#
# -- 量表/问卷表
# DROP TABLE IF EXISTS `psy_scale`;
# CREATE TABLE psy_scale
# (
#     id            BIGINT PRIMARY KEY AUTO_INCREMENT,
#     name          VARCHAR(100) NOT NULL,
#     description   TEXT,
#     `create_by`   BIGINT COMMENT '创建人ID',
#     `update_by`   BIGINT COMMENT '更新人ID',
#     `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
# );
#
# -- 题目表
# DROP TABLE IF EXISTS `psy_question`;
# CREATE TABLE psy_question
# (
#     id            BIGINT PRIMARY KEY AUTO_INCREMENT,
#     scale_id      BIGINT       NOT NULL,
#     content       VARCHAR(500) NOT NULL,
#     question_type TINYINT               DEFAULT 1 COMMENT '1=单选 2=多选 3=评分',
#     sort_order    INT,
#     `create_by`   BIGINT COMMENT '创建人ID',
#     `update_by`   BIGINT COMMENT '更新人ID',
#     `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#     FOREIGN KEY (scale_id) REFERENCES psy_scale (id)
# );
#
# -- 选项表
# DROP TABLE IF EXISTS `psy_option_item`;
# CREATE TABLE psy_option_item
# (
#     id            BIGINT PRIMARY KEY AUTO_INCREMENT,
#     question_id   BIGINT       NOT NULL,
#     content       VARCHAR(200) NOT NULL,
#     score         INT                   DEFAULT 0 COMMENT '该选项对应的分值',
#     sort_order    INT,
#     `create_by`   BIGINT COMMENT '创建人ID',
#     `update_by`   BIGINT COMMENT '更新人ID',
#     `create_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `update_time` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#     FOREIGN KEY (question_id) REFERENCES psy_question (id)
# );
# DROP TABLE IF EXISTS `psy_user_answer_sheet`;
# -- 用户答卷表
# CREATE TABLE psy_user_answer_sheet
# (
#     id            BIGINT PRIMARY KEY AUTO_INCREMENT,
#     user_id       BIGINT    NOT NULL,
#     scale_id      BIGINT    NOT NULL,
#     total_score   INT                DEFAULT 0,
#     result_text   VARCHAR(500),
#     `create_by`   BIGINT COMMENT '创建人ID',
#     `update_by`   BIGINT COMMENT '更新人ID',
#     `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
#     `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
#     FOREIGN KEY (user_id) REFERENCES sys_user (id),
#     FOREIGN KEY (scale_id) REFERENCES psy_scale (id)
# );
# DROP TABLE IF EXISTS `psy_user_answer`;
# -- 答案明细表
# CREATE TABLE psy_user_answer
# (
#     id          BIGINT PRIMARY KEY AUTO_INCREMENT,
#     sheet_id    BIGINT NOT NULL,
#     question_id BIGINT NOT NULL,
#     option_id   BIGINT,
#     score       INT DEFAULT 0,
#     FOREIGN KEY (sheet_id) REFERENCES psy_user_answer_sheet (id),
#     FOREIGN KEY (question_id) REFERENCES psy_question (id),
#     FOREIGN KEY (option_id) REFERENCES psy_option_item (id)
# );

SET FOREIGN_KEY_CHECKS = 1;