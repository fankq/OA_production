<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/x-template"  id="documentList">
    <div  style="width:1500px">
        <el-container>
            <el-header>
                <el-row>
                    <el-col :span="8">
                        <h5><i class="el-icon-location-outline"> &nbsp;&nbsp;&nbsp;当前位置:OA管理系统>>>下载中心>>>文档查询</i></h5>
                    </el-col>
                </el-row>
            </el-header>
            <el-main>
                <!--信息查询条件-->
                <el-row type="flex" justify="left">
                    <el-col :span="24">
                        <el-form ref="form1" :inline="true" :model="form1" label-width="80px">
                            <el-form-item label="标题" prop="title">
                                <el-input  v-model="form1.title"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="queryDocumentList">搜索</el-button>
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
                            <el-table-column prop="title" label="标题"  width="180"> </el-table-column>
                            <el-table-column prop="createdate" label="创建时间"> </el-table-column>
                            <el-table-column prop="username" label="创建人"  width="180"> </el-table-column>
                            <!--单元格可编辑-->
                            <el-table-column label="描述">
                                <template slot-scope="scope">
                                    <span v-if="!scope.row.editFlag">{{ scope.row.desc }}</span>
                                    <span v-if="scope.row.editFlag" class="cell-edit-input"><el-input v-model="scope.row.desc" placeholder="请输入内容"></el-input></span>
                                </template>
                            </el-table-column>
                            <el-table-column  label="操作">
                                <template slot-scope="scope">
                                    <span v-if="!scope.row.editFlag"><el-button type="text" size="small" @click="handleClick(scope.row,'save')">编辑</el-button></span>
                                    <span v-if="scope.row.editFlag"><el-button type="text" size="small" @click="handleClick(scope.row,'save')">保存</el-button></span>
                                    <el-button type="text" size="small" @click="handleClick(scope.row,'delete')">移除</el-button>
                                    <el-button type="text" size="small" @click="handleClick(scope.row,'download')">下载</el-button>
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
<script>
    var documentList = Vue.component("documentList", {
        template: '#documentList',
        data:function(){
            return{
                form1:{
                    title:"",
                    pageIndex:1
                },
                tableData:[],
                pageSize:4,
                itemCount:0
            }
        },
        methods:{
            queryDocumentList:function(){
                var _self = this;

                var url = "${pageContext.request.contextPath}/document/"
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
                                _self.tableData = data.documentInfos;
                                _self.itemCount = data.pageModel.recordCount;
                            }
                        }
                )
                console.log("查询");
            },
            reset:function(form){
                this.$refs[form].resetFields();
            },
            handleClick:function(data,type){

            },
            handleCurrentChange:function(val){

            }
        }
    });
</script>