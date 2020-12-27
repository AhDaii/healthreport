package xyz.jpcr.healthreportfinal.service;

import xyz.jpcr.healthreportfinal.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
public interface ITeacherService extends IService<Teacher> {
    public Teacher getTeacherByTno(int tno);
    public int checkPwd(int tno, String pwd);
}
