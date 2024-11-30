package vn.iotstar.controllers.admin.payment_method;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletFileUpload;
import vn.iotstar.entity.PaymentMethod;
import vn.iotstar.services.IPaymentService;
import vn.iotstar.services.implement.PaymentService;
import vn.iotstar.utils.Constant;
import vn.iotstar.utils.Utils;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/payment-method/edit"})
public class PaymentMethodEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    private final IPaymentService paymentMethodService = new PaymentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        if (paymentMethod != null) {
            req.setAttribute("paymentMethod", paymentMethod);
            req.getRequestDispatcher(Constant.PAYMENT_METHOD_EDIT).forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Payment method not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!JakartaServletFileUpload.isMultipartContent(req)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request does not contain upload data");
            return;
        }

        DiskFileItemFactory.Builder builder = DiskFileItemFactory.builder();
        builder.setBufferSize(MEMORY_THRESHOLD);
        
        String tempDir = System.getProperty("java.io.tmpdir");
        builder.setPath(Path.of(tempDir));

        DiskFileItemFactory factory = builder.get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        String webappPath = getServletContext().getRealPath("/");
        File rootDir = new File(webappPath).getParentFile().getParentFile();
        String uploadPath = rootDir + File.separator + "src" + File.separator + "main" + 
                          File.separator + "webapp" + File.separator + Constant.UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            List<FileItem> formItems = upload.parseRequest(req);
            PaymentMethod updatePaymentMethod = new PaymentMethod();
            
            // Lấy payment method hiện tại để giữ lại ảnh cũ nếu không upload ảnh mới
            int id = -1;
            
            for (FileItem item : formItems) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();

                    switch (fieldName) {
                        case "id":
                            id = Integer.parseInt(fieldValue);
                            updatePaymentMethod.setId(id);
                            break;
                        case "bankName":
                            updatePaymentMethod.setBankName(fieldValue);
                            break;
                        case "accountNumber":
                            updatePaymentMethod.setAccountNumber(fieldValue);
                            break;
                        case "accountOwner":
                            updatePaymentMethod.setAccountOwner(fieldValue);
                            break;
                        case "status":
                            updatePaymentMethod.setStatus(Integer.parseInt(fieldValue));
                            break;
                    }
                } else {
                    // Xử lý file upload
                    String fileName = new File(item.getName()).getName();
                    if (fileName != null && !fileName.isEmpty()) {
                        String newFileName = new Utils().generateUniqueFileName(fileName);
                        String filePath = uploadPath + File.separator + newFileName;
                        File storeFile = new File(filePath);
                        
                        try (InputStream input = item.getInputStream();
                             OutputStream output = new FileOutputStream(storeFile)) {
                            byte[] buffer = new byte[1024];
                            int length;
                            while ((length = input.read(buffer)) > 0) {
                                output.write(buffer, 0, length);
                            }
                        }
                        updatePaymentMethod.setImage("/uploads/" + newFileName);
                    } else {
                        // Nếu không upload ảnh mới, giữ lại ảnh cũ
                        PaymentMethod currentPaymentMethod = paymentMethodService.findById(id);
                        if (currentPaymentMethod != null) {
                            updatePaymentMethod.setImage(currentPaymentMethod.getImage());
                        }
                    }
                }
            }

            paymentMethodService.update(updatePaymentMethod);
            resp.sendRedirect(req.getContextPath() + "/admin/payment-method");

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error during file upload: " + e.getMessage());
        }
    }

}
