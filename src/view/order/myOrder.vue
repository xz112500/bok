<template>
  <div id="myOrder">
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      prop="createtime"
      label="日期"
      width="250">
    </el-table-column>
    <el-table-column
      prop="username"
      label="姓名"
      width="250">
    </el-table-column>
    <el-table-column
      prop="address"
      label="地址"
      width="300">
    </el-table-column>
    <el-table-column
      prop="commodity"
      label="商品名称"
      width="300">
    </el-table-column>
    <el-table-column
      prop="price"
      label="价格"
      width="300">
    </el-table-column>
    <el-table-column
      prop="counts"
      label="数量"
      width="200">
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "myOrder",
  data(){
    return{
      tableData: []
    }
  },

  created() {
    this.getOrders();
  },
  methods:{
   async getOrders(){
    await axios.get("http://localhost:8080/commodity/queryOrder",
      {
        params:{
          username:sessionStorage.getItem("username"),
        }
      }).then(res=>{
    /*  console.log(res)*/
        this.tableData=res.data.meta.data;
    })
    }
  }
}
</script>

<style scoped>

</style>
