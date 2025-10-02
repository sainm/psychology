package com.mindvoice.psych.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.form.ConfigForm;
import com.mindvoice.psych.system.model.query.ConfigPageQuery;
import com.mindvoice.psych.system.model.vo.ConfigVO;

/**
 * 系统配置Service接口
 *
 * @author liu
 * @since 2024-07-29 11:17:26
 */
public interface ConfigService  {

    /**
     * 保存系统配置
     * @param sysConfigForm 系统配置表单
     * @return 是否保存成功
     */
    boolean save(ConfigForm sysConfigForm);

    /**
     * 获取系统配置表单数据
     *
     * @param id 系统配置ID
     * @return 系统配置表单数据
     */
    ConfigForm getConfigFormData(Long id);

    /**
     * 编辑系统配置
     * @param id  系统配置ID
     * @param sysConfigForm 系统配置表单
     * @return 是否编辑成功
     */
    boolean edit(Long id, ConfigForm sysConfigForm);

    /**
     * 删除系统配置
     * @param ids 系统配置ID
     * @return 是否删除成功
     */
    boolean delete(Long ids);

    /**
     * 刷新系统配置缓存
     * @return 是否刷新成功
     */
    boolean refreshCache();

    /**
     * 获取系统配置
     * @param key 配置键
     * @return 配置值
     */
    Object getSystemConfig(String key);

    PageResult<ConfigVO> page(ConfigPageQuery configPageQuery);
}
