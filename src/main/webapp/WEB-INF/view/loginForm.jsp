<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>人事管理系统登陆页面</title>
</head>
<style>
    .el-row {
        margin-bottom: 20px;
        &:last-child {
             margin-bottom: 0;
         }
    }
    body{
        background-image:url("statics/imgs/login/backlogin.jpg") ;
    }
    .login-box {

        border:0px red solid;
        margin-top:15%;
    }
    .loginborder{
        opacity:0.95;
        box-shadow:10px 8px 10px #a2bedd;
        transform: rotate(0deg);
        border-radius: 10px;
        background-color: #c8dee6;
        padding-bottom: 10%;
        padding-top:5%;
        border:1px green solid;
    }
</style>
<body>

    <div class="login-box" id="app" >
        <el-row>
            <el-col :span="8" :offset="8">
                <div class="loginborder">
                    <el-row>
                        <el-col :span="12" :offset="6">
                            <div align="center">
                                <h1>OA管理系统</h1>
                            </div>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12" :offset="6">
                            <el-input id="loginname"  v-model="loginname" placeholder="请输入帐号">
                                <template slot="prepend">帐号</template>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12" :offset="6">
                            <el-input id="password" v-model="password" type="password" placeholder="请输入密码">
                                <template slot="prepend">密码</template>
                            </el-input>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12" :offset="6">
                            <el-button id="login" v-on:click="check" style="width:100%" type="primary">登录</el-button>
                        </el-col>
                    </el-row>
                 </div>
            </el-col>
        </el-row>
    </div>
</body>
<jsp:include  page="vueElementUI.jsp"/>
<script type="text/javascript">
    base = "${pageContext.request.contextPath}";
    new Vue({
        el : '#app',
        data : {
            loginname : '',
            password : ''
        },
        methods : {
            check : function(event){
                //获取值
                var loginname = this.loginname;
                var password = this.password;
                if(loginname == '' || password == ''){
                    this.$message({
                        message : '账号或密码为空！',
                        type : 'error'
                    })
                    return;
                }
                $.ajax({
                    url :base+ '/login',
                    type : 'post',
                    data : {
                        loginname : loginname,
                        password : password
                    },
                    success : function(data) {

                        alert(data.message);
                    },
                    error : function(data) {
                        alert(data);
                    },
                    dataType : 'json',
                })
            }
        }
    })
</script>
</html>
