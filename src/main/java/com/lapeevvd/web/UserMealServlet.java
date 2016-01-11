package com.lapeevvd.web;

import com.lapeevvd.controller.UserMealController;
import com.lapeevvd.model.UserMeal;
import com.lapeevvd.util.TimeUtil;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UserMealServlet extends HttpServlet{

    private ConfigurableApplicationContext springContext;
    private UserMealController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        springContext = new ClassPathXmlApplicationContext("spring/spring-context.xml", "spring/spring-db.xml");
        controller = springContext.getBean(UserMealController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null){
            req.setAttribute("mealList", controller.getAll());
            req.getRequestDispatcher("mealList.jsp").forward(req, resp);
        } else if (action.equals("create")){
            // дефолтные значения
            UserMeal userMeal = new UserMeal(LocalDateTime.now(), "", 1000);
            req.setAttribute("meal", userMeal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        } else if (action.equals("update")){
            UserMeal userMeal = controller.get(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("meal", userMeal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }else if (action.equals("delete")){
            controller.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("meals");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null){
            String id = req.getParameter("id");
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.parseInt(id),
                    LocalDateTime.parse(req.getParameter("dateTime")),
                    req.getParameter("description"),
                    Integer.parseInt(req.getParameter("calories")));
            if (userMeal.isNew()){
                //для логирования, что произошло именно сохранение
                controller.save(userMeal);
            } else {
                // для логирования, что произошло именно редактирование
                controller.update(userMeal);
            }
            resp.sendRedirect("meals");
        } else if (action.equals("filter")){

                LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", req), LocalDate.MIN);
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", req), LocalDate.MAX);
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", req), LocalTime.MIN);
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", req), LocalTime.MAX);

            req.setAttribute("mealList", controller.getBetween(startDate, endDate, startTime, endTime));
            req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
        }
    }

    private String resetParam(String param, HttpServletRequest req){
        String value = req.getParameter(param);
        req.setAttribute(param, value);
        return value;
    }
}
