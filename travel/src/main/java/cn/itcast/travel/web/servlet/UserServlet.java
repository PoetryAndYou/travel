package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author rui
 * @create 2019-08-26 22:42
 */
@WebServlet("/user/*") //  /user/add
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 注册
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(checkcode_server) || checkcode_server == null) {
            //不成功
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            //接受响应数据
            ObjectMapper objectMapper = new ObjectMapper();
            String s = objectMapper.writeValueAsString(resultInfo);
            //将json写回客户端
            //设置响应编码
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
            return;
        }
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装数据
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service
        // UserService userService = new UserServiceImpl();
        boolean regist = userService.regist(user);
        ResultInfo resultInfo = new ResultInfo();
        if (regist) {
            //成功
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("该名字太火，换一个吧");
        }
        //接受响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(resultInfo);
        //将json写回客户端
        //设置响应编码
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /**
     * 登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证码
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(checkcode_server) || check == null) {
            //不成功
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            //接受响应数据
            response.setContentType("application/json;charset=utf-8");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(response.getWriter(), resultInfo);

            //将json写回客户端
            //设置响应编码
            return;
        }

        //用户信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // UserService userService = new UserServiceImpl();
        User login = userService.login(user);
        System.out.println(login);
        ResultInfo resultInfo = new ResultInfo();
        if (login != null) {
            //用户存在
            String status = login.getStatus();
            if ("N".equals(status) || status == null) {
                //没有激活
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("请到邮箱中激活");

            } else {
                //激活
                resultInfo.setFlag(true);
                session.setAttribute("login_s", login);
            }

        } else {
            //用户名密码错误
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名密码错误");

        }
        //设置响应头
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        om.writeValue(response.getWriter(), resultInfo);

    }

    /**
     * 查找单个队形
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User login_s = (User) session.getAttribute("login_s");
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper ob = new ObjectMapper();
        ob.writeValue(response.getOutputStream(), login_s);
    }

    /**
     * 退出
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("login_s");
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            //调用service
            //UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);
            //判断
            String msg = null;
            if (flag) {
                //成功
                msg = "激活成功，请<a href='login.html'>登录</a>";

            } else {
                //失败
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    public void active2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("find..");
    }

    public void active3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("find..");
    }
}