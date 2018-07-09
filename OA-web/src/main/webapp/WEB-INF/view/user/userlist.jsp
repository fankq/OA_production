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
                                <el-option
                                        v-for="item in selectItem"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value">
                                </el-option>
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
                        <el-table  :data="tableData" stripe style="width: 100%">
                            <el-table-column prop="id" label="id"  width="180"> </el-table-column>
                            <el-table-column prop="loginname" label="登录名"  width="180"> </el-table-column>
                            <el-table-column  label="状态" width="180">
                                <template slot-scope="scope">
                                    <el-select v-model="scope.row.status" :disabled = "!scope.row.editFlag">
                                        <el-option
                                                v-for="item in selectItem"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value" >
                                        </el-option>
                                    </el-select>
                                </template>
                            </el-table-column>
                            <el-table-column prop="createdate" :formatter="formatterDate" label="创建时间"> </el-table-column>
                            <!--单元格可编辑-->
                            <el-table-column label="姓名">
                                <template slot-scope="scope">
                                <span v-if="!scope.row.editFlag">{{ scope.row.username }}</span>
                                <span v-if="scope.row.editFlag" class="cell-edit-input"><el-input v-model="scope.row.username" placeholder="请输入内容"></el-input></span>
                                </template>
                            </el-table-column>
                           <el-table-column  label="操作">
                                <template slot-scope="scope">
                                    <span v-if="!scope.row.editFlag"><el-button type="text" size="small" @click="handleClick(scope.row,'save')">编辑</el-button></span>
                                    <span v-if="scope.row.editFlag"><el-button type="text" size="small" @click="handleClick(scope.row,'save')">保存</el-button></span>
                                    <el-button type="text" size="small" @click="handleClick(scope.row,'delete')">移除</el-button>
                                </template>
                            </el-table-column>

                        </el-table>
                    </el-col>
                    <el-col>
                        <div class="block" align="center">
                            <el-pagination
                                    layout="prev, pager, next,total"
                                    :total="itemCount"
                                    @current-change="handleCurrentChange"
                                    :page-size="pageSize"
                                    :current-page = "form1.pageIndex">
                            </el-pagination>
                        </div>
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
                  status:0,
                  pageIndex:1
              },
                selectItem:[{
                    value: 0,
                    label: '激活'
                }, {
                    value: 1,
                    label: '未激活'
                }]
                ,
                pageSize:4,
                tableData:[],
                itemCount:0
            }
        },
        methods: {
            formatterDate:function(row, column, cellValue, index){
                console.log(cellValue);
                var date = new Date(cellValue);
                return  date.format("yyyy-MM-dd hh:mm:ss");
            },
            handleCurrentChange:function(val){
                this.form1.pageIndex=val;
                this.queryUsers();
            },
            handleClick:function(row,type){
                    if(type=='delete'){
                        this.updateUser(row,type)
                    }else if(type=='save'){
                        if(!row.editFlag) {
                            row.editFlag = true;
                        }else{//更新当前用户信息
                            row.editFlag = false;
                            this.updateUser(row,type)
                        }
                    }
            },
            updateUser:function(data,type){//更新用户信息
                var url;
                console.log(this.form1);
                console.log(data);
                var _self = this;
                if(type=='save'){
                    url ="${pageContext.request.contextPath}/user/updateUser"
                }else if(type=='delete'){
                    url = "${pageContext.request.contextPath}/user/removeUser"
                }
                $.ajax(
                        {
                            url:url,
                            cache:true,//保留缓存数据
                            type:"POST",//为post请求
                            data:data,
                            async:true,//设置成true，这标志着在请求开始后，其他代码依然能够执行。如果把这个选项设置成false，这意味着所有的请求都不再是异步的了，这也会导致浏览器被锁死
                            error:function(request){//请求失败之后的操作
                                alert("error!");
                            },
                            success:function(data){//请求成功之后的操作
                                if(data.flag=='true'){
                                    alert("操作成功！");
                                    _self.queryUsers();
                                }
                            }
                        }
                )
            },
            queryUsers:function(){
                var _self = this;

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
                                _self.tableData = data.userInfos;
                                _self.itemCount = data.pageModel.recordCount;
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

    Date.prototype.format = function (format) {
        var args = {
            "M+": this.getMonth() + 1,
            "d+": this.getDate(),
            "h+": this.getHours(),
            "m+": this.getMinutes(),
            "s+": this.getSeconds(),
            "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
            "S": this.getMilliseconds()
        };
        if (/(y+)/.test(format))
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var i in args) {
            var n = args[i];
            if (new RegExp("(" + i + ")").test(format))
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
        }
        return format;
    };
</script>