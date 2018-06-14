<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/x-template"  id="home">
    <div>
        <span>{{ message }}</span>
        <el-button @click="updateMessage"></el-button>
    </div>
</script>
<script >
    const home =Vue.component("home",{
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
    })
</script>