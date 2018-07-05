<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<script type="text/x-template"  id="addUser">
    <div id="userlist" style="width:1500px">
        <el-container>
            <el-header>
                    <el-row>
                        <el-col :span="8">
                            <h5><i class="el-icon-location-outline"> &nbsp;&nbsp;&nbsp;当前位置:OA管理系统>>>用户管理>>>添加用户</i></h5>
                        </el-col>
                    </el-row>
            </el-header>
            <el-main>
                <div >
                    <el-row>
                        <el-col :span="8" :offset="5">
                            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                                <el-form-item label="姓   名:"  prop="username">
                                    <el-input v-model="form.username"></el-input>
                                </el-form-item>
                                <el-form-item label="状   态:" prop="status">
                                    <el-select v-model="form.status"  style="width: 100%" placeholder="请选择">
                                        <el-option  label="激活"  value="0"> </el-option>
                                        <el-option  label="未激活"  value="1"></el-option>
                                    </el-select>
                                </el-form-item>
                                <el-form-item label="登录名:" prop="loginname">
                                    <el-input v-model="form.loginname"></el-input>
                                </el-form-item>
                                <el-form-item label="密  码:" prop="password">
                                    <el-input :type="type" v-model="form.password"></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="onSubmit('form')">立即创建</el-button>
                                    <el-button @click="doReset('form')">取消</el-button>
                                </el-form-item>
                            </el-form>
                            </el-col>
                    </el-row>
                </div>
            </el-main>
        </el-container>
    </div>
</script>
<style type="text/css">
    .invisible {
        background-image: url(data:image/png;base64,iVBORw0K);
        height: .5rem;
        top: .7rem;
    }
    .visible {
        background-image: url(data:image/png;base64,iVBORw0KG);
        height: .8rem;
        top: .55rem;
    }

</style>
<script >
    const addUser =Vue.component("addUser",{
        template: '#addUser',
        data: function () {
            return {
                      type:"password",
                      form:{
                          username:"",
                          status:"0",
                          loginname:"",
                          password:""
                      },
                      rules:{
                          username: [
                              { required: true, message: '请输入姓名', trigger: 'blur' },
                              { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                          ],
                          status: [
                              { required: true, message: '请选择状态', trigger: 'blur' }
                          ],
                          loginname: [
                              { required: true, message: '请输入登陆名', trigger: 'blur' },
                              { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                          ],
                          password: [
                              { required: true, message: '请输入密码', trigger: 'blur' },
                              { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
                          ],
                      }
            }
        },
        methods: {
            showOrHidden:function(){
                if(this.type=='text'){
                    this.type='password'
                }else{
                    this.type='text'
                }
            },
            toList:function(_self){
                if(_self instanceof MouseEvent){
                    this.$router.push("/userslist");
                }else{
                    _self.$router.push("/userslist");
                }

            },
             doReset:function(formName){
                 this.$refs[formName].resetFields();
            },
            onSubmit:function(formName){
                console.log("onSubmit!");
                var url = "${pageContext.request.contextPath}/user/adduser";
                var _self=this;
                this.$refs[formName].validate(function(valid,object){
                    if (valid) {
                        $.ajax({
                            cache:true,//保留缓存数据
                            type:"POST",//为post请求
                            url:url,//这是我在后台接受数据的文件名
                            data:this.form,//将该表单序列化
                            async:false,//设置成true，这标志着在请求开始后，其他代码依然能够执行。如果把这个选项设置成false，这意味着所有的请求都不再是异步的了，这也会导致浏览器被锁死
                            error:function(request){//请求失败之后的操作
                                alert("error！")
                                return;
                            },
                            success:function(data){//请求成功之后的操作
                                if(data.flag=="true"){
                                    alert("创建成功！");
                                    doReset(_self);
                                    _self.$router.push("/userslist");

                                }else{
                                    alert("创建失败！"+data.message);
                                    //清空页面内容
                                    _self.doReset(_self);
                                }

                            }
                        });
                    } else {
                        alert("输入有误！");
                        return false;
                    }
            });
                //使用ajax发送请求到后台


            }

        }
    })



</script>