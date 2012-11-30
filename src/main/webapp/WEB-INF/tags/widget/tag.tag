<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="tag" required="true" type="java.lang.String"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<a href="/tag/${fn:escapeXml(tag)}" style="font-size: 14px">${fn:escapeXml(tag)}</a>

