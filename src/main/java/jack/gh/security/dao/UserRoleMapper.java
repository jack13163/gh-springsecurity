package jack.gh.security.dao;

import jack.gh.security.entity.UserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRoleMapper extends Mapper<UserRole> {
}
