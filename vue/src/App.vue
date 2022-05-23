<template>

<div>
  <!--    头部-->
  <Header/>

  <!--  主体-->
  <div style="display: flex;">
    <!--  侧边栏-->
    <Aside/>
<!--    内容区域-->
    <HomeView style="flex: 1"/>
  </div>



</div>

</template>

<script>
  import Header from "@/components/Header";
  import HomeView from "@/views/HomeView";
  import Aside from "@/components/Aside";

  export default {
    name: "Layout",
    components:{
      Header,
      HomeView,
      Aside
    }
  }
</script>
