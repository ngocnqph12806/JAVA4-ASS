package polymart.xyz.ass_jv4.controller.load;

import polymart.xyz.ass_jv4.entity.EntityProduct;
import polymart.xyz.ass_jv4.entity.EntityProductDetails;
import polymart.xyz.ass_jv4.entity.EntityProductImage;
import polymart.xyz.ass_jv4.service.IServiceProduct;
import polymart.xyz.ass_jv4.service.IServiceProductDetails;
import polymart.xyz.ass_jv4.service.IServiceProductImage;
import polymart.xyz.ass_jv4.service.implement.ServiceProduct;
import polymart.xyz.ass_jv4.service.implement.ServiceProductDetails;
import polymart.xyz.ass_jv4.service.implement.ServiceProductImage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletLoadProduct", value = "/load/product")
public class ServletLoadProduct extends HttpServlet {

    private IServiceProduct _iServiceProduct;
    private IServiceProductDetails _iServiceProductDetails;
    private IServiceProductImage _iServiceProductImage;

    @Override
    public void init() throws ServletException {
        _iServiceProduct = new ServiceProduct();
        _iServiceProductDetails = new ServiceProductDetails();
        _iServiceProductImage = new ServiceProductImage();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String idDetails = request.getParameter("idDetails");
        String imageBig = request.getParameter("imageBig");
        String view = request.getParameter("view");
        System.out.println(view);
        if (view != null) {
            EntityProduct product = _iServiceProduct.findById(view);
            if (product != null) {
                product.setView(product.getView() + 1);
                System.out.println(product);
                _iServiceProduct.updateProduct(product);
            }
        }
        if (id != null) {
            EntityProduct product = _iServiceProduct.findById(id);
            if (product != null) {
                String price = "0";
                if (idDetails != null && !idDetails.equals("")) {
                    EntityProductDetails entityProductDetails = _iServiceProductDetails.findById(idDetails);
                    if (entityProductDetails != null) {
                        price = entityProductDetails.getPrice() + "";
                        request.setAttribute("idDetails", idDetails);
                    }
                }
                String image = "";
                List<EntityProductImage> lst = _iServiceProductImage.findByIdProduct(product.getId() + "");
                if (lst != null && lst.size() > 0) {
                    if (imageBig != null && Integer.parseInt(imageBig) > -1) {
                        image = lst.get(Integer.parseInt(imageBig)).getImage();
                    } else {
                        image = lst.get(0).getImage();
                    }
                }
                System.out.println(image);
                request.setAttribute("imageBig", image);
                request.setAttribute("price", price);
                request.setAttribute("product", product);
                request.getRequestDispatcher("/views/admin/page/product/layout/modalShowDetailProduct.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin/product");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
