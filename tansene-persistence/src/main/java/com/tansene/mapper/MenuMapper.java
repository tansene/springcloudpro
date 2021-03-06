package com.tansene.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tansene.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tansene
 * @since 2020/11/22
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findMenuByRoleCode(@Param("roleCode") String roleCode);
}
