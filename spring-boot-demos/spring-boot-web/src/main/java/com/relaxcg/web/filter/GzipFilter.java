package com.relaxcg.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author relaxcg
 * @date 2024/1/12 17:26
 */
// @Component
@Slf4j
// @WebFilter(filterName = "gzipFilter", urlPatterns = "/*")
public class GzipFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter");
        String contentEncoding = ((HttpServletRequest) request).getHeader("Content-Encoding");
        if (contentEncoding != null && contentEncoding.contains("gzip")) {
            request = new GzipHttpServletRequestWrapper((HttpServletRequest) request);
        }
        chain.doFilter(request, response);
    }

    private static class GzipHttpServletRequestWrapper extends HttpServletRequestWrapper {

        /**
         * Constructs a request object wrapping the given request.
         *
         * @param request The request to wrap
         * @throws IllegalArgumentException if the request is null
         */
        public GzipHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            log.info("getInputStream");
            ServletInputStream inputStream = getRequest().getInputStream();

            try (GZIPInputStream gzip = new GZIPInputStream(inputStream);
                 ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                int len;
                byte[] buffer = new byte[1024];
                while ((len = gzip.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                byte[] unzippedData = bos.toByteArray();

                ByteArrayInputStream bis = new ByteArrayInputStream(unzippedData);
                return new ServletInputStream() {
                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setReadListener(ReadListener listener) {

                    }

                    @Override
                    public int read() throws IOException {
                        return bis.read();
                    }
                };
            }
        }
    }
}
