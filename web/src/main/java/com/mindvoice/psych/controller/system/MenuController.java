package com.mindvoice.psych.controller.system;

import com.mindvoice.psych.common.annotation.Log;
import com.mindvoice.psych.common.annotation.RepeatSubmit;
import com.mindvoice.psych.common.enums.LogModuleEnum;
import com.mindvoice.psych.common.model.Option;
import com.mindvoice.psych.common.pojo.CommonResult;
import com.mindvoice.psych.system.model.form.MenuForm;
import com.mindvoice.psych.system.model.query.MenuQuery;
import com.mindvoice.psych.system.model.vo.MenuVO;
import com.mindvoice.psych.system.model.vo.RouteVO;
import com.mindvoice.psych.system.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mindvoice.psych.common.pojo.CommonResult.success;

/**
 * 菜单控制层
 *
 * @author liu
 * @since 2020/11/06
 */
@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@RequiredArgsConstructor
@Slf4j
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "菜单列表")
    @GetMapping
    @Log(value = "菜单列表", module = LogModuleEnum.MENU)
    public CommonResult<List<MenuVO>> getMenus(MenuQuery queryParams) {
        List<MenuVO> menuList = menuService.listMenus(queryParams);
        return success(menuList);
    }

    @Operation(summary = "菜单下拉列表")
    @GetMapping("/options")
    public CommonResult<List<Option<Long>>> getMenuOptions(@Parameter(description = "是否只查询父级菜单") @RequestParam(required = false, defaultValue = "false") boolean onlyParent) {
        List<Option<Long>> menus = menuService.listMenuOptions(onlyParent);
        return success(menus);
    }

    @Operation(summary = "当前用户菜单路由列表")
    @GetMapping("/routes")
    public CommonResult<List<RouteVO>> getCurrentUserRoutes() {
        List<RouteVO> routeList = menuService.listCurrentUserRoutes();
        return success(routeList);
    }

    @Operation(summary = "菜单表单数据")
    @GetMapping("/{id}/form")
    @PreAuthorize("@ss.hasPerm('sys:menu:edit')")
    public CommonResult<MenuForm> getMenuForm(@Parameter(description = "菜单ID") @PathVariable Long id) {
        MenuForm menu = menuService.getMenuForm(id);
        return success(menu);
    }

    @Operation(summary = "新增菜单")
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:menu:add')")
    @RepeatSubmit
    public CommonResult<?> addMenu(@RequestBody MenuForm menuForm) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单")
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:edit')")
    public CommonResult<?> updateMenu(@RequestBody MenuForm menuForm) {
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/{id}")
    @PreAuthorize("@ss.hasPerm('sys:menu:delete')")
    public CommonResult<?> deleteMenu(@Parameter(description = "菜单ID，多个以英文(,)分割") @PathVariable("id") Long id) {
        boolean result = menuService.deleteMenu(id);
        return Result.judge(result);
    }

    @Operation(summary = "修改菜单显示状态")
    @PatchMapping("/{menuId}")
    @PreAuthorize("@ss.hasPerm('sys:menu:edit')")
    public CommonResult<?> updateMenuVisible(@Parameter(description = "菜单ID") @PathVariable Long menuId, @Parameter(description = "显示状态(1:显示;0:隐藏)") Integer visible

    ) {
        boolean result = menuService.updateMenuVisible(menuId, visible);
        return Result.judge(result);
    }

}

