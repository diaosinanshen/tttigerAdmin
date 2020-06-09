package com.tttiger.admin.service.iml;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tttiger.admin.bean.Menu;
import com.tttiger.admin.common.constant.MenuConstant;
import com.tttiger.admin.mapper.MenuMapper;
import com.tttiger.admin.service.MenuService;
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
        List<Menu> parentMenu = menus.stream().filter(x ->
                x.getMenuId().equals(MenuConstant.ROOT_TYPE)
        ).collect(ArrayList::new, (r, y) -> r.add(y), ArrayList::addAll);

        List<Menu> childMenu = menus.stream().filter(x ->
                !x.getMenuId().equals(MenuConstant.ROOT_TYPE)
        ).collect(ArrayList::new, (r, y) -> r.add(y), ArrayList::addAll);
        parentMenu.forEach(x -> recursionBuild(x, childMenu));
        return parentMenu;
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
