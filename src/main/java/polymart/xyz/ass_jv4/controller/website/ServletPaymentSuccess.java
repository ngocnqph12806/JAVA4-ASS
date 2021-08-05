package polymart.xyz.ass_jv4.controller.website;

import polymart.xyz.ass_jv4.entity.EntityPayment;
import polymart.xyz.ass_jv4.service.IServicePayment;
import polymart.xyz.ass_jv4.service.implement.ServicePayment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletPaymentSuccess", value = "/payment-success")
public class ServletPaymentSuccess extends HttpServlet {

    private IServicePayment _iServicePayment;

    @Override
    public void init(ServletConfig config) throws ServletException {
        _iServicePayment = new ServicePayment();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vnp_TxnRef = request.getParameter("vnp_TxnRef");
        if (vnp_TxnRef != null) {
            EntityPayment entityPayment = _iServicePayment.findById(vnp_TxnRef);
            if (entityPayment != null) {
                entityPayment.setStatusPayment(true);
                _iServicePayment.updatePayment(entityPayment);
                response.sendRedirect(request.getContextPath() + "/order?payment-success=true");
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/order?payment-method=cod");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
