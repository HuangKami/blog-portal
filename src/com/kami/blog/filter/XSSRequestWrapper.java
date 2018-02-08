package com.kami.blog.filter;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
	private static Policy policy;

    static {
        try {
        	String path = XSSRequestWrapper.class.getResource("/antisamy-slashdot.xml").getPath();
            policy = Policy.getInstance(path);
        } catch (PolicyException e) {
            e.printStackTrace();
        }
    }

	public XSSRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        if (str == null)
            return null;
        return xssClean(str);
    }

	@Override
    public String getHeader(String paramString) {
        String str = super.getHeader(paramString);
        if (str == null)
            return null;
        return xssClean(str);
    }
    
	@Override
    @SuppressWarnings("rawtypes")
    public Map<String, String[]> getParameterMap() {
        @SuppressWarnings("unchecked")
		Map<String, String[]> request_map = super.getParameterMap();
        Iterator iterator = request_map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry me = (Map.Entry) iterator.next();
            String[] values = (String[]) me.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = xssClean(values[i]);
            }
        }
        return request_map;
    }

    public String[] getParameterValues(String paramString) {
        String[] arrayOfString1 = super.getParameterValues(paramString);
        if (arrayOfString1 == null)
            return null;
        int i = arrayOfString1.length;
        String[] arrayOfString2 = new String[i];
        for (int j = 0; j < i; j++)
            arrayOfString2[j] = xssClean(arrayOfString1[j]);
        return arrayOfString2;
    }

    private String xssClean(String value) {
        AntiSamy antiSamy = new AntiSamy();
        try {
            final CleanResults cleanResults = antiSamy.scan(value, policy);
            return cleanResults.getCleanHTML();
        } catch (ScanException e) {
            e.printStackTrace();
        } catch (PolicyException e) {
            e.printStackTrace();
        }
        return value;
    }

}
