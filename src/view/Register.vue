<template>
<div id="Register">
  <el-form :label-position="labelPosition" label-width="80px" :model="formList" :rules="rule" ref="formList" @keydown.enter.native="register(formList)">
    <div class="el-icon-back" @click="back"></div>
    <el-form-item prop="username">
      <el-input v-model="formList.username" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input v-model="formList.password" placeholder="密码" type="password"></el-input>
    </el-form-item>
    <el-form-item prop="checkpass">
      <el-input v-model="formList.checkpass" placeholder="再次输入密码" type="password"></el-input>
    </el-form-item>
    <el-form-item prop="email">
      <el-input v-model="formList.email" placeholder="Email" type="email"></el-input>
    </el-form-item>
    <el-button type="primary" @click="register(formList)" :loading="loading">注册</el-button>
  </el-form>
</div>
</template>

<script>
import Qs from 'qs';
import {post} from "../../network/request.js";

export default {
  name: "admin",
  data() {
    let validatepass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("密码为空!"));
      } else {
        if (this.formList.checkpass !== '') {
          this.$refs.formList.validateField('checkpass');
        }
        callback();
      }
    };
    let validatepass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error("请再次输入密码!"))
      } else if (value !== this.formList.password) {
        callback(new Error("两次密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      labelPosition: 'right',
      formList: {
        ip:cip,
        username: '',
        password: '',
        checkpass: '',
        email: ''
      },
      rule: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}, {
          min: 3,
          max: 10,
          message: '长度在 3 到 10 个字符',
          trigger: 'blur'
        }],
        password: [{validator: validatepass, required: true, message: '请输入密码', trigger: 'blur'}, {
          min: 6,
          max: 11,
          message: '长度在 6 到 11 个字符',
          trigger: 'blur'
        }],
        checkpass: [{validator: validatepass2, required: true, trigger: 'blur'}, {
          min: 6,
          max: 11,
          message: '长度在 6 到 11 个字符',
          trigger: 'blur'
        }],
        email: [{required: true, message: '邮箱格式不对', trigger: 'blur', type: 'email'}],
      }
    }
  },
  methods: {
   async register(formList) {
      this.loading = true
     /*       console.log(JSON.stringify(formList))*/
      await this.$refs.formList.validate((async valid => {
        if (!valid) {
          this.$message.error("请输入正确格式信息!")
          return this.loading = false;
        } else {
          await post('register', Qs.parse(formList)).then(res => {
            switch (res.data.meta.status) {
              case 200:
                this.$router.push('/login');
                this.$message.success("注册成功!");
                break;
              case 404:
                this.$message.error("注册失败!");
                this.loading = false;
                break;
              case 405:
                this.$message.error("用户名已存在!")
                this.loading = false;
                break;
              default :
                this.$message.error("请求失败")
                return this.loading = false
            }
          })
        }
      }))
    },
    back() {
      this.$router.go(-1);
    },
  }
}
</script>

<style scoped>
#Register{
  height: 100%;
  background-image: url("../assets/pexels-chris-kane-166360.jpg");
  background-size: cover;
}
.el-form{
  position: absolute;
  box-sizing: border-box;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  padding:80px 20px 0 0;
  width: 360px;
  height: 460px;
  border-radius: 10px;
  background-color: aliceblue;
  opacity: 0.8;
}
.el-form-item{
  position: relative;
  right: 27px;

}
.el-button{
  width: 200px;
  margin:10px 0 0 80px;
  border-radius: 20px;
}
.el-icon-back{
  cursor: pointer;
  position: absolute;
  top: 0;
  left: 0;
}
</style>
