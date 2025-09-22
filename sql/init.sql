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
    `id`          BIGINT PRIMARY KEY AUTO_INCREMENT,
    `name`        VARCHAR(100) NOT NULL UNIQUE,
    `parent_id`   BIGINT    DEFAULT NULL COMMENT '上级组织ID，顶级组织为NULL',
    `status`      TINYINT   DEFAULT 1 COMMENT '1=启用 0=禁用',
    `create_by` BIGINT COMMENT '创建人ID',
    `update_by` BIGINT COMMENT '更新人ID',
    `created_at`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at`  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
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

-- 量表表
DROP TABLE IF EXISTS sys_questionnaire;
CREATE TABLE sys_questionnaire (
   `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '量表ID',
   `code` VARCHAR(50) UNIQUE NOT NULL COMMENT '量表编码，如SCL90',
   `name` VARCHAR(100) NOT NULL COMMENT '量表名称',
   `description` TEXT COMMENT '量表说明',
   `status` TINYINT DEFAULT 1 COMMENT '1=启用 0=停用',
   `create_by` BIGINT COMMENT '创建人ID',
   `update_by` BIGINT COMMENT '更新人ID',
   `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
   `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS sys_question;
CREATE TABLE sys_question (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '题目ID',
    `questionnaire_id` BIGINT NOT NULL COMMENT '所属量表ID',
    `content` VARCHAR(500) NOT NULL COMMENT '题目内容',
    `type` TINYINT DEFAULT 1 COMMENT '1=单选 2=多选 3=量表打分',
    `order_no` INT COMMENT '题目顺序',
    `create_by` BIGINT COMMENT '创建人ID',
    `update_by` BIGINT COMMENT '更新人ID',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (questionnaire_id) REFERENCES sys_questionnaire(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 选项表
DROP TABLE IF EXISTS sys_question_option;
CREATE TABLE sys_question_option (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '选项ID',
    `question_id` BIGINT NOT NULL COMMENT '所属题目ID',
    `content` VARCHAR(200) NOT NULL COMMENT '选项内容',
    `score` INT NOT NULL COMMENT '该选项分值',
    `order_no` INT COMMENT '选项顺序',
    FOREIGN KEY (question_id) REFERENCES sys_question(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户答卷表
DROP TABLE IF EXISTS sys_user_answer_sheet;
CREATE TABLE sys_user_answer_sheet (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '答卷ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `questionnaire_id` BIGINT NOT NULL COMMENT '量表ID',
    `total_score` INT DEFAULT 0 COMMENT '总分',
    `status` TINYINT DEFAULT 0 COMMENT '0=未完成 1=已完成',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `finished_at` DATETIME COMMENT '完成时间',
    FOREIGN KEY (user_id) REFERENCES sys_user(id),
    FOREIGN KEY (questionnaire_id) REFERENCES sys_questionnaire(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 答题详情表
DROP TABLE IF EXISTS sys_user_answer;
CREATE TABLE sys_user_answer (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '答题记录ID',
    `answer_sheet_id` BIGINT NOT NULL COMMENT '答卷ID',
    `question_id` BIGINT NOT NULL COMMENT '题目ID',
    `option_id` BIGINT COMMENT '选项ID（多选时多条记录）',
    `score` INT NOT NULL COMMENT '得分',
    FOREIGN KEY (answer_sheet_id) REFERENCES sys_user_answer_sheet(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES sys_question(id),
    FOREIGN KEY (option_id) REFERENCES sys_question_option(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 测评结果表
DROP TABLE IF EXISTS sys_evaluation_result;
CREATE TABLE sys_evaluation_result (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '结果ID',
    `answer_sheet_id` BIGINT NOT NULL COMMENT '答卷ID',
    `dimension` VARCHAR(100) COMMENT '维度，如“抑郁”、“焦虑”',
    `score` INT COMMENT '维度分数',
    `interpretation` TEXT COMMENT '解释说明',
    `create_time`DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (answer_sheet_id) REFERENCES sys_user_answer_sheet(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 维度表
DROP TABLE IF EXISTS sys_questionnaire_dimension;
CREATE TABLE sys_questionnaire_dimension (
     `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '维度ID',
     `questionnaire_id` BIGINT NOT NULL COMMENT '所属量表ID',
     `code` VARCHAR(50) NOT NULL COMMENT '维度编码，如depression',
     `name` VARCHAR(100) NOT NULL COMMENT '维度名称，如抑郁',
     `description` TEXT COMMENT '维度说明',
     FOREIGN KEY (questionnaire_id) REFERENCES sys_questionnaire(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 维度-题目映射表
DROP TABLE IF EXISTS sys_dimension_question_map;
CREATE TABLE sys_dimension_question_map (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `dimension_id` BIGINT NOT NULL COMMENT '维度ID',
    `question_id` BIGINT NOT NULL COMMENT '题目ID',
    FOREIGN KEY (dimension_id) REFERENCES sys_questionnaire_dimension(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES sys_question(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 维度解释规则表
DROP TABLE IF EXISTS sys_dimension_interpretation;
CREATE TABLE sys_dimension_interpretation (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
    `dimension_id` BIGINT NOT NULL COMMENT '维度ID',
    `min_score` INT NOT NULL COMMENT '最小分数',
    `max_score` INT NOT NULL COMMENT '最大分数',
    `interpretation` TEXT NOT NULL COMMENT '解释说明',
    FOREIGN KEY (dimension_id) REFERENCES sys_questionnaire_dimension(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;