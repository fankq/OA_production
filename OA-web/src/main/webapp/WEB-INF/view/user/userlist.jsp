<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<script type="text/x-template"  id="users">
    <div id="userlist"  style="width:1500px;height:800px;text-align: left">
        <el-container>
            <el-header>
                    <el-row>
                        <el-col :span="8">
                            <h5><i class="el-icon-location-outline"> &nbsp;&nbsp;&nbsp;当前位置:OA管理系统>>>用户管理>>>用户查询</i></h5>
                        </el-col>
                    </el-row>
            </el-header>
            <el-main >
                <!--用户信息查询条件-->
                <el-row type="flex" justify="left">
                    <el-col :span="24">
                    <el-form ref="form1" :inline="true" :model="form1" label-width="80px">
                        <el-form-item label="用户名" prop="username">
                            <el-input  v-model="form1.username"></el-input>
                        </el-form-item>
                        <el-form-item label="用户状态" prop="status">
                            <el-select v-model="form1.status" placeholder="请选择">
                                <el-option label="激活  " value="0"></el-option>
                                <el-option label="未激活" value="1"></el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="queryUsers">查询</el-button>
                            <el-button @click="reset('form1')">取消</el-button>
                        </el-form-item>
                    </el-form>
                        </el-col>
                </el-row>
                <!--用户信息结果展示页面-->
                <el-row>
                    <el-col>
                        <el-table>

                        </el-table>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</script>
<style type="text/css">
</style>
<script >
    const users =Vue.component("users",{
        template: '#users',
        data: function () {
            return {
              form1:{
                  username:"",
                  status:"0"
              }
            }
        },
        methods: {
            queryUsers:function(){
                var url = "${pageContext.request.contextPath}/user/selectUser"
                $.ajax(
                        {
                            url:url,
                            cache:true,//保留缓存数据
                            type:"POST",//为post请求
                            data:this.form1,
                            async:true,//设置成true，这标志着在请求开始后，其他代码依然能够执行。如果把这个选项设置成false，这意味着所有的请求都不再是异步的了，这也会导致浏览器被锁死
                            error:function(request){//请求失败之后的操作
                                alert("error!");
                            },
                            success:function(data){//请求成功之后的操作

                            console.log(data);
                            }
                        }
                )
                console.log("查询");
            },
            reset:function(formName){
                this.$refs[formName].resetFields();
                console.log("reset");
            }
        }
    })
</script>