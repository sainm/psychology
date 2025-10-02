package com.mindvoice.psych.controller.system;

import com.mindvoice.psych.common.annotation.Log;
import com.mindvoice.psych.common.enums.LogModuleEnum;
import com.mindvoice.psych.common.pojo.CommonResult;
import com.mindvoice.psych.common.pojo.PageResult;
import com.mindvoice.psych.system.model.form.ConfigForm;
import com.mindvoice.psych.system.model.query.ConfigPageQuery;
import com.mindvoice.psych.system.model.vo.ConfigVO;
import com.mindvoice.psych.system.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.mindvoice.psych.common.pojo.CommonResult.success;

/**
 * 系统配置前端控制层
 *
 * @author liu
 * @since 2024-07-30 11:25
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/config")
public class ConfigController {

    private final ConfigService configService;

    @Operation(summary = "系统配置分页列表")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPerm('sys:config:query')")
    @Log(value = "系统配置分页列表", module = LogModuleEnum.SETTING)
    public CommonResult<PageResult<ConfigVO>> page(@ParameterObject ConfigPageQuery configPageQuery) {
        PageResult<ConfigVO> result = configService.page(configPageQuery);
        return success(result);
    }

    @Operation(summary = "新增系统配置")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:config:add')")
    @Log(value = "新增系统配置", module = LogModuleEnum.SETTING)
    public CommonResult<?> save(@RequestBody @Valid ConfigForm configForm) {
        return Result.judge(configService.save(configForm));
    }

    @Operation(summary = "获取系统配置表单数据")
    @GetMapping("/{id}/form")
    public CommonResult<ConfigForm> getConfigForm(@Parameter(description = "系统配置ID") @PathVariable Long id) {
        ConfigForm formData = configService.getConfigFormData(id);
        return success(formData);
    }

    @Operation(summary = "刷新系统配置缓存")
    @PutMapping("/refresh")
    @PreAuthorize("@ss.hasPerm('sys:config:refresh')")
    @Log(value = "刷新系统配置缓存", module = LogModuleEnum.SETTING)
    public CommonResult<ConfigForm> refreshCache() {
        return Result.judge(configService.refreshCache());
    }

    @Operation(summary = "修改系统配置")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:config:update')")
    @Log(value = "修改系统配置", module = LogModuleEnum.SETTING)
    public CommonResult<?> update(@Valid @PathVariable Long id, @RequestBody ConfigForm configForm) {
        return Result.judge(configService.edit(id, configForm));
    }

    @Operation(summary = "删除系统配置")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:config:delete')")
    @Log(value = "删除系统配置", module = LogModuleEnum.SETTING)
    public CommonResult<Boolean> delete(@PathVariable Long id) {
        return Result.judge(configService.delete(id));
    }

}
