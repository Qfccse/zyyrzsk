<template>
  <div>
    <el-container>
      <el-aside width="180px">
        <!-- 个人信息 -->
        <div class="personalInfo" height="2000px">
          <div style="margin-left: 65px">
            <img
              src="../../src/assets/avatars/admin.jpg"
              style="
                height: 60px;
                width: 60px;
                margin-top: 20px;
                border-radius: 50%;
              "
              @click="testVuex"
            />
            <h5
              style="
                color: #ffffff;
                width: 60px;
                font-weight: bolder;
                text-align: center;
                margin-top: 5px;
              "
            >
              {{ Name }}
            </h5>
            <p style="color: #b2aeaf; margin-left: 15px;" >{{ Id }}</p>
          </div>
        </div>

        <el-menu
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#393836"
          text-color="#b2aeaf"
          active-text-color="#349f50"
        >
          <!-- 侧边栏导航区 -->
          <el-menu-item index="1" @click="gotoTeacherList">
            <i class="el-icon-document"></i>
            <template v-slot:title>教师管理</template>
          </el-menu-item>

          <el-menu-item index="2" @click="gotoStudentList">
            <i class="el-icon-document"></i>
            <template v-slot:title>学生管理</template>
          </el-menu-item>

          <el-menu-item index="3" @click="gotoUserList">
            <i class="el-icon-document"></i>
            <template v-slot:title>用户管理</template>
          </el-menu-item>

          <el-menu-item index="4" @click="$router.push('/admin/board')">
            <i class="el-icon-data-board"></i>
            <template v-slot:title>公告板</template>
          </el-menu-item>

          <el-menu-item index="5" disabled>
            <i class="el-icon-setting"></i>
            <template v-slot:title>我的信息</template>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header>
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#393836"
            text-color="#b2aeaf"
            active-text-color="#349f50"
          >
            <el-menu-item index="1"
              ><b>同济大学实验教学课程管理系统</b></el-menu-item
            >

            <el-menu-item index="4" style="float: right"  @click="logout">
              <i class="el-icon-unlock"></i>退出登录</el-menu-item
            >
            <!-- <el-menu-item index="3" disabled style="float: right;">消息中心</el-menu-item> -->

            <!-- <el-submenu index="2" style="float: right;">
              <template v-slot:title>我的工作台</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
              <el-menu-item index="2-3">选项3</el-menu-item>
              <el-submenu index="2-4">
                <template v-slot:title>选项4</template>
                <el-menu-item index="2-4-1">选项1</el-menu-item>
                <el-menu-item index="2-4-2">选项2</el-menu-item>
                <el-menu-item index="2-4-3">选项3</el-menu-item>
              </el-submenu>
            </el-submenu> -->
          </el-menu>
        </el-header>
        <el-main style="min-height: 800px">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "AdminLayout",
  data() {
    return {
      activeIndex: "1",
      activeIndex2: "1",
      Name: this.$store.state.userInfo.name,
      Id: this.$store.state.userInfo.id,
    };
  },
  methods: {
    testVuex() {
      alert(
        this.$store.state.userInfo.role +
          "   " +
          this.$store.state.userInfo.id +
          "\n" +
          this.Name +
          "  " +
          this.Id
      );
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    gotoTeacherList(name) {
      console.log(name);
      this.$router.push({
        path: "/admin/teacherCourses",
      });
    },
    gotoStudentList(c_id) {
      console.log(c_id);
      this.$router.push({
        path: "/admin/studentCourses",
      });
    },
    gotoUserList() {
      this.$router.push({
        path: "/admin/user",
      });
    },
    logout() {
      this.$store.commit("clear");
      sessionStorage.clear();
      this.$router.push("/login");
    }
  },
  mounted() {
    // sessionStorage.setItem(
    //   "UserInfo",
    //   JSON.stringify({ id: "9527", role: "admin" , name: "郭明暄"})
    // );
    // this.$store.commit("setUserInfo");

    this.getCoursesAsStudent();
    console.log("layout has been mounted");
  }
};
</script>


<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-header {
  background-color: #393836;
}

.el-aside {
  background-color: #393836;
}

.el-main {
  /* background-color: #e9eef3; */
}

.personalInfo {
  height: 160px;
  background-color: #292929;
}
</style>
