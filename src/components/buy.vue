<template>
<div id="order">
  <el-form :model="OrderList" ref="OrderList" :rules="rule">
    <div><el-image :src="orderdata.img" alt=""></el-image></div>
    <span>商品名称:{{orderdata.name}}&nbsp价格:{{orderdata.price}}</span>
    <el-form-item prop="username">
      <el-input v-model="OrderList.username" placeholder="姓名"></el-input>
    </el-form-item>
    <el-form-item prop="address">
      <el-input v-model="OrderList.address" placeholder="配送地区"></el-input>
    </el-form-item>
    <el-form-item prop="phone">
      <el-input v-model="OrderList.phone" placeholder="电话号码"></el-input>
    </el-form-item>
    <el-form-item label="购买数量:" prop="counts">
      <el-input-number v-model="OrderList.counts" @change="handleChange" :min="1" :max="999" label="描述文字"></el-input-number>
    </el-form-item>
    <el-form-item prop="dates">
      <el-date-picker
        v-model="OrderList.dates"
        type="date"
        placeholder="选择日期" class="picktime">
      </el-date-picker>
    </el-form-item>
  </el-form>
  <el-button type="primary" class="btn1" :loading="loading" @click="confirm('OrderList')">确认订单</el-button>
  <el-button type="danger" class="btn2" @click="cancel">取消订单</el-button>
</div>
</template>

<script>
import Login from "./login.vue";
import {postcomm} from "../../network/request.js";
import Qs from 'qs'
export default {
  name: "buy",
  components:{
    Login,
  },
  data(){
    let data=JSON.parse(this.$route.query.name);
/*    let datas=JSON.parse(this.$route.query.datas);*/
    return{
      loading:false,
      Order:[],
      orderdata:data,
      OrderList:{
      uname:sessionStorage.getItem('username'),
      commodity:data.name,
      numbers:data.numbers,
      price:data.price,
      counts:'',
      allcount:data.counts,
      phone:'',
      username:'',
      address:'',
      dates:'',
      },
      rule:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 9, message: '长度在 1 到 9 个字符', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系电话', trigger: 'blur' },
          { min: 11, max: 11, message: '长度为 11', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' },
          { min: 6, max: 99, message: '至少 6 个字符', trigger: 'blur' }
        ],
        counts: [
          { required: true, message: '请输入数量', trigger: 'blur' },
        ],
        dates:[
          {required: true, message: '请输入日期', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    handleChange(value) {
  /*    console.log(value);*/
    },
    cancel(){
      this.$router.go(-1)
    },
    confirm(){
      console.log(this.OrderList)
      this.loading=true
      this.$refs.OrderList.validate((valid=>{
        if(!valid){
          this.$message.error("请输入正确格式!")
          return this.loading=false
        }else{
          postcomm('order',Qs.parse(this.OrderList)).then(res=>{
            switch (res.data.status){
              case 200:
                this.$message.success("下单成功")
                sessionStorage.setItem("order",JSON.stringify(res.data.meta.data))
             /*   console.log(res)*/
                this.$router.push('/success')
                return this.loading=false
              case 201:
                this.$message.error("下单失败")
                    return this.loading=false;
            }
          })
        }
      }))
    }
  }
}
</script>

<style scoped>
#order{
  position: relative;
  top: 40%;
  left: 40%;
  transform: translate(-50%,-50%);
  background: aliceblue;
  text-align: center;
  width: 400px;
  height: 750px;
}
.el-image{
 margin-top: 20px;
}
span{
  display: block;
  margin: 5px 0;
}
.el-form-item{
  margin-left: 30px;
}
.btn1{
  margin-top: 10px;
  width: 86%;
}
.btn2{
  margin: 10px 10px 0 0;
  width: 86%;
}
.picktime{
 position: relative;
  width: 100%;
}
span{
  color: gray;
  font-size: 14px;
}
</style>
