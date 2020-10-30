package com.tttiger.admin.service.sys.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.sys.Menu;
import com.tttiger.admin.common.constant.MenuConstant;
import com.tttiger.admin.mapper.sys.MenuMapper;
import com.tttiger.admin.service.sys.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 秦浩桐
 * @version 1.0
 * @Date 2019/12/22 16:46
 * @Description
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuByRoleId(Integer roleId) {
        return menuMapper.selectMenuByRoleId(roleId);
    }

    @Override
    public List<Menu> selectAll() {
        return buildHierarchicalMenu(menuMapper.selectList(null));
    }

    @Override
    public List<Menu> selectUserHasAuthorityMenu(String account) {
        return buildHierarchicalMenu(menuMapper.selectUserHasAuthorityMenu(account));
    }


    @Override
    public BaseMapper<Menu> getMapper() {
        return menuMapper;
    }

    private List<Menu> buildHierarchicalMenu(List<Menu> menus) {
        if (menus == null || menus.isEmpty()) {
            return Collections.emptyList();
        }
        // 找到所有根菜单
        List<Menu> parentMenu = menus.stream().filter(x ->
                x.getParentMenu().equals(MenuConstant.ROOT_TYPE)
        ).collect(ArrayList::new, (r, y) -> r.add(y), ArrayList::addAll);
        // 找到所有子菜单
        List<Menu> childMenu = menus.stream().filter(x ->
                !x.getParentMenu().equals(MenuConstant.ROOT_TYPE)
        ).collect(ArrayList::new, (r, y) -> r.add(y), ArrayList::addAll);
        // 父子节点拼装
        parentMenu.forEach(x -> recursionBuild(x, childMenu));
        // 父子节点排序
        sortMenu(parentMenu);

        return parentMenu;
    }

    private void  sortMenu(List<Menu> menus){
        menus.sort((x,y)->x.getSort().compareTo(y.getSort()));
        for(Menu menu:menus){
            if(menu.getChildren() != null && !menu.getChildren().isEmpty()){
                sortMenu(menu.getChildren());
            }
        }
    }

    private Menu recursionBuild(Menu parentMenu, List<Menu> childMenu) {
        parentMenu.setChildren(new ArrayList<>());
        for (Menu next : childMenu) {
            if (next.getParentMenu().equals(parentMenu.getMenuId())) {
                parentMenu.getChildren().add(next);
                if (hasChild(next, childMenu)) {
                    recursionBuild(next, childMenu);
                }
            }
        }
        return parentMenu;
    }

    private boolean hasChild(Menu menu, List<Menu> childMenu) {
        for (Menu x : childMenu) {
            if (menu.getMenuId().equals(x.getParentMenu())) {
                return true;
            }
        }
        return false;
    }
}
