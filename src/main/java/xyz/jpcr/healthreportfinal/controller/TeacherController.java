package xyz.jpcr.healthreportfinal.controller;


import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import xyz.jpcr.healthreportfinal.entity.Teacher;
import xyz.jpcr.healthreportfinal.service.ITeacherService;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hunz1
 * @since 2020-12-23
 */
@Api(value = "班主任controller", tags = "班主任操作接口")
@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {

    @Resource
    ITeacherService teacherService;



    @ApiOperation("班主任登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tno", value = "教师号"),
            @ApiImplicitParam(name = "pwd", value = "密码")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "教师号密码正确"),
            @ApiResponse(code = 0, message = "教师号密码错误"),
            @ApiResponse(code = -1, message = "无此教师号")
    })
    @PostMapping("/check_pwd/{tno}&{pwd}")
    public int checkPwd(@PathVariable int tno, @PathVariable String pwd) {
        if(pwd.equals("undefined") || pwd.equals(""))
            pwd = null;
        Teacher teacher = teacherService.getTeacherByTno(tno);
        if(teacher == null)
            return -1;
        else if(teacher.getPassword() == null|| teacher.getPassword().equals(""))
            return pwd == null ? 1 : 0;
        else
            return teacher.getPassword().equals(pwd) ? 1 : 0;
    }

    @ApiOperation("通过教师号获取姓名")
    @ApiParam(name = "tno", value = "教师号")
    @GetMapping("/get_name/{tno}")
    public String getNameByNo(@PathVariable int tno) {
        Teacher teacher = teacherService.getTeacherByTno(tno);
        return teacher.getTname();
    }

    @ApiOperation("通过教师号获取班级号")
    @ApiParam(name = "tno", value = "教师号")
    @GetMapping("/get_cno/{tno}")
    public int getCnoByCno(@PathVariable int tno) {
        Teacher teacher = teacherService.getTeacherByTno(tno);
        return teacher.getCno();
    }
}

