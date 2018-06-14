<%--
  Created by IntelliJ IDEA.
  User: fankq
  Date: 2018/6/9
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <div id="default">
                您好，{{username}}欢迎登录OA管理系统页面！
        </div>
</body>
<jsp:include page="vueElementUI.jsp"></jsp:include>
<script type="text/javascript">

    export default {
        data (){
            return {
                username:"fankq"
            }
        }
    }
</script>
</html>
