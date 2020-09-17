package jack.gh.security.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "t_admin_role")
public class UserRole implements Serializable {

    /**
     * 主键id
     */
    @Id
    private Integer id;
    /**
     * 用户id
     */
    private Integer adminId;
    /**
     * 角色id
     */
    private Integer roleId;

}
