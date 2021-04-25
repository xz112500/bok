<template>
<div id="shoes">
<el-card>
  <el-input placeholder="请输入内容" v-model="datalist.search" @keydown.enter.native="search" class="search">
    <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
  </el-input>

  <el-table
    :data="commoditylist"
    height="770"
    border
    style="width: 100%">
    <el-table-column width="200" label="编号" align="center" prop="numbers"></el-table-column>
    <el-table-column
      prop="name"
      label="商品"
      width="300" align="center">
    </el-table-column>
    <el-table-column
      prop="price"
      label="价格"
     width="300" align="center">
    </el-table-column>
    <el-table-column
      prop="counts"
      label="数量"
      width="300" align="center">
    </el-table-column>
    <el-table-column prop="img" label="图片" width="250" align="center">
      <template slot-scope="scope">
        <el-image :src="scope.row.img"  alt="" ></el-image>
      </template>
    </el-table-column>
    <el-table-column
      label="操作" align="center">
      <template slot-scope="scope">
     <el-button type="primary" icon="el-icon-edit" @click.native.stop="Buy(scope.row)">购买</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page.sync="datalist.page"
    :page-sizes="[1,2,5,10,50]"
    :page-size="datalist.num"
    layout="sizes, prev, pager, next"
    :total=100>
  </el-pagination>
</el-card>
</div>
</template>

<script>
import axios from "axios";

export default {
  name: "shoes",
  data() {
    return {
      rowdata:'',
      commoditylist: [],
      datalist: {
        search: this.$route.query.query,
        page: sessionStorage.getItem('messagePage'),
        num: '',
      }
    }
  },
  created() {
    this.getCommodity()
  },
  methods: {
   async getCommodity() {
     await axios.get("http://localhost:8080/commodity/list", {
       params: {
         search: this.datalist.search,
         page: sessionStorage.getItem("messagePage"),
         num: sessionStorage.getItem("messageNum")
       }
     }).then(res => {
        this.commoditylist = res.data.meta.data;
    /*    console.log(res)*/
        switch (res.data.status) {
          case 409:
            this.$message.error("暂无宝贝");
            break;
      /*    case 410:
            this.$message.success("查询成功")
            break;*/
        }
      })
    },
    handleSizeChange(newNum) {
      this.datalist.num = newNum;
      sessionStorage.setItem("messageNum",newNum);
      this.getCommodity();
    },
    handleCurrentChange(newPage) {
      this.datalist.page = newPage;
      sessionStorage.setItem('messagePage',newPage);
      this.getCommodity();
    },
    search() {
      this.getCommodity()
    },
    Buy(row) {
      this.user=this.$route.query.user
/*      console.log(row)*/
      this.rowdata=row
      let data=JSON.stringify(row);
      this.$router.push({path: "/buy", query: {name:data}
      })
    }
  },
  watch:{
    $route(to,from){
      this.$router.go(0);
    },
    'datalist.search':{
      handler(){
        this.getCommodity()
      },
      deep:true,
      immediate:true,
    }
  },
}
</script>

<style scoped>
#shoes{
  height: 100%;
}
.search{
  width: 50%;
  margin-bottom: 10px;
}
</style>
