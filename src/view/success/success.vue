<template>
<div id="success">
   <div class="message"><img src="../../assets/成功.png" alt="">
     <span class="s1">下单成功</span>
     <div class="check" @click="check"><span>查看订单</span></div>
   </div>

</div>
</template>

<script>
export default {
  name: "success",
  data(){
    return{

    }
  },
  methods:{
    check(){
      this.$router.push('/order')
    }
  }
}
</script>

<style scoped>
#success{
  position: relative;
  width: 100%;
  height: 100%;
  background: aliceblue;
}
img{
  width: 60px;
  height: 60px;
  margin:100px 0 0 300px;
}
.s1{
  font-size: 25px;
  position: relative;
  bottom: 22px;
}
.message{
  position: relative;
  left: 20%;
}
.check{
  width: 100px;
  height: 30px;
  border-radius: 10px;
  border: 1px solid gray;
  margin: 30px 0 0 350px;
}
.check span{
  font-size: 18px;
  color: mediumspringgreen;
  padding-left: 14px;
  }
.check span:hover{
  cursor: pointer;
}
</style>
