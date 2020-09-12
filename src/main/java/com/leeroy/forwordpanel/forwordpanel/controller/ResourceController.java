package com.leeroy.forwordpanel.forwordpanel.controller;

import com.leeroy.forwordpanel.forwordpanel.common.WebCurrentData;
import com.leeroy.forwordpanel.forwordpanel.common.response.ApiResponse;
import com.leeroy.forwordpanel.forwordpanel.model.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理
 */
@RestController
public class ResourceController {

    private static Map<Integer, List<Resource>> USER_TYPE_RESOURCE = new HashMap<>();

    /**
     * 初始化权限
     */
    static {
        List<Resource> adminResourceList = new ArrayList<>();
        adminResourceList.add(new Resource(1, "看板", "/dashboard", "dashboard"));
        adminResourceList.add(new Resource(2, "服务器管理", "/server", "el-icon-cpu"));
        adminResourceList.add(new Resource(3, "端口管理", "/port/manage", "el-icon-s-help"));
        adminResourceList.add(new Resource(4, "账号管理", "/user/userManage", "el-icon-user-solid"));
        adminResourceList.add(new Resource(5, "中转管理", "/portForwardManage", "el-icon-attract"));
        adminResourceList.add(new Resource(6, "配置文件", "/clashManage", "el-icon-tickets"));


        USER_TYPE_RESOURCE.put(0, adminResourceList);

        List<Resource> userResourceList = new ArrayList<>();
        Resource userSystemManage = new Resource(1, "系统管理", "", "form");
        List<Resource> userSystemManageChildList = new ArrayList<>();
        userSystemManageChildList.add(new Resource(5, "中转管理", "/portForwardManage", "form"));
        userSystemManageChildList.add(new Resource(6, "配置文件", "/clashManage", "form"));
        userSystemManage.setChildren(userSystemManageChildList);
        userResourceList.add(userSystemManage);
        USER_TYPE_RESOURCE.put(1, userResourceList);
    }

    /**
     * 获取资源
     *
     * @return
     */
    @GetMapping("/getMenus")
    public ApiResponse getResource() {
        Integer userType = WebCurrentData.getUser().getUserType();
        return ApiResponse.ok(USER_TYPE_RESOURCE.get(userType));
    }
}
