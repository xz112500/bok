import axios from 'axios';

const instance=axios.create({
/*  baseURL:'http://8.140.107.62:8890/api/private/v1/',*/
  baseURL:'http://localhost:8080/user/',
  timeout:10000,
})
const getComm=axios.create({
  baseURL:'http://localhost:8080/commodity/',
  timeout:10000,
})
 axios.interceptors.request.use(config=>{
  config.headers.Authorization=window.sessionStorage.getItem('token');
  return config;
})
export  function post(url,data){
  return instance.post(url,data)
}
export function get(url,params){
  return instance.get(url,params)
}
export function postcomm(url,data){
  return getComm.post(url,data)
}
export function getcomm(url,params){
  return getComm.get(url,params)
}
