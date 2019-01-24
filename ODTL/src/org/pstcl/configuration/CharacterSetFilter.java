package org.pstcl.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterSetFilter implements Filter {
	private static final String UTF8 = "UTF-8";
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private String encoding;

	public void init(final FilterConfig config) throws ServletException {
		this.encoding = config.getInitParameter("requestCharEncoding");
		if (this.encoding == null) {
			this.encoding = "UTF-8";
		}
	}

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.encoding);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}