package polymart.xyz.ass_jv4.controller.account;

import polymart.xyz.ass_jv4.entity.EntityStaff;
import polymart.xyz.ass_jv4.service.IServiceAccount;
import polymart.xyz.ass_jv4.service.implement.ServiceAccount;
import polymart.xyz.ass_jv4.utils.ContaiUtils;
import polymart.xyz.ass_jv4.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletResultRegister", value = {"/account", "/voucher", "/visit", "/order"})
public class ServletResultRegister extends HttpServlet {

    private IServiceAccount _iServiceStaff;

    @Override
    public void init() throws ServletException {
        _iServiceStaff = new ServiceAccount();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if (url.contains("/voucher")) {
            String applyVoucher = request.getParameter("cart-voucher");
            String applyCheckoutVoucher = request.getParameter("checkout-voucher");
            if (applyVoucher != null) {
                if (applyVoucher.equals("true")) {
                    request.setAttribute("title", "Voucher");
                    request.setAttribute("message", "Bạn đã áp dụng voucher");
                    request.setAttribute("link", "cart");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Voucher");
                    request.setAttribute("message", "Voucher không tồn tại!");
                    request.setAttribute("link", "cart");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            } else if (applyCheckoutVoucher != null) {
                if (applyCheckoutVoucher.equals("true")) {
                    request.setAttribute("title", "Voucher");
                    request.setAttribute("message", "Bạn đã áp dụng voucher");
                    request.setAttribute("link", "checkout");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Voucher");
                    request.setAttribute("message", "Voucher không tồn tại!");
                    request.setAttribute("link", "checkout");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            }
        } else if (url.contains("/account")) {
            String result = request.getParameter("result");
            String active = request.getParameter("active");
            String changepassword = request.getParameter("changepassword");
            String forgotPassword = request.getParameter("forgot-password");
            String resetPassword = request.getParameter("reset-password");
            String sendResetPassword = request.getParameter("send-reset-password");
            if (result != null) {
                if (result.equals("signin-successfully")) {
                    request.setAttribute("title", ContaiUtils.LOGIN_TRUE);
                    request.setAttribute("message", "Bạn sẽ được điều hướng tới trang quản lý");
                    request.setAttribute("link", "admin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else if (result.equals("signup-successfully")) {
                    request.setAttribute("title", ContaiUtils.REGISTER_TRUE);
                    request.setAttribute("message", "Vui lòng kiểu tra email");
                    request.setAttribute("link", "signin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            } else if (active != null) {
                if (_iServiceStaff.activeAccount(active)) {
                    request.setAttribute("title", ContaiUtils.ACTIVE_TRUE);
                    request.setAttribute("link", "signin");
                } else {
                    request.setAttribute("title", ContaiUtils.ACTIVE_FALSE);
                    request.setAttribute("link", "signin");
                }
                request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
            } else if (changepassword != null) {
                if (changepassword.equals("true")) {
                    request.setAttribute("title", "Đổi mật khẩu thành công");
                    request.setAttribute("link", "admin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else if (changepassword.equals("false")) {
                    request.setAttribute("title", "Đổi mật khẩu thất bại");
                    request.setAttribute("message", "Mật khẩu không chính xác");
                    request.setAttribute("link", "admin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            } else if (forgotPassword != null) {
                EntityStaff entityStaff = _iServiceStaff.findById(forgotPassword);
                if (entityStaff != null) {
                    SessionUtils.getSessionUtils().saveSessionStaff("userforgot", entityStaff, request);
                    response.sendRedirect(request.getContextPath() + "/reset-password");
                    return;
                } else {
                    request.setAttribute("title", "Link không hợp lệ");
                    request.setAttribute("link", "");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                    return;
                }
            } else if (resetPassword != null) {
                if (resetPassword.equals("true")) {
                    request.setAttribute("title", "Lấy lại mật khẩu thành công");
                    request.setAttribute("link", "signin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Lấy lại mật khẩu thất bại");
                    request.setAttribute("link", "forgot-password");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            } else if (sendResetPassword != null) {
                if (sendResetPassword.equals("true")) {
                    request.setAttribute("title", "Đã gửi email");
                    request.setAttribute("message", "Kiểm tra email để lấy lại mật khẩu");
                    request.setAttribute("link", "signin");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Gửi email lấy mật khẩu thất bại");
                    request.setAttribute("link", "forgot-password");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }
            }
        } else if (url.contains("/visit")) {
            String login = request.getParameter("login-visit");
            if (login != null) {
                if (login.equals("true")) {
                    request.setAttribute("title", ContaiUtils.LOGIN_TRUE);
                    request.setAttribute("link", "checkout");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                } else {
                    request.setAttribute("title", "Tài khoản không chính xác");
                    request.setAttribute("link", "checkout");
                    request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
                }

            }
        } else if (url.contains("/order")) {
            String order = request.getParameter("payment-method");
            if (order != null && order.equals("cod")) {
                request.setAttribute("title", "Đã tạo đơn hàng");
                request.setAttribute("message", "Xin cảm ơn");
                request.setAttribute("link", "");
                request.getRequestDispatcher("/views/account/resultRegister.jsp").forward(request, response);
            }
        }


        request.getRequestDispatcher("/views/website/home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
