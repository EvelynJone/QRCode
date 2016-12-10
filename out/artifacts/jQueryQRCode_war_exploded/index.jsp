<%--
  Created by IntelliJ IDEA.
  User: zhaoxl
  Date: 2016/12/10
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/js/jquery.qrcode.min.js"></script>
    <title>QRCode</title>
  </head>
  <body>
  QRCode: <br/>
  <div id="qrcode"></div>
  <script type="text/javascript">
    jQuery('#qrcode').qrcode("http://www.baidu.com");
  </script>
  </body>
</html>
