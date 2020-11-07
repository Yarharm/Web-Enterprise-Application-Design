package servlet;

import business_layer.MessageBoardManager;
import models.Attachment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static helpers.Constants.DISPLAY_WARNING_POPUP;
import static helpers.Constants.ROOT_PAGE;

@WebServlet(name = "DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private final int BINARY_SIZE = 4096;
    MessageBoardManager boardManager = new MessageBoardManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postID = request.getParameter("postID");
        try {
            if(postID == null || postID.isEmpty()) {
                throw new Exception("Download failed! Missing postID");
            }

            Attachment attachment = this.boardManager.getAttachment(Integer.parseInt(postID));
            if(attachment == null) {
                throw new Exception("Download failed! Attachment does not exist");
            }

            response.setContentType(attachment.getMediaType());
            response.setHeader("Content-disposition", "attachment; filename=" + attachment.getFileName());

            try(OutputStream out = response.getOutputStream()) {
                byte[] buffer = new byte[BINARY_SIZE];

                int numBytesRead;
                while ((numBytesRead = attachment.getAttachmentBinary().read(buffer)) > 0) {
                    out.write(buffer, 0, numBytesRead);
                }
            }
        } catch (Exception e) {
            request.getServletContext().setAttribute(DISPLAY_WARNING_POPUP, e.getMessage());
            response.sendRedirect(ROOT_PAGE);
        }
    }
}
