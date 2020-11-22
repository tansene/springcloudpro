package com.tansene.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tansene.base.PublicResultConstant;
import com.tansene.config.ResponseHelper;
import com.tansene.config.ResponseModel;
import com.tansene.entity.Role;
import com.tansene.model.RoleModel;
import com.tansene.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     *  角色列表
     */
    @GetMapping("/pageList")
    //拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
    //@RequiresRoles(value = {Constant.RoleType.SYS_ADMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
    public ResponseModel<Page<Role>> getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        //根据姓名查分页
//        Page<Role> rolePage = roleService.selectPage(new Page<>(pageIndex, pageSize),
//                new EntityWrapper<Role>().where("role_name like {0}","%"+name+"%"));
        return ResponseHelper.buildResponseModel(roleService.selectPage(new Page<>(pageIndex, pageSize)));
    }

    /**
     *  获取所有角色
     */
    @GetMapping("/all")
    public  ResponseModel<List<Role>> getAllRole(){
        return ResponseHelper.buildResponseModel(roleService.selectList(new EntityWrapper<Role>()));
    }

    /**
     * 获取角色详细信息
     */
    @GetMapping(value = "/{roleCode}")
    public ResponseModel getById(@PathVariable("roleCode") String roleCode)throws Exception{
        return ResponseHelper.buildResponseModel(roleService.selectByRoleCode(roleCode));
    }

    /**
     * 删除角色
     */
    @DeleteMapping(value = "/{roleCode}")
    //拥有超级管理员或管理员角色的用户可以访问这个接口,换成角色控制权限,改变请看MyRealm.class
    //@RequiresRoles(value = {Constant.RoleType.SYS_ADMIN_ROLE,Constant.RoleType.ADMIN},logical =  Logical.OR)
    public ResponseModel deleteRole(@PathVariable("roleCode") String roleCode)throws Exception{
        roleService.deleteByRoleCode(roleCode);
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }

    /**
     * 添加角色
     * @param roleModel
     * @return
     */
    @PostMapping
    public ResponseModel addRole(RoleModel roleModel) throws Exception{
        return ResponseHelper.buildResponseModel(roleService.addRoleAndPermission(roleModel));
    }

    /**
     * 修改角色信息
     */
    @PutMapping
    public ResponseModel updateRole(RoleModel roleModel) throws Exception{
        roleService.updateRoleInfo(roleModel);
        return ResponseHelper.buildResponseModel(PublicResultConstant.SUCCEED);
    }



}

