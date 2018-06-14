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
                            <span>导航栏</span>
                        </template>
                        <el-menu-item-group title="用户管理">
                            <el-menu-item index="/home">用户信息列表</el-menu-item>
                            <el-menu-item index="1-2">员工信息列表</el-menu-item>
                            <el-submenu index="1-3">
                                <template slot="title">职位管理</template>
                                <el-menu-item index="1-3-1">职位信息列表</el-menu-item>
                            </el-submenu>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>公告</span>
                        </template>
                        <el-menu-item-group title="公告管理">
                            <el-menu-item index="2-1">评论管理</el-menu-item>
                            <el-menu-item index="2-2">发布公告</el-menu-item>
                        </el-menu-item-group>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>文件管理</span>
                        </template>
                        <el-menu-item-group title="下载中心">
                            <el-menu-item index="3-1">文件上传</el-menu-item>
                            <el-menu-item index="3-2">文件下载</el-menu-item>
                        </el-menu-item-group>
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
        text-align: center;
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
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

</style>
<jsp:include page="home.jsp"/>
<script type="text/javascript">

    const routes = [
        {
            path:"/home",
            component: home
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
            changeSize(){
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
        },
        components:{
            "home":{
                 template: '#home',
                data: function () {
                return {
                    message: '没有更新'
                }
                },
        methods: {
            updateMessage: function () {
                this.message = '更新完成'
                console.log(this.$el.textContent) // => '没有更新'
                this.$nextTick(function () {
                    console.log(this.$el.textContent) // => '更新完成'
                })
            }
        }
    }
        }
    })
</script>
</html>
