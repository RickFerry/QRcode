package br.com.fatec.oauth.token;

import org.apache.catalina.util.ParameterMap;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class RefreshTokenCookiePreProcessorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        if ("/oauth/token".equalsIgnoreCase(request.getRequestURI())
                        && "refresh_token".equals(req.getParameter("grant_type"))
                        && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    String cookieValue = cookie.getValue();
                    request = new MyServletRequestWrapper(request, cookieValue);
                }
            }
        }
        chain.doFilter(request, resp);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    static class MyServletRequestWrapper extends HttpServletRequestWrapper {

        private final String refreshtoken;

        public MyServletRequestWrapper(HttpServletRequest request, String refreshToken) {
            super(request);
            this.refreshtoken = refreshToken;
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            ParameterMap<String, String[]> map = new ParameterMap<>(getRequest().getParameterMap());
            map.put("refresh_token", new String[]{refreshtoken});
            map.setLocked(true);
            return map;
        }
    }
}
