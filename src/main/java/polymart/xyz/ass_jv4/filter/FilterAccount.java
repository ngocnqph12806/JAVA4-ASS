package polymart.xyz.ass_jv4.filter;

import polymart.xyz.ass_jv4.entity.EntityProductDetails;
import polymart.xyz.ass_jv4.entity.EntityStaff;
import polymart.xyz.ass_jv4.entity.EntityVisit;
import polymart.xyz.ass_jv4.entity.EntityVoucher;
import polymart.xyz.ass_jv4.service.*;
import polymart.xyz.ass_jv4.service.implement.*;
import polymart.xyz.ass_jv4.utils.CookieUtils;
import polymart.xyz.ass_jv4.utils.SessionUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static polymart.xyz.ass_jv4.utils.SessionUtils.getSessionUtils;

@WebFilter(filterName = "FilterAccount")
public class FilterAccount implements Filter {

    private IServiceProductDetails _iServiceProductDetails;
    private IServiceBanner _iServiceBanner;
    private IServiceProduct _iServiceProduct;
    private IServiceCategory _iServiceCategory;
    private IServiceBrand _iServiceBrand;
    private IServiceVoucher _iServiceVoucher;
    private IServiceVisit _iServiceVisit;

    public void init(FilterConfig config) throws ServletException {
        _iServiceProductDetails = new ServiceProductDetails();
        _iServiceBanner = new ServiceBanner();
        _iServiceProduct = new ServiceProduct();
        _iServiceCategory = new ServiceCategory();
        _iServiceBrand = new ServiceBrand();
        _iServiceVoucher = new ServiceVoucher();
        _iServiceVisit = new ServiceVisit();
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String url = ((HttpServletRequest) request).getRequestURI();
        System.out.println(url);
        if (url.contains(request1.getContextPath() + "/admin")) {
            if (getModelStaff(request1)) {
                chain.doFilter(request, response);
                return;
            }
            response1.sendRedirect(request1.getContextPath() + "/signin");
        } else if (url.equals(request1.getContextPath() + "/signin") || url.equals(request1.getContextPath() + "/signup")
                || url.equals(request1.getContextPath() + "/forgot-password")) {
            if (getModelStaff(request1)) {
                response1.sendRedirect(request1.getContextPath() + "/account?result=signin-successfully");
                return;
            }
            chain.doFilter(request, response);
        } else {
            String cookie = CookieUtils.getCookieUtils().getCookieUtils("cart", request1);
            if (cookie != null && !cookie.trim().equals("")) {
                String[] arrCart = cookie.split("c");
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
                List<EntityProductDetails> lstProductDetails = new ArrayList<>();
                EntityProductDetails entityProductDetails;
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
                request1.setAttribute("lstCart", lstProductDetails);
            }
            String voucher = SessionUtils.getSessionUtils().getSessionString("voucher", request1);
            if (voucher != null && !voucher.trim().equals("")) {
                EntityVoucher entityVoucher = _iServiceVoucher.findById(voucher);
                if (entityVoucher != null) {
                    request1.setAttribute("discountVoucher", entityVoucher.getPriceSale());
                }
            }
            EntityVisit entityVisit = SessionUtils.getSessionUtils().getSessionVisit("visit", request1);
            if (entityVisit != null) {
                entityVisit = _iServiceVisit.findByPhone(entityVisit.getPhoneNumber());
//                if (entityVisit != null) {
//                    request1.setAttribute("visit", entityVisit);
//                }else{
//                    SessionUtils.getSessionUtils().removeSession("visit", request1);
//                }
                request1.setAttribute("visit", entityVisit);
            }
            request1.setAttribute("lstNewImportProduct", _iServiceProduct.findNewImportProduct());
            request1.setAttribute("lstHotProduct", _iServiceProduct.findHotProduct());
            request1.setAttribute("product1", _iServiceProduct.findNewProduct().get(0));
            request1.setAttribute("lstCategory", _iServiceCategory.findAll());
            request1.setAttribute("lstBrand", _iServiceBrand.findAll());
            request1.setAttribute("lstTopBuyProduct", _iServiceProduct.findByTopBuy());
            request1.setAttribute("lstNewProduct", _iServiceProduct.findNewProduct());
            request1.setAttribute("lstHotSaleProduct", _iServiceProduct.findHotSaleProduct());
            request1.setAttribute("lstMostViewProduct", _iServiceProduct.findMostViewedProduct());
            request1.setAttribute("lstShowBanner", _iServiceBanner.findShowBanner());
            chain.doFilter(request, response);
        }
    }

    private boolean getModelStaff(HttpServletRequest request1) {
        EntityStaff modelStaff = getSessionUtils().getSessionStaff("user", request1);
        if (modelStaff != null && modelStaff.getEmail() != null) {
            IServiceAccount iServiceAccount = new ServiceAccount();
            modelStaff = iServiceAccount.findByEmail(modelStaff.getEmail());
        }
        return modelStaff != null && modelStaff.isActive() && !modelStaff.isBlocked() && !modelStaff.isRemoved();
    }

}
