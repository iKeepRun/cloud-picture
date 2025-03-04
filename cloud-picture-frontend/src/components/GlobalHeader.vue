<template>
    <a-row :wrap="false">
        <a-col flex="250px">
            <router-link to="/">
                <div class="title-bar">
                    <img class="logo" src="../assets/cloud1.png" alt="cloud-picture">
                    <div class="title">智能云图库</div>
                </div>
            </router-link>
        </a-col>
        <a-col flex="auto">
            <a-menu v-model:selectedKeys="current" mode="horizontal" :items="items" @click="doMenuClick" />
        </a-col>
        <a-col flex="100px">
            <div class="login-content">
                <div v-if="loginUserStore.loginUser.id">
                    <a-dropdown>
                        <a-space>
                            <a-avatar :src="loginUserStore.loginUser.userAvatar" />
                            {{ loginUserStore.loginUser.userName ?? '未知用户' }}
                        </a-space>

                        <template #overlay>
                            <a-menu>
                                <a-menu-item @click="doLogout">
                                    <a href="javascript:;">
                                        <LogoutOutlined />
                                        退出登录
                                    </a>
                                </a-menu-item>

                            </a-menu>
                        </template>
                    </a-dropdown>
                </div>

                <div v-else>
                    <a-button type="primary" class="login-btn" href="/user/login">登录</a-button>
                </div>
            </div>
        </a-col>
    </a-row>

</template>





<script lang="ts" setup>
import { DownOutlined, LogoutOutlined } from '@ant-design/icons-vue';



import { h, ref } from 'vue';
import { MailOutlined, AppstoreOutlined, SettingOutlined, HomeOutlined } from '@ant-design/icons-vue';
import { message, type MenuProps } from 'ant-design-vue';
import { useRouter } from 'vue-router';
import { useLoginUserStore } from '@/stores/useLoginUserStore';
import { logoutUsingGet } from '@/api/userController';


const loginUserStore = useLoginUserStore()
loginUserStore.fetchLoginUser()



const items = ref<MenuProps['items']>([
    {
        key: '/',
        icon: () => h(HomeOutlined),
        label: '主页',
        title: '主页',
    },
    {
        key: '/about',
        icon: () => h(AppstoreOutlined),
        label: '关于',
        title: '关于',
    }
]);



const router = useRouter();

// const doMenuClick = ({ item, key, keyPath }) => {
const doMenuClick = () => {
    // router.push({
    //     path: key
    // })
}
const doLogout = async () => {
    console.log("exit enter")
    const res = await logoutUsingGet()
    if (res.data.code === 0 && res.data.data) {
        loginUserStore.setLoginUser({
            userName:"未登录",
        })
        
        await router.push({path: '/user/login'})
    } else {
        message.error("退出登录失败："+res.data.msg)
    }
}

const current = ref<string[]>([]);
router.afterEach((to, from, next) => {
    current.value = [to.path]
})
</script>


<style scoped>
.title-bar {
    display: flex;
    align-items: center;
}

.title {
    color: black;
    font-size: 18px;
    margin-left: 16px;
}

.logo {
    /* width: 40px; */
    height: 48px;

}

.login-btn {
    /* width: 120px;
    height: 40px;
    line-height: 40px;
    text-align: center; */
}
</style>