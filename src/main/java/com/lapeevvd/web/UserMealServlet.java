package com.lapeevvd.web;

import javax.servlet.http.HttpServlet;

public class UserMealServlet extends HttpServlet{

   /* private UserMealController controller;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(UserMealController.class);
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

                LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", req), TimeUtil.MIN_DATE);
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", req), TimeUtil.MAX_DATE);
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
    }*/
}
