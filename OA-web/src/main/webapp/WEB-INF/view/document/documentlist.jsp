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
                            <el-table-column prop="id" label="id"  width="100"> </el-table-column>
                            <el-table-column prop="filename" label="文件名"  width="180"> </el-table-column>
                            <el-table-column prop="title" label="标题"  width="180"> </el-table-column>
                            <el-table-column prop="createDate"  label="创建时间" :formatter="formatterDate" width="180"> </el-table-column>
                            <el-table-column prop="user.username" label="创建人"  width="100"> </el-table-column>
                            <el-table-column prop="remark" label="描述"  > </el-table-column>

                            <el-table-column  width="100"   label="操作">
                                <template slot-scope="scope">
                                   <el-button type="text" size="small" @click="handleClick(scope.row,'delete')">移除</el-button>
                                    <el-button type="text" size="small" @click="handleClick(scope.row,'download')">下载</el-button>
                                </template>
                            </el-table-column>

                        </el-table>
                    </el-col>
                    <el-col>

                    </el-col>
                </el-row>
            </el-main>
        </el-container>
        <%--下载--%>
        <form id="downLoad"  name="downLoad" method="post" action="${pageContext.request.contextPath}/document/download">
            <input id="downLoadId" type="hidden" value="" name="id"/>
        </form>
    </div>
</script>
<script>
    var documentList = Vue.component("documentList", {
        template: '#documentList',
        data:function(){
            return{
                form1:{
                    title:""
                },
                tableData:[]/*,
                pageSize:4,
                itemCount:0*/
            }
        },
        methods:{
            formatterDate:function(row, column, cellValue, index){
                console.log(cellValue);
                var date = new Date(cellValue);
                return  date.format("yyyy-MM-dd hh:mm:ss");
            },
            queryDocumentList:function(){
                var _self = this;

                var url = "${pageContext.request.contextPath}/document/query"
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
                                console.log(data.documentInfos);

                                /*
                                                                _self.itemCount = data.pageModel.recordCount;
                                */
                            }
                        }
                )
                console.log("查询");
            },
            reset:function(form){
                this.$refs[form].resetFields();
            },
            handleClick:function(data,type){
                console.log(data,type);
                var file = data;
                if(type=='delete'){
                    var isRemove=this.$confirm(`确定移除` +file.name +`？`);
                    if(isRemove){
                    var url = `${pageContext.request.contextPath}/document/delete`;
                    $.ajax({
                                cache:true,//保留缓存数据
                                type:"POST",//为post请求
                                url:url,//这是我在后台接受数据的文件名
                                data:{
                                    id:file.id
                                },//将该表单序列化
                                async:false,//设置成true，这标志着在请求开始后，其他代码依然能够执行。如果把这个选项设置成false，这意味着所有的请求都不再是异步的了，这也会导致浏览器被锁死
                                error:function(request){//请求失败之后的操作
                                    alert("error!")
                                    return;
                                },
                                success:function(data){//请求成功之后的操作
                                    console.log(data);
                                }
                            }
                    )
                    }
                }else if(type=='download'){
                    $("#downLoadId").val(file.id);
                     $("#downLoad").submit();
                }

            }

        }
    });
</script>