package learn.digipet.jwt;

import learn.digipet.models.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private JwtConverter jwtConverter;

    public JwtFilter(JwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // this is where we apply security rules... in the example
        // the "app" always allows GET and OPTIONS requests...

        if (request.getMethod().equalsIgnoreCase("GET")
                || request.getMethod().equalsIgnoreCase("OPTIONS")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // always allow person to register & authenticate if they have the correct credentials...
        if (request.getServletPath().equalsIgnoreCase("/register")
                || request.getServletPath().equalsIgnoreCase("/authenticate")) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // could get fancy with roles... but here  we require a valid token for anything that
        // is not GET, OPTIONS, /register, or /authenticate
        User user = jwtConverter.getUserFromAuthHeader(request.getHeader("Authorization"));
        if (user == null) {
            response.setStatus(403);
            System.out.println("FORBIDDEN");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }

}
