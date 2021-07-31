package polymart.xyz.ass_jv4.controller.website;

import org.apache.commons.beanutils.BeanUtils;
import polymart.xyz.ass_jv4.entity.*;
import polymart.xyz.ass_jv4.service.*;
import polymart.xyz.ass_jv4.service.implement.*;
import polymart.xyz.ass_jv4.utils.CookieUtils;
import polymart.xyz.ass_jv4.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletCheckout", value = {"/checkout", "/payment/order"})
public class ServletCheckout extends HttpServlet {

    private IServiceVoucher _iServiceVoucher;
    private IServiceVisit _iServiceVisit;
    private IServicePayment _iServicePayment;
    private IServiceProductDetails _iServiceProductDetails;
    private IServicePaymentDetails _iServicePaymentDetails;

    @Override
    public void init() throws ServletException {
        _iServiceVoucher = new ServiceVoucher();
        _iServiceVisit = new ServiceVisit();
        _iServiceProductDetails = new ServiceProductDetails();
        _iServicePaymentDetails = new ServicePaymentDetails();
        _iServicePayment = new ServicePayment();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.getRequestDispatcher("/views/website/page/cart/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String url = request.getRequestURI();
        if (url.contains("/checkout")) {
            String voucher = request.getParameter("voucher");
            String getLoginPhoneNumber = request.getParameter("login-phoneNumber");
            String getLoginPassword = request.getParameter("login-password");
            if (getLoginPhoneNumber != null && getLoginPassword != null) {
                EntityVisit entityVisit = _iServiceVisit.findByPhoneAnhPassword(getLoginPhoneNumber, getLoginPassword);
                if (entityVisit != null) {
                    SessionUtils.getSessionUtils().saveSessionVisit("visit", entityVisit, request);
                    response.sendRedirect(request.getContextPath() + "/visit?login-visit=true");
                    return;
                }
                response.sendRedirect(request.getContextPath() + "/visit?login-visit=false");
                return;
            }
            if (voucher != null) {
                EntityVoucher entityVoucher = _iServiceVoucher.findById(voucher);
                SessionUtils.getSessionUtils().saveSession("voucher", voucher, request);
                if (entityVoucher != null) {
                    response.sendRedirect(request.getContextPath() + "/voucher?checkout-voucher=true");
                    return;
                }
                response.sendRedirect(request.getContextPath() + "/voucher?checkout-voucher=false");
                return;
            }
        } else if (url.contains("/payment/order")) {
            EntityVisit entityVisit = new EntityVisit();
            try {
                // get thông tin khách hàng
                BeanUtils.populate(entityVisit, request.getParameterMap());
                EntityVisit checkEntityVisit = _iServiceVisit.findByPhone(entityVisit.getPhoneNumber());

                // lấy voucher sử dụng (nếu có)
                EntityPayment entityPayment = new EntityPayment();
                BeanUtils.populate(entityPayment, request.getParameterMap());
                String idVoucher = SessionUtils.getSessionUtils().getSessionString("voucher", request);
                if (idVoucher != null) {
                    EntityVoucher entityVoucher = _iServiceVoucher.findById(idVoucher);
                    if (entityVoucher != null) {
                        entityPayment.setEntityVoucher(entityVoucher);
                    }
                }

                // lấy danh sách giỏ hàng
                List<EntityProductDetails> lstProductDetails = new ArrayList<>();
                EntityProductDetails entityProductDetails;
                String cart = CookieUtils.getCookieUtils().getCookieUtils("cart", request);
                if (cart != null && !cart.trim().equals("")) {
                    String[] arrCart = cart.split("c");
                    String[] arrId = new String[arrCart.length];
                    String[] arrQuantity = new String[arrCart.length];
                    String id = "";
                    String quantity = "";
                    for (int i = 0; i < arrCart.length; i++) {
                        id = arrCart[i].substring(0, arrCart[i].lastIndexOf("-"));
                        quantity = arrCart[i].substring(arrCart[i].lastIndexOf("-") + 1);
                        arrId[i] = id;
                        arrQuantity[i] = quantity;
                    }
                    for (int i = 0; i < arrId.length; i++) {
                        entityProductDetails = _iServiceProductDetails.findById(arrId[i]);
                        if (entityProductDetails != null) {
                            try {
                                int sl = Integer.parseInt(arrQuantity[i]);
                                entityProductDetails.setQuantity(sl);
                                lstProductDetails.add(entityProductDetails);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                // tính tổng tiền hoá đơn
                Long paymentAmount = Long.parseLong("0");
                for (EntityProductDetails x : lstProductDetails) {
                    paymentAmount += (x.getPrice() - (x.getPrice() * x.getEntityProduct().getPersenSale() / 100)) * x.getQuantity();
                }
                entityPayment.setPaymentAmount(paymentAmount);


                if (checkEntityVisit != null) {
                    // lấy số điện thoại khách hàng
                    entityPayment.setEntityVisit(checkEntityVisit);
                    if (_iServiceVisit.updateVisit(entityVisit)) {
                        savePayment(request, response, entityVisit, entityPayment, lstProductDetails, paymentAmount);
                        return;
                    }
                } else {
                    if (_iServiceVisit.newVisit(entityVisit)) {
                        checkEntityVisit = _iServiceVisit.findByPhone(entityVisit.getPhoneNumber());
                        if (checkEntityVisit != null) {
                            // lấy số điện thoại khách hàng
                            entityPayment.setEntityVisit(checkEntityVisit);
                            savePayment(request, response, entityVisit, entityPayment, lstProductDetails, paymentAmount);
                            return;
                        }
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect(request.getContextPath() + "/checkout");
    }

    private void savePayment(HttpServletRequest request, HttpServletResponse response, EntityVisit entityVisit, EntityPayment entityPayment, List<EntityProductDetails> lstProductDetails, Long paymentAmount) throws IOException, ServletException {
        EntityProductDetails entityProductDetails;
        if (_iServicePayment.newPayment(entityPayment)) {
            entityPayment = _iServicePayment.findIdPayment(entityVisit, paymentAmount + "");
            if (entityPayment != null) {
                EntityPaymentDetails entityPaymentDetails;

                for (EntityProductDetails x : lstProductDetails) {
                    int quantity = x.getQuantity();
                    entityPaymentDetails = new EntityPaymentDetails();
                    entityProductDetails = _iServiceProductDetails.findById(x.getId() + "");
                    if (entityProductDetails != null) {
                        entityPaymentDetails.setEntityPayment(entityPayment);
                        entityPaymentDetails.setEntityProductDetails(entityProductDetails);
                        entityPaymentDetails.setQuantity(quantity);
                        entityPaymentDetails.setPrice(x.getPrice() * quantity);
                        entityPaymentDetails.setPriceSale((x.getPrice() * x.getEntityProduct().getPersenSale() / 100));
                        _iServicePaymentDetails.newPaymentDetails(entityPaymentDetails);
                    }
                }
                CookieUtils.getCookieUtils().setCookieUtils("cart", "", response);
                String paymentmethod = request.getParameter("payments");
                if (entityPayment.getEntityVoucher() != null) {
                    EntityVoucher entityVoucher = _iServiceVoucher.findById(entityPayment.getEntityVoucher().getId());
                    if (entityVoucher != null) {
                        entityVoucher.setReQuantity(entityVoucher.getReQuantity() - 1);
                        boolean flag = _iServiceVoucher.updateVoucher(entityVoucher);
                        System.out.println(flag);
                    }
                }
                if (paymentmethod != null) {
                    if (paymentmethod.equals("cod")) {
                        response.sendRedirect(request.getContextPath() + "/order?payment-method=cod");
                        return;
                    } else if (paymentmethod.equals("qrcode")) {
                        request.setAttribute("paymentId", entityPayment.getId());
                        request.setAttribute("totalAmount", paymentAmount);
                        request.getRequestDispatcher("/views/website/page/cart/qrCode.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/checkout");
    }
}
