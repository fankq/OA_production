<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OA管理系统</title>
</head>
<body>
<div id="app">
    <el-container>
        <el-header>
            <el-row type="flex" justify="left">
                <el-col :span="3" :offset="1">
                  <span style="color: #ffffff;" ><h1>O A 管 理 系 统</h1></span>
                </el-col>
            </el-row>
        </el-header>
        <el-container>
            <el-aside  :width="size"  style="height: 1000px">

                <el-row>
                    <el-col :span="1">
                        <el-button  style="width:220px;margin:0px" :class="{ active: isActive}" icon="el-icon-d-arrow-left" @click="changeSize"></el-button>
                        <el-button  style="width:60px;margin:0px"  :class="{ active: !isActive }" icon="el-icon-d-arrow-right" @click="changeSize"></el-button>

                    </el-col>
                    <el-col :span="24">
                <el-menu
                        router
                        :model="model"
                        :collapse="isCollapse"
                        default-active="2"
                        :unique-opened = "isUnique"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffea57">
                    <el-submenu index="1">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>用户管理</span>
                        </template>
                            <el-menu-item index="/userslist">用户查询</el-menu-item>
                            <el-menu-item index="/addUser">添加用户</el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>部门管理</span>
                        </template>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>职位管理</span>
                        </template>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>员工管理</span>
                        </template>
                    </el-submenu>
                    <el-submenu index="5">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>公告管理</span>
                        </template>
                            <el-menu-item index="/noticeInfo">评论管理</el-menu-item>
                            <el-menu-item index="/deployNotice">发布公告</el-menu-item>
                    </el-submenu>
                    <el-submenu index="6">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>下载中心</span>
                        </template>
                    </el-submenu>
                </el-menu>
            </el-col>
            </el-row>
            </el-aside>
            <el-main>
                <router-view></router-view>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<jsp:include  page="vueElementUI.jsp"/>
<style type="text/css">
    .el-header, .el-footer {
        background-color: #B3C0D1;
        color: #333;
        height:100px;!important;
    }
    .active{
        display: none;
    }
    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
    }

    .el-main {
        overflow:visible;
        background-color: #E9EEF3;
        color: #333;

    }

    body > .el-container {
        margin-bottom: 40px;
    }

</style>
<jsp:include page="home.jsp"/>
<jsp:include  page="user/userlist.jsp"/>
<jsp:include  page="user/addUser.jsp"/>

<script type="text/javascript">

    const routes = [
        {
            path:"/",
            component: home
        },
        {
            path:"/userslist",
            component: users
        },
        {
            path:"/addUser",
            component: addUser
        }
    ]

    var router =  new VueRouter({
        routes
    })

    const app = new Vue({
        router,
        el:'#app',
        data:{
            size:"220px",
            model:"horizontal",
            isUnique:true,
            isCollapse:false,
            msg:11111,
            isActive:false
        },
        methods:{
            changeSize:function(){
                if(this.isCollapse) this.isCollapse = false;
                else this.isCollapse = true;
                if(!this.isCollapse){
                    this.isActive = false;
                    this.size="220px";
                }
                else {
                    this.isActive= true;
                    this.size="60px";

                }
            }
        }
    })
</script>
</html>
