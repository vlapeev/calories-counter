package com.lapeevvd.web;

import javax.servlet.http.HttpServlet;

public class UserServlet extends HttpServlet{
    /*private static final LoggerWrapper LOG = LoggerWrapper.get(UserServlet.class);

    private AdminController adminController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        adminController = wac.getBean(AdminController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("getAll");
        req.setAttribute("userList", adminController.getAll());
        req.getRequestDispatcher("/userList.jsp").forward(req, resp);
        //resp.sendRedirect("userList.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.valueOf(req.getParameter("userId"));
        LoggedUser.setId(userId);
        resp.sendRedirect("meals");
    }*/
}
