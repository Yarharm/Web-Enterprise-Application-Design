package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static helpers.Constants.ROOT_PAGE;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    private final static List<String> CAN_REACH_ROUTES_WITHOUT_AUTH = Arrays.asList("login.jsp", "index.jsp", "LoginServlet");
    private final static String ROOT_URI = "/MessageBoardSystem_war/";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        boolean authenticated = request.getSession().getAttribute("userID") != null;

        if(!requestURI.equals(ROOT_URI) && !authenticated) {
            boolean isProtectedURI = true;
            for(String reachableRoute : CAN_REACH_ROUTES_WITHOUT_AUTH) {
                isProtectedURI = !requestURI.contains(reachableRoute) && isProtectedURI;
            }

            if(isProtectedURI) {
                response.sendRedirect(ROOT_PAGE);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
