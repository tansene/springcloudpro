package com.tansene.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
@RestController
@RequestMapping("/userToRole")
//不加入swagger ui里
@ApiIgnore
public class UserToRoleController {

}

