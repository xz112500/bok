import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/login.vue";
import Index from "../view/index.vue"
import Register from "../view/Register.vue";
import Commodity from "../components/Commodity.vue";
import Buy from "../components/buy.vue";
import Order from "../view/order/Order.vue";
import Success from '../view/success/success.vue'
import MyOrder from "../view/order/myOrder.vue";
import introduction from "../view/Introduction/introduction.vue";

Vue.use(Router)

const router=new Router({
  mode:'hash',
  routes: [
    {
      path:'/',
      redirect:'/login'
    },
    {
      path: '/login',
      component: Login,
      meta:{
        title:'登录',
      }
    },
    {
      path: '/index',
      component: Index,
      redirect:'/introduction',
      meta: {
        title: "首页"
      },
      children:[
        {path:'/commodity',component:Commodity,meta:{title:"商品"}},
        {path:'/buy',component:Buy,meta:{title: "购买"}},
        {path:'/success',component:Success,meta:{title: '下单成功'}},
        {path:'/order', component:Order, meta:{title: "订单"}},
        {path:'/myOrder',component:MyOrder,meta:{title: "我的订单"}},
        {path:'/introduction',component:introduction,meta:{title: "永无BUG"}}
      ]
    },
    {path:'/register',
      component:Register, meta:{title: "注册"}
    },
  ]
})

export  default  router

router.beforeEach((to, from, next) => {
  if (to.meta.title){
    window.document.title=to.meta.title
  }
  next()
  if (to.path==='/login') return next()
  if (to.path==='/register') return  next('/register')
  next()
  const tokenStr=window.sessionStorage.getItem('token')
  if (!tokenStr) return next('/login')
  next()
})
