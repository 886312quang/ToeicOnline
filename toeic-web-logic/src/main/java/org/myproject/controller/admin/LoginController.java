package org.myproject.controller.admin;

import org.apache.log4j.Logger;
import org.myproject.command.UserCommand;
import org.myproject.core.dto.UserDTO;
import org.myproject.core.service.UserService;
import org.myproject.core.service.impl.UserServiceImpl;
import org.myproject.core.web.common.WebConstant;
import org.myproject.core.web.utils.FormUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login.html")
public class LoginController extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserCommand command = FormUtil.populate(UserCommand.class, request);
        UserDTO pojo = command.getPojo();

        UserService userService = new UserServiceImpl();

        try {
            if (userService.isUserExist(pojo) != null) {
                if (userService.findRoleByUser(pojo) != null && userService.findRoleByUser(pojo).getRoleDTO() != null) {
                    if (userService.findRoleByUser(pojo).getRoleDTO().getName().equalsIgnoreCase(WebConstant.ROLE_ADMIN)) {
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPES_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Admin");
                    } else if (userService.findRoleByUser(pojo).getRoleDTO().getName().equalsIgnoreCase(WebConstant.ROLE_USER)) {
                        request.setAttribute(WebConstant.ALERT, WebConstant.TYPES_SUCCESS);
                        request.setAttribute(WebConstant.MESSAGE_RESPONSE, "User");
                    }
                }
            }
        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
            request.setAttribute(WebConstant.ALERT, WebConstant.TYPES_ERROR);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, "Sai tài khoản hoặc mật khẩu");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/views/web/login.jsp");
        rd.forward(request, response);
    }
}
