<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<script type="text/x-template"  id="home">
    <div id="title">
        <h1 class="default">{{username}}欢迎登录OA系统</h1>
    </div>
</script>
<style type="text/css">
    #title h1{
        top: 100px;
        left: 100px;
        color: #3a8ee6;
    }
</style>
<script >
    const home =Vue.component("home",{
        template: '#home',
        data: function () {
            return {
                message: '没有更新',
                username:'${sessionScope.user_session.username}'
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