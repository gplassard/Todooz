<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="tag" required="true" type="java.lang.String"%>

<a href="/tag/${tag}" style="font-size: 14px">${tag }</a>

