<template>
<div id="index">
  <el-container class="contain">
    <el-header>
      <div class="btns">
        <el-button type="primary">首页</el-button>
        <el-button @click="logout">退出</el-button>
      </div>
    </el-header>
    <el-container class="contain">
      <el-aside width="210px">
        <el-menu  default-active="2"
                  class="el-menu-vertical-demo"
                  background-color="#E9EEF3"
                  text-color="#545c64"
                  active-text-color="#545c64" router>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-goods"></i>
              <span>购物</span>
            </template>
            <el-menu-item-group>
              <template slot="title">潮鞋</template>
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[0]}}">跑鞋</el-menu-item>
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[1]}}">板鞋</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="潮服">
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[2]}}">格子衫</el-menu-item>
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[3]}}">工装服</el-menu-item>
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[4]}}">夹克</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">手机</template>
              <el-menu-item index="/commodity" :route="{path:'/commodity',query:{query:this.data[5]}}">爱国者</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="/introduction">
            <i class="el-icon-menu"></i>
            <span slot="title">介绍</span>
          </el-menu-item>
          <el-menu-item index="/myOrder">
            <i class="el-icon-setting"></i>
            <span slot="title">我的订单</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
     <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<script>
export default {
  name: "index",
  data(){
    return{
      data:["跑鞋","板鞋","格子衫","工装服","夹克","手机"]
    }
  },
  methods:{
    logout(){
      window.sessionStorage.clear();
      localStorage.clear();
      this.$router.push('/login')
    },
  }
}
</script>

<style scoped>
#index{
  height: 100%;
}
.el-header{
  position: relative;
  background-color: #E9EEF3;
  width: 100%;
}
.el-aside{
  background-color:#E9EEF3;
}
.btns{
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.el-main{
  margin: 0;
  padding: 0;
  background-color: #E9EEF3;
}
.contain{
  height: 100%;
}
img{
  background-size: cover;
  width: 100%;
  height: 100%;
}
</style>
