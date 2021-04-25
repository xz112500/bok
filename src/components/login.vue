<template>
  <div id="main">
    <div id="login">
      <div class="a"></div>
      <form v-model="LoginForm" @keydown.enter="login">
      <div class="b">
        <h1>Login/Register</h1>
        <label >
          <input type="text" placeholder="用户名" v-model="LoginForm.username">
        </label>
        <label >
          <input type="password" placeholder="密码" v-model="LoginForm.password">
        </label><br>
        <div id="btns">
          <el-button type="primary" @click.native="login" :loading="loading">登录</el-button>
          <el-button type="primary" @click.native="Register">注册</el-button>
        </div>
      </div>
      </form>
    </div>
  </div>
</template>

<script>
import {post} from "../../network/request.js";
import Qs from 'qs';

export default {
  name: "login",
  data(){
    return{
      json:'',
      loading:false,
       LoginForm:{
       username:localStorage.getItem("username"),
       password:localStorage.getItem("password")
     }
    }
  },
  methods:{
   async login(){
     this.loading=true
     let json={username:this.LoginForm.username,password:this.LoginForm.password}
    await post('authority',Qs.stringify(json)).then( res=>{
       console.log(res)
       console.log(JSON.stringify(json))
       switch (res.data.status){
         case 401:
           this.$message.success("登录成功!")
           window.sessionStorage.setItem("token",res.data.data.token);
           window.sessionStorage.setItem("username",this.LoginForm.username)
           window.localStorage.setItem("username",this.LoginForm.username)
           window.localStorage.setItem("password",this.LoginForm.password)
           let user=JSON.stringify(this.LoginForm.username);
           this.$router.push({path:'/index',query:{user:user}})
           break;
         case 403:
           this.$message.error("请填写账号或密码!")
           this.loading=false
           break;
             case 404:
               this.$message.error("用户名或密码错误!")
               this.loading=false
               break;
           case 405:
       /*      this.$message.error("未注册!")*/
             this.loading=false
             break;
       }
     })
    },
    Register(){
   this.$router.push('/register')
    },
  },
  created() {
      this.login();
      setTimeout(function (){
        localStorage.clear();
      },60000 * 60 * 2)
  }
  /*  watch:{
      LoginForm:{
        handler(newname,oldname){
          console.log(newname)
          console.log(oldname)
        },
        deep:true,
        immediate:true
      }
    }*/
}
</script>

<style scoped>
 #main{
  height: 100%;
/*  background-image: linear-gradient(to left,#AAC3EE90, rgba(44, 69, 111, 0.56));*/
}
#login{
  position: absolute;
  display: flex;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  height: 400px;
  box-shadow: 0 5px 15px rgba(0,0,0,.8);
}
.a{
  width: 600px;
  background: url("../../src/assets/pexels-daria-shevtsova-1070945.jpg");
  background-size: cover;
}
.b{
  box-sizing: border-box;
  padding:30px 0 0 50px;
  width: 300px;
  height: 400px;
}
input {
  outline: none;
  border: 0;
  border-bottom: 2px solid dodgerblue;
  height: 40px;
  margin:20px 0 0 10px;
}
.el-button{
  margin:40px 0 0 20px;
}
</style>
