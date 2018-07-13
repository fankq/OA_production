<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<script type="text/x-template"  id="upload">
    <div  style="width:1500px">
        <el-container>
            <el-header>
                <el-row>
                    <el-col :span="8">
                        <h5><i class="el-icon-location-outline"> &nbsp;&nbsp;&nbsp;当前位置:OA管理系统>>>下载中心>>>上传文件</i></h5>
                    </el-col>
                </el-row>
            </el-header>
            <el-main>
                <el-row>
                    <el-col >
                        <el-upload
                                ref="upload"
                                class="upload-demo"
                                    action="${pageContext.request.contextPath}/document/upload"
                                :on-preview="handlePreview"
                                :data="documentData"
                                :on-remove="handleRemove"
                                :before-remove="beforeRemove"
                                multiple
                                :onError="uploadError"
                                :onSuccess="uploadSuccess"
                                list-type="picture"
                                :limit="3"
                                :auto-upload="false"
                                :on-exceed="handleExceed"
                                :file-list="fileList">
                            <el-button size="small" slot="trigger" type="primary">选取文件</el-button>
                            <el-button style="margin-left: 10px;" size="small" type="success" @click="centerDialogVisible = true">上传到服务器</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                        </el-upload>
                        <el-dialog
                                title="上传文件"
                                :visible.sync="centerDialogVisible"
                                width="30%"
                                center>
                            <span>
                                <el-form ref="documentData" :model="documentData" label-width="80px">
                                    <el-form-item label="文档标题">
                                    <el-input type="text" v-model="documentData.title"  placeholder="请填写文档标题"/>
                                    </el-form-item>
                                    <el-form-item label="文档描述">
                                        <el-input type="text" v-model="documentData.remark" placeholder="请填写文档描述"/>
                                    </el-form-item>
                                </el-form>

                            </span>
                              <span slot="footer" class="dialog-footer">
                                <el-button @click="centerDialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="submitUpload">确 定</el-button>
                              </span>
                        </el-dialog>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </div>
</script>
<style type="text/css">
    .upload-demo .el-upload-list{
        height: 20px;
    }
</style>
<script>
    const upload =Vue.component("upload",{
        template: '#upload',
        data:function() {
            return {
                centerDialogVisible:false,
                documentData:{
                    title:"",
                    remark:""
                },
                fileList: []
            };
        },
        methods: {

            uploadError(response, file, fileList){
                console.log('上传文件失败', response);

            },
            uploadSuccess(response, file, fileList){
                console.log('上传文件成功', response,file,fileList);
                if(response.flag=='success'){
                    if (fileList instanceof Array){
                        var file1;
                        for(var i in fileList){
                            file1 = fileList[i];
                            console.log(file1);
                            if(file.uid ==file1.uid)
                            file1["id"]=response.docId;
                            console.log(file1);
                        }

                    }
                }else{
                    this.$message.warning("上传出错");
                }
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
                return false;

            },
            handlePreview(file) {
                console.log('handlePreview'+file);
            },
            handleExceed(files, fileList) {
                this.$message.warning(`当前限制选择 3 个文件，本次选择了 `+files.length+`个文件，共选择了 `+ (files.length + fileList.length) +`个文件`);
            },
            beforeRemove(file, fileList) {
                console.log(file, fileList);
                var isRemove=this.$confirm(`确定移除` +file.name +`？`);
                if(file.status=='success'){
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
                return isRemove;
            },
            submitUpload() {
                this.$refs.upload.submit();
                this.centerDialogVisible = false;
            }
        }
    });
</script>