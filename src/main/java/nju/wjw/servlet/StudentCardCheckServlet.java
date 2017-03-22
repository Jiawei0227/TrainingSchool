package nju.wjw.servlet;

import nju.wjw.service.Impl.StudentCardImpl;
import nju.wjw.service.StudentCardService;
import nju.wjw.util.scheduletask.TaskBuilder;
import nju.wjw.util.scheduletask.TaskEntity;
import nju.wjw.util.scheduletask.TaskManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jerry Wang on 2017/3/20.
 */
public class StudentCardCheckServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{
        super.init();
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
        StudentCardService studentCardService = ctx.getBean(StudentCardImpl.class);
        TaskEntity taskEntity = null;
        try {
            taskEntity = TaskBuilder.createBuilder()
                    .setName("会员状态检查")
                    .addTask(()->studentCardService.checkStudentCardState())
                    .asRepeatableTask()
                    .frequency(1, TimeUnit.DAYS)
                    .firstRunTime("15:48:00")
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TaskManager.registerTask(taskEntity);
        TaskManager.scheduleMonitor();

    }



}
