package jack.gh.security.dao;

import jack.gh.security.entity.Resource;
import jack.gh.security.entity.RoleResource;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface RoleResourceMapper extends Mapper<RoleResource> {
}
