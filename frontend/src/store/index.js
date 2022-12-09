import Vue from 'vue'
import Vuex from 'vuex'

//挂载Vuex
Vue.use(Vuex)

//创建VueX对象
const store = new Vuex.Store({
    state:{
        //存放的键值对就是所要管理的状态
        userInfo: JSON.parse(sessionStorage.getItem('UserInfo')),
        c_id: 0,
        l_id: 0,
        roleInCourse: '',
    },
    mutations:{
        setUserInfo(state){
            state.userInfo = JSON.parse(sessionStorage.getItem('UserInfo'))
        },
        setCurrentCid(state, c_id) {
            state.c_id=c_id;
        },
        setCurrentLid(state, l_id) {
            state.l_id=l_id;
        },
        setCourseRole(state, role) {
            state.roleInCourse=role;
        }
    }
})

export default store