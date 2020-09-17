package jack.gh.security.dao;

import jack.gh.security.entity.Resource;
import jack.gh.security.entity.Role;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ResourceMapper extends Mapper<Resource> {
}
