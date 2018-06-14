<template>
  <div id="home">
      {{msg}}
  </div>
</template>

<style>
  body {
    background-color: #ff0000;
  }
</style>

<script>
    Vue.component('home',
            { template: '#home',
              data:function(){
                  return data:{
                      msg:"我是home文件"
                  };
              }
            }
    );
</script>